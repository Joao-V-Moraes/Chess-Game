module com.example.chess {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.chess to javafx.fxml;
    exports com.example.chess;
    opens com.example.chess.pieces to javafx.fxml;
    exports com.example.chess.pieces;
    exports com.example.chess.Controllers;
    opens com.example.chess.Controllers to javafx.fxml;
    exports com.example.chess.Operation;
    opens com.example.chess.Operation to javafx.fxml;
}