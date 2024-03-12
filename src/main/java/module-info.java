module com.example.dobozok {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dobozok to javafx.fxml;
    exports com.example.dobozok;
}