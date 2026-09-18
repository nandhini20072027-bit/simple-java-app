import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", (HttpExchange exchange) -> {
            String response = """
                    <html>
                    <head>
                        <title>Java CI/CD Application</title>
                    </head>
                    <body>
                        <h1>Hello from Java CI/CD Pipeline!</h1>
                        <p>Jenkins + Docker + Kubernetes is working successfully.</p>
                    </body>
                    </html>
                    """;

            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, response.getBytes().length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });

        server.start();

        System.out.println("Java CI/CD Application Started on port 8080...");

        while (true) {
            Thread.sleep(5000);
        }
    }
}