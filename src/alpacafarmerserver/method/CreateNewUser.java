package alpacafarmerserver.method;

import alpacafarmerserver.Util;
import alpacafarmerserver.user.UserManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * Created by ndh13 on 23/11/14.
 */
public class CreateNewUser extends Method<JsonObject> {

    private final UserManager userManager;
    private final String username;
    private final String password;

    public CreateNewUser(UserManager userManager, String username, String password) {

        this.userManager = userManager;
        this.username = username;
        this.password = password;
    }

    @Override
    public JsonObject call() {
        JsonObject response = new JsonObject();
        if (!Util.isAlphaNumeric(username) || !Util.isSuitableLength(username)) {
            response.add("Success", new JsonPrimitive(false));
            response.add("Error", new JsonPrimitive(0));
            return response;
        } else if (!Util.isAlphaNumeric(password) || !Util.isSuitableLength(password)) {
            response.add("Success", new JsonPrimitive(false));
            response.add("Error", new JsonPrimitive(1));
            return response;
        } else if (userManager.getUser(username) != null) {
            response.add("Success", new JsonPrimitive(false));
            response.add("Error", new JsonPrimitive(2));
            return response;
        }
        userManager.addNewUser(username, password);
        response.add("Success", new JsonPrimitive(true));
        return response;
    }
}
