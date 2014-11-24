package alpacafarmerserver.method;

import alpacafarmerserver.user.UserManager;

/**
 * Created by ndh13 on 23/11/14.
 */
public class LogInUser extends Method<Boolean> {

    private final UserManager userManager;
    private final String username;
    private final String password;

    public LogInUser(UserManager userManager, String username, String password) {
        this.userManager = userManager;
        this.username = username;
        this.password = password;
    }

    @Override
    public Boolean call() {
        if (userManager.getUser(username) == null) {
            return false;
        } else if (!userManager.correctPassword(username, password)) {
            return false;
        }
        return true;
    }
}
