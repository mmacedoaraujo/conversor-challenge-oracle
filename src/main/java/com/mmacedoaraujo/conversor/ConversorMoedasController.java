package com.mmacedoaraujo.conversor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ConversorMoedasController implements Initializable {

    private static final String[] CURRENCIES_ABBREVIATIONS = {"BRL",
            "USD",
            "EUR",
            "CHI",
            "AAA"};

    private static final String URL_API = "https://economia.awesomeapi.com.br/json/";


    @FXML
    private ComboBox<String> comboBoxMoeda = new ComboBox();
    @FXML
    private ComboBox<String> comboBoxMoedaFinal = new ComboBox();
    @FXML
    private TextField valorConversao;
    @FXML
    private Button converterBtn;

    

    @FXML
    public void onConverterBtnClick() {
        String valueComboBoxMoeda = comboBoxMoeda.getValue();
        String valueComboBoxMoedaFinal = comboBoxMoedaFinal.getValue();

        String urlRequest = URL_API + valueComboBoxMoeda + "-" + valueComboBoxMoedaFinal;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeComboBoxes();
    }

    private void getCurrencyNames() {

    }

    private void initializeComboBoxes() {
        comboBoxMoeda.getItems().addAll(
                CURRENCIES_ABBREVIATIONS
        );
        comboBoxMoeda.getSelectionModel().select(0);

        comboBoxMoedaFinal.getItems().addAll(
                CURRENCIES_ABBREVIATIONS
        );
        comboBoxMoedaFinal.getSelectionModel().select(1);
    }
}
