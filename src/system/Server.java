package system;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;

public class Server {

    private HttpServer server;

    private HttpHandler handler;

    private static Integer currentPort;

    private static final int START_PORT = 8100;
    private static final int MAX_PORT = 8110;

    public Server(HttpHandler handler) {
        this.handler = handler;
        createFreeServer(START_PORT);
    }

    public static Integer getCurrentPort() {
        return currentPort;
    }

    private void createFreeServer(int port) {

        try {
            currentPort = port;
            createServer(port);
        } catch (IOException e) {
            //Notification.notify("Port " + port + " is already in use");
            int nextPort = port + 1;
            if (port <= MAX_PORT) {
                createFreeServer(nextPort);
            } else {
                Notification.notify("No free port found!");
                return;
            }
        }

    }

    private void createServer(int port) throws IOException {

        com.sun.net.httpserver.HttpServer server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", this.handler);
        server.setExecutor(null); // creates a default executor
        server.start();
        this.server = server;

    }

    public void closeServer() {
        this.server.stop(0);
    }

    static class Handler {

        public static void setCors(HttpExchange httpExchange) {
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
//            if (httpExchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
//                httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
//                httpExchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
//                httpExchange.sendResponseHeaders(204, -1);
//                return;
//            }

        }

        public static String getData(HttpExchange httpExchange) {

            InputStream input = httpExchange.getRequestBody();
            StringBuilder stringBuilder = new StringBuilder();

            new BufferedReader(new InputStreamReader(input))
                    .lines()
                    .forEach( (String s) -> stringBuilder.append(s + "\n") );

            return stringBuilder.toString();

        }

        public static void response(HttpExchange httpExchange) throws IOException {

            String response = "OK";
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();

        }
    }

}