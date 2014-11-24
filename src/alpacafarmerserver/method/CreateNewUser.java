package alpacafarmerserver.method;

import alpacafarmerserver.user.UserManager;

/**
 * Created by ndh13 on 23/11/14.
 */
public class CreateNewUser extends Method<Boolean> {

    private final UserManager userManager;
    private final String username;
    private final String password;

    public CreateNewUser(UserManager userManager, String username, String password) {

        this.userManager = userManager;
        this.username = username;
        this.password = password;
    }

    @Override
    public Boolean call() {
        if (userManager.getUser(username) != null) {
            return false;
        }
        userManager.addNewUser(username, password);
        return true;
    }
}
