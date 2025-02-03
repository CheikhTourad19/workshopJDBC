module pfa.java.workshopjdbc {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens pfa.java.workshopjdbc to javafx.fxml;
    exports pfa.java.workshopjdbc;
    exports pfa.java.workshopjdbc.Controllers;
    opens pfa.java.workshopjdbc.Controllers to javafx.fxml;
}