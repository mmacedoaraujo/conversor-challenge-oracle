package com.example.conversor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ConversorMoedasController implements Initializable {

    @FXML
    private ComboBox<String> comboBoxMoeda = new ComboBox();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxMoeda.getItems().addAll(
                "BRL",
                "USD",
                "EUR",
                "CHI",
                "AAA"
        );
        comboBoxMoeda.getSelectionModel().select(0);
    }
}
