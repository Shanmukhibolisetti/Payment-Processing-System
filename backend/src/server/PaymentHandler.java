package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import service.PaymentService;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class PaymentHandler implements HttpHandler {

    private final PaymentService paymentService = new PaymentService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");

        if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        String body = new String(
                exchange.getRequestBody().readAllBytes(),
                StandardCharsets.UTF_8
        );

        try {
            double amount = extractAmount(body);
            String mode = extractMode(body);

            String message = paymentService.processPayment(amount, mode);

            String jsonResponse =
                    "{ \"status\": \"SUCCESS\", \"message\": \"" + message + "\" }";

            sendResponse(exchange, 200, jsonResponse);

        } catch (Exception e) {
            String errorResponse =
                    "{ \"status\": \"FAILED\", \"message\": \"" + e.getMessage() + "\" }";

            sendResponse(exchange, 400, errorResponse);
        }
    }

    private double extractAmount(String body) {
        String value = body.split("\"amount\"\\s*:\\s*")[1].split(",")[0];
        return Double.parseDouble(value);
    }

    private String extractMode(String body) {
        return body.split("\"method\"\\s*:\\s*\"")[1].split("\"")[0];
    }

    private void sendResponse(HttpExchange exchange, int status, String response)
            throws IOException {

        exchange.getResponseHeaders().add("Content-Type", "application/json");
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

        exchange.sendResponseHeaders(status, bytes.length);

        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}
