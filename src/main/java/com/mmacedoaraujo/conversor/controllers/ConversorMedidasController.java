package com.mmacedoaraujo.conversor.controllers;

import com.mmacedoaraujo.conversor.service.ConversorMedidasService;
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

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static com.mmacedoaraujo.conversor.util.Constants.METRIC;

public class ConversorMedidasController implements Initializable {

    @FXML
    private ComboBox<String> comboBoxTemperatura = new ComboBox();
    @FXML
    private ComboBox<String> comboBoxTemperaturaDestino = new ComboBox();
    @FXML
    private TextField valorConversao;
    @FXML
    private Button converterBtn;
    @FXML
    private Label currencyNameLbl;
    @FXML
    private Label conversionLbl;
    @FXML
    private Label temperatureAbbreviationLbl;
    @FXML
    private ImageView closeImg;

    private ConversorMedidasService service;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Constraints.setTextFieldDouble(valorConversao);
        initializeComboBoxes();
        updateInterfaceValues();
        Constraints.setTextFieldDouble(this.valorConversao);
    }

    @FXML
    public void onConverterBtnClick() throws IOException {
        BigDecimal conversion = service.getPosition(METRIC, comboBoxTemperatura.getValue(), comboBoxTemperaturaDestino.getValue(), valorConversao.getText());
        converterBtn.setVisible(false);
        conversionLbl.setText(conversion.toPlainString() + " " + comboBoxTemperaturaDestino.getValue().substring(0, 3));
        conversionLbl.setVisible(true);
        closeImg.setVisible(true);
    }

    @FXML
    public void updateInterfaceValues() {
        removeAlreadySelectedValue();
        temperatureAbbreviationLbl.setText(comboBoxTemperatura.getValue().substring(0, 3));
    }

    @FXML
    public void clearConversion(MouseEvent event) {
        closeImg.setVisible(false);
        conversionLbl.setVisible(false);
        converterBtn.setText("Converter");
        converterBtn.setVisible(true);
        event.consume();
    }


    private void initializeComboBoxes() {
        List<String> comboBoxItems = METRIC;
        comboBoxTemperatura.getItems().addAll(comboBoxItems);
        comboBoxTemperatura.getSelectionModel().selectFirst();
        List<String> filteredList = comboBoxItems.stream().filter(item -> !item.equals(comboBoxTemperatura.getValue())).collect(Collectors.toList());
        comboBoxTemperaturaDestino.getItems().addAll(filteredList);
        comboBoxTemperaturaDestino.getSelectionModel().selectNext();
    }

    private void removeAlreadySelectedValue() {
        List<String> comboBoxItems = METRIC;
        List<String> filteredList = comboBoxItems.stream().filter(item -> !item.equals(comboBoxTemperatura.getValue())).collect(Collectors.toList());
        comboBoxTemperaturaDestino.getItems().clear();
        comboBoxTemperaturaDestino.getItems().addAll(filteredList);
        comboBoxTemperaturaDestino.getSelectionModel().selectNext();
        comboBoxTemperaturaDestino.getSelectionModel().selectPrevious();
    }

}
