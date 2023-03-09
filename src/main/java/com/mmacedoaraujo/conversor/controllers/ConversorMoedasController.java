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
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConversorMoedasController implements Initializable {

    private static final String[] CURRENCIES_ABBREVIATIONS = {"BRL",
            "USD",
            "EUR",
            "CHI",
            "AAA"};

    private static final String URL_API = "https://economia.awesomeapi.com.br/json/last/";
    private ApiResponseValuesEntity apiResponseValuesEntity;


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
    public void onConverterBtnClick() throws IOException {
        requestCreator();
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
        currencyNameLbl.setText("Digite a quantidade de" +
                " " +
                name.replaceAll("(?=/).*", "").toLowerCase() +
                " \nque deseja converter para " +
                name.replaceAll(".*(?=/).", "").toLowerCase() + ":");
    }

    private void initializeComboBoxes() {
        comboBoxMoeda.getItems().addAll(
                CURRENCIES_ABBREVIATIONS
        );
        comboBoxMoeda.getSelectionModel().select(0);

        comboBoxMoedaDestino.getItems().addAll(
                CURRENCIES_ABBREVIATIONS
        );
        comboBoxMoedaDestino.getSelectionModel().select(1);
    }

    private ApiResponseValuesEntity requestCreator() throws IOException {
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String json = mapper.readTree(requestUrlCreator()).get(comboBoxMoeda.getValue() + comboBoxMoedaDestino.getValue()).toString();
        ApiResponseValuesEntity entity = mapper.readValue(json, ApiResponseValuesEntity.class);
        return entity;
    }

    private URL requestUrlCreator() throws MalformedURLException {
        String valueComboBoxMoeda = comboBoxMoeda.getValue();
        String valueComboBoxMoedaFinal = comboBoxMoedaDestino.getValue();

        String urlRequest = URL_API + valueComboBoxMoeda + "-" + valueComboBoxMoedaFinal;

        return new URL(urlRequest);
    }

}
