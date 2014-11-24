package alpacafarmerserver.user;

/**
 * Created by ndh13 on 23/11/14.
 */
public class User {

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean correctPassword(String password) {
        return this.password.equals(password);
    }
}
