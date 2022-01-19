package exceptions;

public class UsernameTooShortException extends Exception{
    //FIELDS
    private final String message;

    //CONSTRUCTORS
    public UsernameTooShortException() {
        this.message = "This username is too short, please use a longer one";
    }

    public UsernameTooShortException(String message) {
        this.message = message;
    }
}
