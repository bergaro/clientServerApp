import server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = Server.getInstance();
        server.startServer();
    }
}
