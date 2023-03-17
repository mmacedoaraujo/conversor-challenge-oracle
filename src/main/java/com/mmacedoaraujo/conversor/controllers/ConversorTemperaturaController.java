package com.mmacedoaraujo.conversor.controllers;

import com.mmacedoaraujo.conversor.service.ConversorTemperaturaService;
import com.mmacedoaraujo.conversor.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static com.mmacedoaraujo.conversor.util.Constants.TEMPERATURE;

@AllArgsConstructor
@NoArgsConstructor
public class ConversorTemperaturaController implements Initializable {

    @FXML
    private ComboBox<String> comboBoxTemperaturaEntrada = new ComboBox();
    @FXML
    private ComboBox<String> comboBoxTemperaturaSaida = new ComboBox();
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

    private ConversorTemperaturaService service;

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
        Double conversionResult = service.executeCalculation(comboBoxTemperaturaEntrada.getValue().substring(0, 2), comboBoxTemperaturaSaida.getValue().substring(0, 2), Double.parseDouble(valorConversao.getText()));
        converterBtn.setVisible(false);
        conversionLbl.setText(conversionResult.toString() + " " + comboBoxTemperaturaSaida.getValue().substring(0, 2));
        conversionLbl.setVisible(true);
        closeImg.setVisible(true);
    }

    @FXML
    public void updateInterfaceValues() {
        removeAlreadySelectedValue();
        temperatureAbbreviationLbl.setText(comboBoxTemperaturaEntrada.getValue().substring(0, 2));
        clearConversion();
    }

    @FXML
    public void clearConversion() {
        closeImg.setVisible(false);
        conversionLbl.setVisible(false);
        converterBtn.setText("Converter");
        converterBtn.setVisible(true);
    }


    private void initializeComboBoxes() {
        List<String> comboBoxItems = new ArrayList<>(List.of(TEMPERATURE));
        comboBoxTemperaturaEntrada.getItems().addAll(comboBoxItems);
        comboBoxTemperaturaEntrada.getSelectionModel().selectFirst();
        List<String> filteredList = comboBoxItems.stream().filter(item -> !item.equals(comboBoxTemperaturaEntrada.getValue())).collect(Collectors.toList());
        comboBoxTemperaturaSaida.getItems().addAll(filteredList);
        comboBoxTemperaturaSaida.getSelectionModel().selectNext();
    }

    private void removeAlreadySelectedValue() {
        List<String> comboBoxItems = new ArrayList<>(List.of(TEMPERATURE));
        List<String> filteredList = comboBoxItems.stream().filter(item -> !item.equals(comboBoxTemperaturaEntrada.getValue())).collect(Collectors.toList());
        comboBoxTemperaturaSaida.getItems().clear();
        comboBoxTemperaturaSaida.getItems().addAll(filteredList);
        comboBoxTemperaturaSaida.getSelectionModel().selectNext();
        comboBoxTemperaturaSaida.getSelectionModel().selectPrevious();
    }
}
