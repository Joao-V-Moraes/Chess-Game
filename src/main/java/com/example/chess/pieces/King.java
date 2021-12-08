package com.example.chess.pieces;

import com.example.chess.Operation.Position;

import static com.example.chess.Controllers.ChessBoardController.board;
import static com.example.chess.Controllers.ChessBoardController.fiftyDraw;

public class King extends BasePiece{

    public King(int color, String piece) {
        super(color, piece);
    }

    @Override
    public boolean validMove( Position destinationPosition){
        BasePiece destinationPiece = board[destinationPosition.i][destinationPosition.j].getPiece();

        if(destinationPiece != null && destinationPiece.getColor() == getColor()) {
            return false;
        }
        int dI = Math.abs(destinationPosition.i - getSourcePosition().i);
        int dJ = Math.abs(destinationPosition.j - getSourcePosition().j);
        Position delta = new Position(dI,dJ);
        return delta.i <= 1 && delta.j <= 1;
    }
}
/*Pode mover-se em todas as direções, mas limitado somente à sua casa vizinha.*/