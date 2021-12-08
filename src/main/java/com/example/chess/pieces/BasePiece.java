package com.example.chess.pieces;

import com.example.chess.Operation.Position;

public abstract class BasePiece {

    public static final String PAWN = "pawn";
    public static final String QUEEN = "queen";
    public static final String KING = "king";
    public static final String KNIGHT = "knight";
    public static final String BISHOP = "bishop";
    public static final String ROOK = "rook";

    private final int color;
    private String pieceType;
    private Position sourcePosition;
    private boolean hasMoved;

    public BasePiece(int color, String pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    public String getPieceType() {
        return pieceType;
    }

    public void setPieceType(String pieceType) {
        this.pieceType = pieceType;
    }

    public int getColor() {
        return color;
    }

    public Position getSourcePosition() {
        return sourcePosition;
    }

    public void setSourcePosition(Position sourcePosition) {
        this.sourcePosition = sourcePosition;
    }

    public void setSourcePosition(int i, int j){
        this.sourcePosition = new Position(i, j);
    }

    public abstract boolean validMove( Position destinationPosition);


    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

}
