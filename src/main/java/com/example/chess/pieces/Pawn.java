package com.example.chess.pieces;

import com.example.chess.Operation.Position;

import static com.example.chess.Controllers.ChessBoardController.*;

public class Pawn extends BasePiece{
    public Pawn(int color, String piece) {
        super(color, piece);
    }

    @Override
    public boolean validMove( Position destinationPosition){
        int range = hasMoved() ? 1 : 2;
        boolean forward, capture;
        int step = 1;
        boolean possibleVerticalMovement = true;

        BasePiece destinationPiece = board[destinationPosition.i][destinationPosition.j].getPiece();

        if(destinationPiece != null && destinationPiece.getColor() == getColor()) {
            return false;
        }
        int dI = destinationPosition.i - getSourcePosition().i;
        int dJ = destinationPosition.j - getSourcePosition().j;
        Position delta = new Position(dI,dJ);
        if(getColor() == WHITE) {
            delta.i = -delta.i;
            step = -step;
        }

        Position possiblePosition = new Position(getSourcePosition().i,getSourcePosition().j);
        for(int i = 0; i < range ; i++){
            possiblePosition.i += step;
            BasePiece auxPiece = board[possiblePosition.i][possiblePosition.j].getPiece();
            if(auxPiece != null) {
                possibleVerticalMovement = false;
                break;
            }
        }
        forward = delta.i > 0 && delta.i <= range && delta.j == 0 && possibleVerticalMovement;

        capture = delta.i == 1 && Math.abs(delta.j) == 1 && destinationPiece != null;
        return forward || capture;
    }
}
/*O peão é a única peça do xadrez que nunca retrocede no tabuleiro. Portanto, quando se encontra na segunda fila sempre estará disponível para
 fazer o seu primeiro movimento. Nesse caso ele pode "optar" entre "andar" uma ou duas casas sempre na mesma coluna. Passando da segunda fila,
  não mais pode se deslocar duas casas. Quando vai capturar uma peça, ele desloca-se na diagonal, andando apenas uma casa, sempre para frente.
  O peão não pode capturar para trás, e não captura peças que obstruam o seu caminho.*/