package cal.info.service;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ControlleurHackathon implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
//        System.out.println("Received request hackathon");
//        String response = "Bienvenue sur la page HACKATHON !";
//        exchange.sendResponseHeaders(200, response.length());
//        OutputStream os = exchange.getResponseBody();
//        os.write(response.getBytes());
//        os.close();

        String typeRequest = exchange.getRequestMethod();
        switch (typeRequest) {
            case "POST":
                ajouterHackathon(exchange);
                break;
            case "GET":
                listeHackathons(exchange);
                break;
            case "PUT":
                modifierHackathon(exchange);
                break;
            case "DELETE":
                supprimerHackathon(exchange);
                break;
            default:
                exchange.sendResponseHeaders(404, 0);
                break;
        }
    }

    private void listeHackathons(HttpExchange exchange) throws IOException{
        System.out.println("GET HACKATHON !");
        String response = "Hackathon affiché";
        byte[] octetsReponse = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, octetsReponse.length);
        OutputStream output = exchange.getResponseBody();
        output.write(octetsReponse);
        output.close();
    }

    private void ajouterHackathon(HttpExchange exchange) throws IOException {
        System.out.println("POST HACKATHON !");
        InputStream input = exchange.getRequestBody();
        String requete = new String(input.readAllBytes(),  "UTF-8");
        input.close();
        System.out.println(requete);

        String response = "Hackathon ajouté";
        byte[] octetsReponse = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, octetsReponse.length);
        OutputStream output = exchange.getResponseBody();
        output.write(octetsReponse);
        output.close();
    }

    private void modifierHackathon(HttpExchange exchange) throws IOException{
        System.out.println("PUT/PATCH HACKATHON !");
        String response = "Hackathon modifié";
        byte[] octetsReponse = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, octetsReponse.length);
        OutputStream output = exchange.getResponseBody();
        output.write(octetsReponse);
        output.close();
    }

    private void supprimerHackathon(HttpExchange exchange) throws IOException{
        System.out.println("DELETE HACKATHON !");
        String response = "Hackathon supprimé";
        byte[] octetsReponse = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, octetsReponse.length);
        OutputStream output = exchange.getResponseBody();
        output.write(octetsReponse);
        output.close();
    }
}
