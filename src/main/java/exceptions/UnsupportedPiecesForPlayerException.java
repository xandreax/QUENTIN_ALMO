package exceptions;

public class UnsupportedPiecesForPlayerException extends Exception{
    //FIELDS
    private String message;

    //CONSTRUCTORS
    public UnsupportedPiecesForPlayerException() {
        this.message = "Generic UnsupportedPiecesForPlayerException";
    }

    public UnsupportedPiecesForPlayerException(String message) {
        this.message = message;
    }
}
