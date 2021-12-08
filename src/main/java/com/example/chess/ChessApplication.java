package com.example.chess;

import com.example.chess.Controllers.ChessBoardController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


import java.io.IOException;
import java.util.Objects;

public class ChessApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("views/player-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Image icon = new Image(Objects.requireNonNull(ChessApplication.class.getResourceAsStream("pieces/icon.png")));
        stage.getIcons().add(icon);
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}