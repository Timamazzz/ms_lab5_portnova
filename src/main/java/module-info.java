module ru.bstu.it191.portnova.lab5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens ru.bstu.it191.portnova.lab5 to javafx.fxml;
    exports ru.bstu.it191.portnova.lab5;
}