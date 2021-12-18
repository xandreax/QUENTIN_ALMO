package exceptions;

public class UsernameTooShortException extends Throwable{
    //FIELDS
    protected String message;

    //CONSTRUCTORS
    public UsernameTooShortException() {
        this.message = "This username is too short, please use a longer one";
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
