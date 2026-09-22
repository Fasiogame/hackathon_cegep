package cal.info.service;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ControlleurEtudiant implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String typeRequest = exchange.getRequestMethod();
        switch (typeRequest) {
            case "POST":
                ajouterEtudiant(exchange);
                break;
            case "GET":
                afficherEtudiant();
                break;
            case "PUT":
                modifierEtudiant();
                break;
            case "PATCH":
                modifierEtudiant();
                break;
            case "DELETE":
                supprimerEtudiant();
                break;
            default:
                exchange.sendResponseHeaders(404, 0);
                break;
        }

//        System.out.println("Received request Etudiant");
//        String response = "Bienvenue sur la page ETUDIANT !";
//        exchange.sendResponseHeaders(200, response.length());
//        OutputStream os = exchange.getResponseBody();
//        os.write(response.getBytes());
//        os.close();
    }

    private void afficherEtudiant() {
        System.out.println("GET ETUDIANT !");

    }

    private void ajouterEtudiant(HttpExchange exchange) throws IOException {
        System.out.println("POST ETUDIANT !");
        InputStream input = exchange.getRequestBody();
        String requete = new String(input.readAllBytes(),  "UTF-8");
        input.close();
        System.out.println(requete);

        String response = "Etudiant ajouté";
        byte[] octetsReponse = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, octetsReponse.length);
        OutputStream output = exchange.getResponseBody();
        output.write(octetsReponse);
        output.close();
    }

    private void modifierEtudiant(){
        System.out.println("Modifier ETUDIANT !");
    }
    private void supprimerEtudiant(){
        System.out.println("Supprimer ETUDIANT !");
    }
}
