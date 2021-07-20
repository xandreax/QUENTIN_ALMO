package entities;

import java.util.Date;

public class Move {
    //FIELDS
    protected final Date time;
    protected Pieces piece;     //to do: Player(String username, Piece p)

    //CONSTRUCTORS

    public Move(Date time, Pieces piece) {
        this.time = time;
        this.piece = piece;
    }


    //METHODS
    public Date getTime() {
        return time;
    }

    public Pieces getPiece() {
        return piece;
    }

    public void setPiece(Pieces piece) {
        this.piece = piece;
    }
}
