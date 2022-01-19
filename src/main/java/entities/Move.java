package entities;

import java.util.Date;
import java.util.Objects;

public class Move {
    //FIELDS
    private final Date time;
    private final Player player;
    private BoardCoordinate boardCoordinate;

    //CONSTRUCTORS
    public Move(Player player, BoardCoordinate xy) {
        this.time = new Date();
        this.player = player;
        this.boardCoordinate = xy;
    }

    //METHODS
    public Date getTime() {
        return this.time;
    }

    public Player getPlayer() {
        return this.player;
    }

    public BoardCoordinate getCoordinate() {
        return boardCoordinate;
    }

    public void setCoordinate(BoardCoordinate boardCoordinate) {
        this.boardCoordinate = boardCoordinate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Move) {
            Move param = (Move) obj;
            return (this.getTime().equals(param.getTime()) && this.getPlayer().equals(param.getPlayer()) && this.getCoordinate().equals(param.getCoordinate()));
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, player, boardCoordinate);
    }
}
