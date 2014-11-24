package alpacafarmerserver;

import alpacafarmerserver.server.AlpacaFarmerServer;

import java.io.IOException;

/**
 * Created by ndh13 on 23/11/14.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        AlpacaFarmerServer server = new AlpacaFarmerServer(7777);
        server.run();
    }

}
