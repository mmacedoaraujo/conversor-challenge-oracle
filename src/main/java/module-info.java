module com.example.conversor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.conversor to javafx.fxml;
    exports com.example.conversor;
}