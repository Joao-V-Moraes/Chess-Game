package com.example.chess.Controllers;

import com.example.chess.ChessApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class LoadController {

    public ProgressBar progressBar;
    float progress = 0;

    public void initialize() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        progress += 0.1;
                        progressBar.setProgress(progress);

                        if (progress >= 1) {
                            openChess();
                            timer.cancel();
                        }
                    }
                });
            }
        }, 0, 750);

    }

    private void openChess() {
        try {
            Stage stage = (Stage) progressBar.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("views/chess-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1100, 800);
            Image icon = new Image(Objects.requireNonNull(ChessApplication.class.getResourceAsStream("pieces/icon.png")));

            stage.getIcons().add(icon);
            stage.setTitle("Chess");
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

