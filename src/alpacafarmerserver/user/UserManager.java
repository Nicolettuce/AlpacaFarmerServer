package alpacafarmerserver.user;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ndh13 on 23/11/14.
 */
public class UserManager {

    private Map<String, User> users;
    private Map<String, User> sessionTokens;

    public UserManager() {
        users = new HashMap<>();
        sessionTokens = new HashMap<>();
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

    public String createSessionToken(String username) {
        String sessionToken = System.currentTimeMillis() + username;
        sessionTokens.put(sessionToken, getUser(username));
        return sessionToken;
    }

    public User removeSessionToken(String sessionToken) {
        return sessionTokens.remove(sessionToken);
    }
}
