package alpacafarmerserver.user;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ndh13 on 23/11/14.
 */
public class UserManager {

    private Map<String, User> users;

    public UserManager() {
        users = new HashMap<>();
    }


    public User getUser(String username) {
        return users.get(username);
    }

    public boolean correctPassword(String username, String password) {
        return getUser(username).correctPassword(password);
    }

    public void addNewUser(String username, String password) {
        users.put(username, new User(username, password));
    }
}
