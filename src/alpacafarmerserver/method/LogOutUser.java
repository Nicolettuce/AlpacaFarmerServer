package alpacafarmerserver.method;

import alpacafarmerserver.user.UserManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * Created by ndh13 on 29/11/14.
 */
public class LogOutUser extends Method<JsonObject> {

    private final UserManager userManager;
    private final String sessionToken;

    public LogOutUser(UserManager userManager, String sessionToken) {
        this.userManager = userManager;
        this.sessionToken = sessionToken;
    }

    @Override
    public JsonObject call() {
        JsonObject response = new JsonObject();
        if (userManager.removeSessionToken(sessionToken) == null) {
            response.add("Success", new JsonPrimitive(false));
            response.add("Error", new JsonPrimitive(5));
        } else {
            response.add("Success", new JsonPrimitive(true));
        }
        return response;
    }

}
