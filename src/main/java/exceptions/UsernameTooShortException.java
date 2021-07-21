package exceptions;

public class UsernameTooShortException extends Throwable{
    //FIELDS
    protected String message;

    //CONSTRUCTORS
    public UsernameTooShortException() {
        this.message = "Generic UsernameTooShortException";
    }

    public UsernameTooShortException(String message) {
        this.message = message;
    }

    //METHODS
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
