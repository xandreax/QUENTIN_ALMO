package exceptions;

import entities.Pieces;

public class VictoryException extends Exception{
    //FIELDS
    private final Pieces piece;

    public VictoryException(Pieces piece) {
        this.piece = piece;
    }

    public Pieces getPiece() {return piece;}
}
