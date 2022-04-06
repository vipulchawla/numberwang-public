import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Random;

// to run this:
// cd java/src/main
// javac Server.java
// java Server
// and then, in chrome or curl:
// http://localhost:8080/number=6
// where 6 would be a guess

public class Server {
    static Integer randomValue;

    public static Integer randomNum() {
        int min = 0;
        int max = 9;
        Random random = new Random();

        randomValue = random.nextInt(max + min) + min;
        System.out.println("Random Number is " + randomValue);
        return randomValue;
    }


    public static void main(String[] args) throws IOException {
        randomNum();
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        HttpContext context = server.createContext("/");
        context.setHandler(Server::handleRequest);
        server.start();
    }

    private static void handleRequest(HttpExchange httpExchange) throws IOException {
        String found = "0";
        String uri = httpExchange.getRequestURI().toString();
        // expecting /number=
        if (uri.startsWith("/number=")) {
            found = uri.substring("/number=".length());
        }
        String response = "Not Numberwang";
        if (randomValue.toString().equals(found)) {
            response = "thatsNumberwang";
        }
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
