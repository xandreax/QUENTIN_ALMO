package exceptions;

public class UnsupportedPiecesForPlayerException extends Exception{

    //CONSTRUCTORS
    public UnsupportedPiecesForPlayerException() {
        super("Generic UnsupportedPiecesForPlayerException");
    }

    public UnsupportedPiecesForPlayerException(String message) {
        super(message);
    }
}
