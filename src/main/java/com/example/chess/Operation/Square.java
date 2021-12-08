package com.example.chess.Operation;

import com.example.chess.pieces.BasePiece;
import javafx.scene.image.ImageView;


public class Square {
    private BasePiece piece;
    private ImageView imageView;

    public Square(BasePiece piece, ImageView imageView) {
        this.piece = piece;
        this.imageView = imageView;
    }

    public BasePiece getPiece() {
        return piece;
    }

    public void setPiece(BasePiece piece) {
        this.piece = piece;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
