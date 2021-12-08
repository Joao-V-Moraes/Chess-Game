package com.example.chess.Controllers;

import com.example.chess.ChessApplication;
import com.example.chess.Operation.Position;
import com.example.chess.Operation.Square;
import com.example.chess.pieces.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class ChessBoardController {
    private Button sourceButton = new Button();
    public final static Square[][] board = new Square[8][8];
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    public GridPane gridPane;
    private boolean clickState;
    public static int currentPlayer = WHITE;
    public static short fiftyDraw = 0;
    public static String name1;
    public static String name2;
    public Label currentPlayerLabel;


    private King kingB, kingW;

    public Position sourcePosition, destinationPosition;

    public void initialize() {
        currentPlayerLabel.setText(name1);
        currentPlayer = WHITE;
        fiftyDraw = 0;
        GenerateBoard();

    }

    public void GenerateBoard() {
        for (int i = 7; i >= 0; i--) {
            Label line = new Label(Math.abs(i - 7) + 1 + "");
            Label col = new Label(((char) (i + 65)) + "");

            col.getStyleClass().add("labelBold");
            col.setPadding(new Insets(10));
            col.setAlignment(Pos.CENTER);
            col.setPrefWidth(100);
            gridPane.add(col, i, 8);

            line.getStyleClass().add("labelBold");
            line.setPadding(new Insets(10));
            line.setAlignment(Pos.CENTER);
            line.setPrefHeight(100);
            gridPane.add(line, 8, i);

            line.getStyleClass().add("edge");
            col.getStyleClass().add("edge");
        }


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                BasePiece piece = null;

                Button button = new Button();
                button.setOnAction(this::onMouseClicked);
                button.getStyleClass().add("style");
                button.setPrefWidth(100);
                button.setPrefHeight(100);

                boolean cond = Math.abs(i - j) % 2 == 0;
                button.getStyleClass().add(cond ? "black" : "white");

                //creat rook
                if (i == 0 && j == 0 || i == 0 && j == 7) {
                    piece = new Rook(BLACK, BasePiece.ROOK);

                } else if (i == 7 && j == 7 || i == 7 && j == WHITE) {
                    piece = new Rook(WHITE, BasePiece.ROOK);
                }

                //creat knight
                if (i == 0 && j == 1 || i == 0 && j == 6) {
                    piece = new Knight(BLACK, BasePiece.KNIGHT);
                } else if (i == 7 && j == 6 || i == 7 && j == 1) {
                    piece = new Knight(WHITE, BasePiece.KNIGHT);
                }

                //creat bishop
                if (i == 0 && j == 2 || i == 0 && j == 5) {
                    piece = new Bishop(BLACK, BasePiece.BISHOP);
                } else if (i == 7 && j == 5 || i == 7 && j == 2) {
                    piece = new Bishop(WHITE, BasePiece.BISHOP);
                }

                //creat Queen
                if (i == 0 && j == 3) {
                    piece = new Queen(BLACK, BasePiece.QUEEN);
                } else if (i == 7 && j == 3) {
                    piece = new Queen(WHITE, BasePiece.QUEEN);
                }

                //creat King
                if (i == 0 && j == 4) {
                    piece = new King(BLACK, BasePiece.KING);
                    kingB = (King) piece;
                } else if (i == 7 && j == 4) {
                    piece = new King(WHITE, BasePiece.KING);
                    kingW = (King) piece;
                }

                //creat Pawn
                if (i == 1) {
                    piece = new Pawn(BLACK, BasePiece.PAWN);
                } else if (i == 6) {
                    piece = new Pawn(WHITE, BasePiece.PAWN);
                }

                ImageView imageView = new ImageView();
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);

                imageView.setImage(findImage(piece));

                button.setGraphic(imageView);

                if (piece != null)
                    piece.setSourcePosition(new Position(i, j));
                board[i][j] = new Square(piece, imageView);

                gridPane.add(button, j, i);//j i
            }

        }

        //onMouseClicked();
    }

    public Image findImage(BasePiece piece) {
        if (piece == null) {
            return null;
        }
        String fileName = "pieces/" + piece.getPieceType() + piece.getColor() + ".png";
        return new Image(ChessApplication.class.getResourceAsStream(fileName));
    }

    @FXML
    public void onMouseClicked(ActionEvent actionEvent) {

        Button button = (Button) actionEvent.getSource();
        ImageView imageView = (ImageView) button.getGraphic();

        if (!clickState) {
            sourcePosition = getClickedPosition(imageView);
            BasePiece sourcePiece = board[sourcePosition.i][sourcePosition.j].getPiece();
            if (sourcePiece == null || sourcePiece.getColor() != currentPlayer)
                return;
            sourceButton = button;
            possibleMovements(sourcePiece);
            button.getStyleClass().add("edgeSquare");
        } else {
            destinationPosition = getClickedPosition(imageView);

            if (move()) {
                Button whiteKingButton = getButtonAt(kingW.getSourcePosition());
                Button blackKingButton = getButtonAt(kingB.getSourcePosition());
                whiteKingButton.getStyleClass().removeAll("kingSquare");
                blackKingButton.getStyleClass().removeAll("kingSquare");
                if (checkWhite())
                    whiteKingButton.getStyleClass().add("kingSquare");
                if (checkBlack())
                    blackKingButton.getStyleClass().add("kingSquare");

                currentPlayer = currentPlayer == WHITE ? BLACK : WHITE;
                if (currentPlayer == WHITE)
                    currentPlayerLabel.setText(name1);
                else
                    currentPlayerLabel.setText(name2);
            }
            clearPossibleMovements();
            sourceButton.getStyleClass().removeAll("edgeSquare");
        }
        clickState = !clickState;
    }

    public Position getClickedPosition(ImageView imageView) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getImageView().equals(imageView)) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    public Button getButtonAt(Position position) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (position.equals(new Position(i, j))) {
                    return (Button) board[i][j].getImageView().getParent();
                }
            }
        }
        return null;
    }

    public boolean move() {

        Square sourceSquare = board[sourcePosition.i][sourcePosition.j];
        BasePiece sourcePiece = sourceSquare.getPiece();

        Square destinationSquare = board[destinationPosition.i][destinationPosition.j];

        if (sourcePiece == null || !sourcePiece.validMove(destinationPosition))
            return false;

        if (sourceSquare != destinationSquare) {
            if (board[destinationPosition.i][destinationPosition.j].getPiece() != null) {
                fiftyDraw = 0;
            } else
                fiftyDraw += 1;

            if (board[destinationPosition.i][destinationPosition.j].getPiece() != null)
                if (board[destinationPosition.i][destinationPosition.j].getPiece().getPieceType().equals("king"))
                    if (board[destinationPosition.i][destinationPosition.j].getPiece().getColor() == WHITE)
                        wWinPopUp();
                    else if (board[destinationPosition.i][destinationPosition.j].getPiece().getColor() == BLACK)
                        bWinPopUp();

            sourcePiece.setSourcePosition(destinationPosition.i, destinationPosition.j);
            destinationSquare.setPiece(sourcePiece);
            sourceSquare.setPiece(null);
            destinationSquare.getImageView().setImage(sourceSquare.getImageView().getImage());
            sourceSquare.getImageView().setImage(null);
            sourcePiece.setHasMoved(true);

            if (fiftyDraw == 50)
                drawPopUp();

            return true;
        }
        return false;
    }

    public boolean checkWhite() {
        List<BasePiece> listPieceB = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getPiece() != null)
                    if (board[i][j].getPiece().getColor() == BLACK)
                        listPieceB.add(board[i][j].getPiece());
            }
        }
        for (BasePiece blackPiece : listPieceB) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Position position = new Position(i, j);
                    if (blackPiece.validMove(position))
                        if (position.equals(kingW.getSourcePosition()))
                            return true;
                }
            }
        }
        return false;
    }

    public boolean checkBlack() {
        List<BasePiece> listPieceW = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getPiece() != null)
                    if (board[i][j].getPiece().getColor() == WHITE)
                        listPieceW.add(board[i][j].getPiece());
            }
        }
        for (BasePiece whitePiece : listPieceW) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Position position = new Position(i, j);
                    if (whitePiece.validMove(position))
                        if (position.equals(kingB.getSourcePosition()))
                            return true;
                }
            }
        }
        return false;
    }

    public void possibleMovements(BasePiece piece) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (piece.validMove(new Position(i, j))) {
                    Button button = (Button) board[i][j].getImageView().getParent();
                    button.getStyleClass().add("selectSquare");
                }

            }

        }
    }

    public void clearPossibleMovements() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Button button = (Button) board[i][j].getImageView().getParent();
                button.getStyleClass().removeAll("selectSquare");
            }

        }
    }


    public void drawPopUp() {
        try {
            gridPane.setDisable(true);
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
            }, 5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void wWinPopUp() {
        try {
            gridPane.setDisable(true);
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("views/wWin-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 350);
            stage.setTitle("BRANCO GANHOU!");
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
            }, 5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bWinPopUp() {
        try {
            gridPane.setDisable(true);
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("views/draw-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 350);
            stage.setTitle("PRETO GANHOU!");
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
            }, 5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void surrender() {
        try {
            gridPane.setDisable(true);
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("views/surrender-view.fxml"));
            Parent parent = fxmlLoader.load();
            SurrenderController surrenderController = fxmlLoader.getController();
            surrenderController.setPlayer(currentPlayer);
            Scene scene = new Scene(parent, 500, 350);
            stage.setTitle("Desistência");
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
            }, 5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void suggestTie() {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("views/suggest-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 350);
            stage.setTitle("Sugerir empate");
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
            }, 15000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reset(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
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
