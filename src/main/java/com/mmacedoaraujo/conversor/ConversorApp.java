package com.mmacedoaraujo.conversor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class ConversorApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ConversorApp.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        Font.loadFont(ConversorApp.class.getResource("/fonts/Roboto-Medium.ttf").toExternalForm(), 10);
        scene.getStylesheets().add("app.css");
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap");
        stage.setTitle("Conversor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}