package alpacafarmerserver.server;

import alpacafarmerserver.method.CreateNewUser;
import alpacafarmerserver.method.LogInUser;
import alpacafarmerserver.method.LogOutUser;
import alpacafarmerserver.user.UserManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by ndh13 on 23/11/14.
 */
public class AlpacaFarmerServer extends Thread {
    private ServerSocket serverSocket;
    private JsonObject request;

    private UserManager userManager;

    public AlpacaFarmerServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(100000);
        userManager = new UserManager();
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                System.out.println("Just connected to " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());
                request = new Gson().fromJson(in.readUTF(), JsonObject.class);
                System.out.println(request);
                String response = doMethod(request.get("Method").getAsString());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF(response);
                server.close();
            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (EOFException e) {

            } catch (IOException e) {
                break;
            }
        }
    }

    private String doMethod(String method) {
        JsonObject response;
        switch (method) {
            case "createNewUser":
                response = new CreateNewUser(userManager, request.get("Username").getAsString(), request.get("Password").getAsString()).call();
                return response.toString();
            case "logInUser":
                response = new LogInUser(userManager, request.get("Username").getAsString(), request.get("Password").getAsString()).call();
                return response.toString();
            case "logOutUser":
                response = new LogOutUser(userManager, request.get("Session_token").getAsString()).call();
                return response.toString();

        }
        return null;
    }

}
