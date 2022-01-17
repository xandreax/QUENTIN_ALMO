package entities;

import exceptions.UnsupportedPiecesForPlayerException;
import exceptions.UsernameTooShortException;

public class Player {
    //FIELDS
    private String username;
    private Pieces pieces;

    //CONSTRUCTORS
    public Player(String username, Pieces pieces) throws UnsupportedPiecesForPlayerException, UsernameTooShortException {
        if (pieces == Pieces.NONE) {
            throw new UnsupportedPiecesForPlayerException();
        }
        else {
            if (username.length() >= 3) {
                this.username = username;
                this.pieces = pieces;
            }
            else throw new UsernameTooShortException();
        }
    }

    //METHODS
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Pieces getPieces() {
        return pieces;
    }

    public void setPieces(Pieces pieces) {
        this.pieces = pieces;
    }

    //TODO: Override anche di hashcode
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Player) {
            Player param = (Player) obj;
            return this.getUsername().equals(param.getUsername());
        }
        else {
            return false;
        }
    }
}
