package com.mmacedoaraujo.conversor.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmacedoaraujo.conversor.model.ApiResponseValuesEntity;
import com.mmacedoaraujo.conversor.util.Constraints;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lombok.SneakyThrows;
import org.apache.commons.math3.util.Precision;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static com.mmacedoaraujo.conversor.service.ConversorMoedasService.convert;
import static com.mmacedoaraujo.conversor.util.Constants.CURRENCIES_ABBREVIATIONS;
import static com.mmacedoaraujo.conversor.util.Constants.URL_API;

public class ConversorMoedasController implements Initializable {
    @FXML
    private ComboBox<String> comboBoxMoeda = new ComboBox();
    @FXML
    private ComboBox<String> comboBoxMoedaDestino = new ComboBox();
    @FXML
    private TextField valorConversao;
    @FXML
    private Button converterBtn;
    @FXML
    private Label currencyNameLbl;
    @FXML
    private Label conversionLbl;
    @FXML
    private Label currencyCodeLbl;
    @FXML
    private ImageView closeImg;


    @FXML
    public void onConverterBtnClick() throws IOException {
        String convertedCurrency = convert(valorConversao.getText(), requestCreator().getBid()).toString();
        converterBtn.setVisible(false);
        conversionLbl.setText(convertedCurrency + " " + comboBoxMoedaDestino.getValue().substring(0, 3));
        conversionLbl.setVisible(true);
        closeImg.setVisible(true);
    }

    @FXML
    public void clearConversion(MouseEvent event) {
        closeImg.setVisible(false);
        conversionLbl.setVisible(false);
        converterBtn.setText("Converter");
        converterBtn.setVisible(true);
        event.consume();
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeComboBoxes();
        Constraints.setTextFieldDouble(this.valorConversao);
        requestCreator();
    }

    @FXML
    private void getCurrencyNames() throws IOException {
        removeAlreadySelectedValue();
        String name = requestCreator().getName();
        String code = requestCreator().getCode();
        currencyCodeLbl.setText(code);
    }

    private void initializeComboBoxes() {
        List<String> comboBoxItems = new ArrayList<>(List.of(CURRENCIES_ABBREVIATIONS));
        comboBoxMoeda.getItems().addAll(comboBoxItems);
        comboBoxMoeda.getSelectionModel().select(0);
        List<String> filteredList = comboBoxItems.stream().filter(item -> !item.equals(comboBoxMoeda.getValue())).collect(Collectors.toList());
        comboBoxMoedaDestino.getItems().addAll(filteredList);
        comboBoxMoedaDestino.getSelectionModel().select(0);
    }

    private ApiResponseValuesEntity requestCreator() throws IOException {
        String comboBoxMoedaCode = comboBoxMoeda.getValue().substring(0, 3);
        String comboBoxMoedaDestinoCode = comboBoxMoedaDestino.getValue().substring(0, 3);

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String json = mapper.readTree(requestUrlCreator()).get(comboBoxMoedaCode + comboBoxMoedaDestinoCode).toString();
        ApiResponseValuesEntity entity = mapper.readValue(json, ApiResponseValuesEntity.class);
        return entity;
    }

    private URL requestUrlCreator() throws MalformedURLException {
        String comboBoxMoedaCode = comboBoxMoeda.getValue().substring(0, 3);
        String comboBoxMoedaDestinoCode = comboBoxMoedaDestino.getValue().substring(0, 3);

        String urlRequest = URL_API + comboBoxMoedaCode + "-" + comboBoxMoedaDestinoCode;
        return new URL(urlRequest);
    }

    private void removeAlreadySelectedValue() {
        List<String> comboBoxItems = new ArrayList<>(List.of(CURRENCIES_ABBREVIATIONS));
        List<String> filteredList = comboBoxItems.stream().filter(item -> !item.equals(comboBoxMoeda.getValue())).collect(Collectors.toList());
        comboBoxMoedaDestino.getItems().clear();
        comboBoxMoedaDestino.getItems().addAll(filteredList);
        comboBoxMoedaDestino.getSelectionModel().selectNext();
        comboBoxMoedaDestino.getSelectionModel().selectPrevious();
    }

}
