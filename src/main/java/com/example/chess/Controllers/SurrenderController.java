package com.example.chess.Controllers;

import javafx.scene.control.Label;

public class SurrenderController {
    public Label colorPlayer;

    public void setPlayer(int currentPlayer) {
        if (currentPlayer == 1)
            colorPlayer.setText("Preto");
        else
            colorPlayer.setText("Branco");

    }
}
