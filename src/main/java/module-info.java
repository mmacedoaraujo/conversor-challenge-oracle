module com.example.conversor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mmacedoaraujo.conversor to javafx.fxml;
    exports com.mmacedoaraujo.conversor;
}