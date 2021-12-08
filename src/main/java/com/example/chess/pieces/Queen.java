package com.example.chess.pieces;

import com.example.chess.Operation.Position;
import javafx.geometry.Pos;

import static com.example.chess.Controllers.ChessBoardController.board;
import static com.example.chess.Controllers.ChessBoardController.fiftyDraw;

public class Queen extends BasePiece {

    public Queen(int color, String piece) {
        super(color, piece);
    }

    @Override
    public boolean validMove(Position destinationPosition) {
        int stepI = 1, stepJ = 1;
        boolean moveVerification = true;
        boolean validDiagonal, validVertical, validHorizontal;
        BasePiece destinationPiece = board[destinationPosition.i][destinationPosition.j].getPiece();

        if (destinationPiece != null && destinationPiece.getColor() == getColor())
            return false;

        int dI = destinationPosition.i - getSourcePosition().i;
        int dJ = destinationPosition.j - getSourcePosition().j;

        Position delta = new Position(Math.abs(dI), Math.abs(dJ));


        if (delta.i > 0 && delta.j > 0 && delta.i != delta.j)
            return false;

        if (dI < 0)
            stepI = -stepI;

        if (dJ < 0)
            stepJ = -stepJ;

        validDiagonal = delta.i == delta.j && validDiagonal(destinationPosition, delta, stepI, stepJ);

        if (validDiagonal)
            return true;

        validHorizontal = delta.i == 0 && validHorizontal(destinationPosition, delta, stepJ);
        if (validHorizontal)
            return true;

        validVertical = delta.j == 0 && validVertical(destinationPosition, delta, stepI);
        return validVertical;
    }
    private boolean validDiagonal(Position destination, Position delta, int stepI, int stepJ){
        Position possiblePosition = new Position(getSourcePosition().i, getSourcePosition().j);

        for (int k = 0; k < delta.i; k++) {
            possiblePosition.i += stepI;
            possiblePosition.j += stepJ;
            if ((possiblePosition.i == -1 || possiblePosition.i == 8) || (possiblePosition.j == -1 || possiblePosition.j == 8))
                break;

            BasePiece auxPiece = board[possiblePosition.i][possiblePosition.j].getPiece();
            if (auxPiece != null)
                return destination.equals(possiblePosition);
        }

        return true;
    }

    private boolean validHorizontal(Position destination, Position delta, int stepJ){
        Position possiblePosition = new Position(getSourcePosition().i, getSourcePosition().j);

        for(int k = 0; k < delta.j; k++){
            possiblePosition.j += stepJ;
            if (possiblePosition.j == -1 || possiblePosition.j == 8)
                break;

            BasePiece auxPiece = board[possiblePosition.i][possiblePosition.j].getPiece();
            if(auxPiece != null)
                return destination.equals(possiblePosition);
        }
        return true;
    }

    private boolean validVertical(Position destination,Position delta,int stepI){
        Position possiblePosition = new Position(getSourcePosition().i, getSourcePosition().j);
        for(int k = 0; k < delta.i; k++){
            possiblePosition.i += stepI;
            if (possiblePosition.i == -1 || possiblePosition.i == 8)
                break;

            BasePiece auxPiece = board[possiblePosition.i][possiblePosition.j].getPiece();
            if(auxPiece != null) {
                    return destination.equals(possiblePosition);
            }
        }
        return true;
    }
}
/*A Dama (também chamada de Rainha) pode movimentar-se quantas casas queira, tanto na diagonal,
como na vertical ou na horizontal, porém, apenas em um sentido em cada jogada.*/




