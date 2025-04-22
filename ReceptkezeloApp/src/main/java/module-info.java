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
    exports micsurin.receptkonyv.receptkezeloapp.command;
    opens micsurin.receptkonyv.receptkezeloapp.command to javafx.fxml;
    exports micsurin.receptkonyv.receptkezeloapp.controller;
    opens micsurin.receptkonyv.receptkezeloapp.controller to javafx.fxml;
}