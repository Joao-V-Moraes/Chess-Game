package com.example.chess.Controllers;

import com.example.chess.ChessApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class TieController {
    public void accept(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("views/draw-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 350);
            stage.setTitle("EMPATE!");
            stage.setScene(scene);
            stage.show();
            Timer time = new Timer();
            time.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            stage.close();
                        }
                    });
                }
            }, 10000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void decline(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }
}
