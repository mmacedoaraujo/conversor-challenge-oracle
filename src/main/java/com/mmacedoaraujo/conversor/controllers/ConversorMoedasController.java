package com.mmacedoaraujo.conversor.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmacedoaraujo.conversor.model.ApiResponseValuesEntity;
import com.mmacedoaraujo.conversor.util.Constraints;
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
import java.util.ResourceBundle;

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
    private ImageView closeImg;


    @FXML
    public void onConverterBtnClick() throws IOException {
        String convertedCurrency = convert(valorConversao.getText(), requestCreator().getBid()).toString();
        converterBtn.setVisible(false);
        conversionLbl.setText(convertedCurrency);
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
        getCurrencyNames();
    }

    @FXML
    private void getCurrencyNames() throws IOException {
        String name = requestCreator().getName();
        currencyNameLbl.setText("Valor a ser convertido" +
                " em " + name.replaceAll(".*(?=/).", "").toLowerCase() + ":");
    }

    private void initializeComboBoxes() {
        comboBoxMoeda.getItems().addAll(
                CURRENCIES_ABBREVIATIONS
        );
        comboBoxMoedaDestino.getItems().addAll(
                CURRENCIES_ABBREVIATIONS
        );

        comboBoxMoedaDestino.getSelectionModel().select(1);
        comboBoxMoeda.getSelectionModel().select(0);
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

    private Double convert(String textFieldValue, Double bid) {
        double valueToBeConverted = Double.parseDouble(textFieldValue);
        return Precision.round(valueToBeConverted * bid, 2);
    }

}
