package entities;

import java.util.Date;

public class Move {
    //FIELDS
    protected final Date time;
    protected final Player player;
    protected Coordinate coordinate;

    //CONSTRUCTORS
    public Move(Player player, Coordinate xy) {
        this.time = new Date();
        this.player = player;
        this.coordinate = xy;
    }

    //METHODS
    public Date getTime() {
        return this.time;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
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
}
