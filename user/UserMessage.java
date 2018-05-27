package user;

public class UserMessage {

    private User u;
    private ErrorType et;

    public UserMessage() {}

    public UserMessage(User u, ErrorType et) {
        this.u = u;
        this.et = et;
    }

    public User getUser() {
        return u;
    }

    public ErrorType getErrorType() {
        return et;
    }

    public void setUser(User u) {
        this.u = u;
    }

    public void setErrorType(ErrorType et) {
        this.et = et;
    }
}
