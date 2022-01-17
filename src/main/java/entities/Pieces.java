package entities;

public enum Pieces {
    //ENUMS
    WHITE("White", "W"),
    BLACK("Black", "B"),
    NONE("No piece", "+");

    //FIELDS
    private final String name;
    private final String symbol;

    //CONSTRUCTORS
    Pieces(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    //METHODS
    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }
}
