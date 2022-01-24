package exceptions;

public class UsernameTooShortException extends Exception {
    //CONSTRUCTORS
    public UsernameTooShortException() {
        super("This username is too short, please use a longer one");
    }

    public UsernameTooShortException(String message) {
        super(message);
    }
}
