package com.example.chess.pieces;

import com.example.chess.Operation.Position;

import static com.example.chess.Controllers.ChessBoardController.board;
import static com.example.chess.Controllers.ChessBoardController.fiftyDraw;

public class Rook extends BasePiece {

    public Rook(int color, String piece) {
        super(color, piece);
    }

    @Override
    public boolean validMove(Position destinationPosition) {
        int stepI = 1,stepJ = 1;

        BasePiece destinationPiece = board[destinationPosition.i][destinationPosition.j].getPiece();

        if(destinationPiece != null && destinationPiece.getColor() == getColor()) {
            return false;
        }

        int dI = destinationPosition.i - getSourcePosition().i;
        int dJ = destinationPosition.j - getSourcePosition().j;

        Position delta = new Position(Math.abs(dI), Math.abs(dJ));
        Position possiblePosition = new Position(getSourcePosition().i,getSourcePosition().j);

        if (delta.i > 0 && delta.j > 0)
            return false;

        if(dI < 0)
            stepI = -stepI;

        if(dJ < 0)
            stepJ = -stepJ;

        for(int k = 0; k < delta.i; k++){
            possiblePosition.i += stepI;
            if(possiblePosition.i == 8)
                break;

            BasePiece auxPiece = board[possiblePosition.i][possiblePosition.j].getPiece();
            if(auxPiece != null){
                return k == delta.i - 1 && destinationPiece != null;
            }
        }

        possiblePosition.i = getSourcePosition().i;
        for(int k = 0; k < delta.j; k++){
            possiblePosition.j += stepJ;
            if(possiblePosition.j == 8)
                break;

            BasePiece auxPiece = board[possiblePosition.i][possiblePosition.j].getPiece();
            if(auxPiece != null)
                return k == delta.j - 1 && destinationPiece != null;

        }

        return true;
    }
}
/* A torre se movimenta em direções ortogonais, isto é, pelas linhas (horizontais) e colunas (verticais), não podendo se mover pelas
diagonais. Ela pode mover quantas casas desejar se estiverem desocupadas pelas colunas e linhas, porém, apenas em um sentido em cada jogada.*/