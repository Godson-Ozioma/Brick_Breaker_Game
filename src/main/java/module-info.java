module com.example.brickbreaker_new {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.brickbreaker_new to javafx.fxml;
    exports com.example.brickbreaker_new;
}