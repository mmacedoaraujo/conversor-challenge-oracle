module com.example.conversor {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires com.google.gson;
    requires com.fasterxml.jackson.databind;


    opens com.mmacedoaraujo.conversor to javafx.fxml;
    exports com.mmacedoaraujo.conversor;
    opens com.mmacedoaraujo.conversor.model to com.fasterxml.jackson.databind;
    exports com.mmacedoaraujo.conversor.model;
    exports com.mmacedoaraujo.conversor.controllers;
    opens com.mmacedoaraujo.conversor.controllers to javafx.fxml;
}