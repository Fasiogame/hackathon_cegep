package cal.info.service;

import cal.info.modele.Etudiant;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ControlleurEtudiant implements HttpHandler {
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String typeRequest = exchange.getRequestMethod();
        switch (typeRequest) {
            case "POST":
                ajouterEtudiant(exchange);
                break;
            case "GET":
                afficherEtudiant(exchange);
                break;
            case "PUT":
                modifierEtudiant(exchange);
                break;
            case "PATCH":
                modifierEtudiant(exchange);
                break;
            case "DELETE":
                supprimerEtudiant(exchange);
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

    private void afficherEtudiant(HttpExchange exchange) throws IOException {
        System.out.println("GET ETUDIANT 2!");
        try{
            //OBJET DEMO
            Etudiant etudiantTest = new Etudiant("Test", 29, 100.0);
            //OBJET -> JSON
            byte[] etudiantConverti = mapper.writeValueAsBytes(etudiantTest);
            System.out.println(etudiantConverti);

//            System.out.println("Etudiant convertit taille : " + etudiantConverti.length);
//            System.out.println("Etudiant convertit : " + etudiantConverti);
//            System.out.println("Etudiant json : " + etudiantjson);

            //RESPONSE
            exchange.sendResponseHeaders(200, etudiantConverti.length);
            OutputStream output = exchange.getResponseBody();
            output.write(etudiantConverti);
            output.close();
        } catch (Exception e) {
            System.out.println(e);
        }


//        System.out.println("GET ETUDIANT !");
//        String response = "Etudiant affiché";
//        byte[] octetsReponse = response.getBytes(StandardCharsets.UTF_8);
//        exchange.sendResponseHeaders(200, octetsReponse.length);
//        output.write(octetsReponse);
//        output.close();
    }

    private void ajouterEtudiant(HttpExchange exchange) throws IOException {
        System.out.println("POST ETUDIANT !");
//        InputStream input = exchange.getRequestBody();
//        String requete = new String(input.readAllBytes(), StandardCharsets.UTF_8);
//        input.close();
//        System.out.println(requete);

        //JSON -> OBJET
        InputStream input = exchange.getRequestBody();
        Etudiant etudiant = mapper.readValue(input.readAllBytes(), Etudiant.class);
        System.out.println(etudiant.toString());

        //REPONSE
        String response = "Etudiant ajouté";
        byte[] octetsReponse = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, octetsReponse.length);
        OutputStream output = exchange.getResponseBody();
        output.write(octetsReponse);
        output.close();
    }

    private void modifierEtudiant(HttpExchange exchange) throws IOException {
        System.out.println("PUT/PATCH ETUDIANT !");
        String response = "Etudiant modifié";
        byte[] octetsReponse = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, octetsReponse.length);
        OutputStream output = exchange.getResponseBody();
        output.write(octetsReponse);
        output.close();
    }
    private void supprimerEtudiant(HttpExchange exchange) throws IOException{
        System.out.println("DELETE ETUDIANT !");
        String response = "Etudiant supprimé";
        byte[] octetsReponse = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, octetsReponse.length);
        OutputStream output = exchange.getResponseBody();
        output.write(octetsReponse);
        output.close();
    }
}
