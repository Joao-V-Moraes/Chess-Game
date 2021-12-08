package com.example.chess.Controllers;

import com.example.chess.ChessApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PlayersController {
    public TextField player1;
    public TextField player2;

    @FXML
    public void onMouseClicked(ActionEvent actionEvent) {
        ChessBoardController.name1 = player1.getText();
        ChessBoardController.name2 = player2.getText();
        try {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("views/load-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 200);
            Image icon = new Image(Objects.requireNonNull(ChessApplication.class.getResourceAsStream("pieces/icon.png")));
            stage.getIcons().add(icon);
            stage.setTitle("Chess");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
