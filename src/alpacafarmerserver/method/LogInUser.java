package alpacafarmerserver.method;

import alpacafarmerserver.user.UserManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * Created by ndh13 on 23/11/14.
 */
public class LogInUser extends Method<JsonObject> {

    private final UserManager userManager;
    private final String username;
    private final String password;

    public LogInUser(UserManager userManager, String username, String password) {
        this.userManager = userManager;
        this.username = username;
        this.password = password;
    }

    @Override
    public JsonObject call() {
        JsonObject response = new JsonObject();
        if (userManager.getUser(username) == null) {
            response.add("Success", new JsonPrimitive(false));
            response.add("Error", new JsonPrimitive(3));
            return response;
        } else if (!userManager.correctPassword(username, password)) {
            response.add("Success", new JsonPrimitive(false));
            response.add("Error", new JsonPrimitive(4));
            return response;
        }
        String sessionToken = userManager.createSessionToken(username);
        response.add("Success", new JsonPrimitive(true));
        response.add("Session_token", new JsonPrimitive(sessionToken));
        return response;
    }
}
