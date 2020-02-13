import InterfaceServer.InterfaceServer;
import MzFoodDelivery.MzFoodDelivery;

public class Main {
    public static void main(String[] args) {
        final String RESTAURANTS_URI = "http://138.197.181.131:8080/restaurants";
        final int PORT = 8080;
        InterfaceServer interfaceServer = new InterfaceServer();
        interfaceServer.start(RESTAURANTS_URI, PORT);
    }
}
