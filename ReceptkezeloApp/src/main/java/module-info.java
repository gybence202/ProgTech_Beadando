module micsurin.receptkonyv.receptkezeloapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens micsurin.receptkonyv.receptkezeloapp to javafx.fxml;
    opens micsurin.receptkonyv.receptkezeloapp.model to javafx.base;
    exports micsurin.receptkonyv.receptkezeloapp;
}