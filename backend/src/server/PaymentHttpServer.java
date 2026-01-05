import com.sun.net.httpserver.HttpServer;
import java.io.*;
import java.net.InetSocketAddress;
import server.PaymentHandler;

public class PaymentHttpServer {
    public static void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/pay", new PaymentHandler());

        server.setExecutor(null);
        server.start();

        System.out.println("Server started at http://localhost:8080");

    }
}