import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        String rootDir = "WebGLBuilds";

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/", (HttpExchange exchange) -> {
            try {
                // จัดการ Path และตรวจสอบไฟล์
                String requestPath = exchange.getRequestURI().getPath();
                if (requestPath.endsWith("/")) {
                    requestPath += "index.html";
                }

                String filePath = rootDir + requestPath;
                Path path = Paths.get(filePath);

                if (Files.exists(path) && !Files.isDirectory(path)) {
                    byte[] response = Files.readAllBytes(path);

                    String mimeType = Files.probeContentType(path);
                    if (mimeType == null) mimeType = "application/octet-stream";
                    exchange.getResponseHeaders().set("Content-Type", mimeType);

                    // Content-Encoding สำหรับไฟล์ .br
                    if (filePath.endsWith(".br")) {
                        exchange.getResponseHeaders().set("Content-Encoding", "br");
                    }

                    exchange.sendResponseHeaders(200, response.length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response);
                    os.close();
                } else {
                    String notFound = "404 Not Found: " + requestPath;
                    exchange.sendResponseHeaders(404, notFound.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(notFound.getBytes());
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                String error = "500 Internal Server Error: " + e.getMessage();
                exchange.sendResponseHeaders(500, error.length());
                OutputStream os = exchange.getResponseBody();
                os.write(error.getBytes());
                os.close();
            }
        });
        
        System.out.println("Backend Server started at http://localhost:8081");
        System.out.println("WebGL Server started at http://localhost:" + port);
        server.start();
    }
}