module com.mycompany.fileanalyse {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.fileanalyse to javafx.fxml;
    exports com.mycompany.fileanalyse;
}
