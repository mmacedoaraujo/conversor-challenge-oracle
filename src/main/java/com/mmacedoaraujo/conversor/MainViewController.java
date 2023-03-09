package com.mmacedoaraujo.conversor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private AnchorPane mainPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Parent fxml = null;
        try {
            fxml = FXMLLoader.load(getClass().getResource("conversor-moedas.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainPane.getChildren().setAll(fxml);
    }

    @FXML
    protected void onConversorDeMoedasClick(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("conversor-moedas.fxml"));
        mainPane.getChildren().setAll(fxml);
    }

    @FXML
    protected void onConversorDeMedidasClick(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("conversor-medidas.fxml"));
        mainPane.getChildren().setAll(fxml);
    }

    @FXML
    protected void onConversorDeTemperaturaClick(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("conversor-temperatura.fxml"));
        mainPane.getChildren().setAll(fxml);
    }
}