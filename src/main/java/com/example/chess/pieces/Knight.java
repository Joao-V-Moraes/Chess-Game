package com.example.chess.pieces;

import com.example.chess.Operation.Position;

import static com.example.chess.Controllers.ChessBoardController.board;
import static com.example.chess.Controllers.ChessBoardController.fiftyDraw;

public class Knight extends BasePiece{

    public Knight(int color, String piece) {
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

        return (delta.i == 2 && delta.j == 1) || (delta.i == 1 && delta.j == 2);
    }
}
/*O movimento do cavalo corresponde ao movimento em "L". Círculo este que corresponde ao movimento octogonal permitido pelo quadriculado do
tabuleiro. Ele pode andar em "forma de L", ou seja, anda duas casas em linha reta e depois uma casa para o lado. Ao colocar uma peça em cada
posição disponível do movimento do Cavalo, você verá que elas formam um círculo no tabuleiro.*/