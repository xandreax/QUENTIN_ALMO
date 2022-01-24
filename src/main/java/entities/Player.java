package entities;

import exceptions.UnsupportedPiecesForPlayerException;
import exceptions.UsernameTooShortException;

import java.util.Objects;

public class Player {
    //FIELDS
    private final String username;
    private Pieces pieces;

    //CONSTRUCTORS
    public Player(String username, Pieces pieces) throws UnsupportedPiecesForPlayerException, UsernameTooShortException {
        if (pieces == Pieces.NONE) {
            throw new UnsupportedPiecesForPlayerException();
        } else {
            if (username.length() >= 3) {
                this.username = username;
                this.pieces = pieces;
            } else throw new UsernameTooShortException();
        }
    }

    //METHODS
    public String getUsername() {
        return username;
    }

    public Pieces getPieces() {
        return pieces;
    }

    public void setPieces(Pieces pieces) {
        this.pieces = pieces;
    }

    public Player invertPlayer(Player[] players) {
        if (this.equals(players[0]))
            return players[1];
        else
            return players[0];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o instanceof Player) {
            Player player = (Player) o;
            return username.equals(player.username);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    public boolean isWhitePlayer() {
        return this.getPieces().equals(Pieces.WHITE);
    }

    public boolean isBlackPlayer() {
        return this.getPieces().equals(Pieces.BLACK);
    }
}
