module me.harshana.guiinpjsp {
    requires javafx.controls;
    requires javafx.fxml;


    opens me.harshana.guiinpjsp to javafx.fxml;
    exports me.harshana.guiinpjsp;
    exports me.harshana.guiinpjsp.controller;
    opens me.harshana.guiinpjsp.controller to javafx.fxml;
}