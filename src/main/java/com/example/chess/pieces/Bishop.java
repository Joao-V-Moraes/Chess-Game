package com.example.chess.pieces;

import com.example.chess.Operation.Position;

import static com.example.chess.Controllers.ChessBoardController.board;
import static com.example.chess.Controllers.ChessBoardController.fiftyDraw;

public class Bishop extends BasePiece{

    public Bishop(int color, String piece) {
        super(color, piece);
    }

    @Override
    public boolean validMove( Position destinationPosition){
        int stepI = 1,stepJ = 1;
        BasePiece destinationPiece = board[destinationPosition.i][destinationPosition.j].getPiece();

        if(destinationPiece != null && destinationPiece.getColor() == getColor()) {
            return false;
        }
        int dI = destinationPosition.i - getSourcePosition().i;
        int dJ = destinationPosition.j - getSourcePosition().j;

        Position delta = new Position(Math.abs(dI), Math.abs(dJ));
        Position possiblePosition = new Position(getSourcePosition().i,getSourcePosition().j);

        if(delta.i != delta.j)
            return false;

        if(dI < 0)
            stepI = -stepI;

        if(dJ < 0)
            stepJ = -stepJ;

        for(int k = 0; k < delta.i; k++){
            possiblePosition.i += stepI;
            possiblePosition.j += stepJ;
            if(possiblePosition.i == 8)
                break;

            BasePiece auxPiece = board[possiblePosition.i][possiblePosition.j].getPiece();
            if(auxPiece != null){
                return k == delta.i - 1 && destinationPiece != null;
            }
        }

        return true;
    }
}
/*O bispo se movimenta nas direções diagonais, não podendo se mover pelas ortogonais como as torres. Ele pode mover quantas casas quiser pelas
diagonais, porém, apenas em um sentido em cada jogada e desde que as mesmas estejam desobstruídas.*/