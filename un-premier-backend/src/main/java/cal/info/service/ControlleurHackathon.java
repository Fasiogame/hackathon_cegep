package cal.info.service;

import cal.info.modele.Hackathon;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashMap;

public class ControlleurHackathon implements HttpHandler {
    ObjectMapper mapper = new ObjectMapper();
    HashMap<String, Hackathon> listeHackathonTemp = new HashMap<String, Hackathon>();
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
            case "PATCH":
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
//        Hackathon hackathonTest = new Hackathon("Test", LocalDate.now(), "lieu");
//        byte[] hackathonTestJson = mapper.writeValueAsString(hackathonTest).getBytes(StandardCharsets.UTF_8);

        String listeTemp = "";
        for (String i : listeHackathonTemp.keySet()) {
            listeTemp += listeHackathonTemp.get(i).toString() + "\n";
        }
        byte[] hackathonConverti = mapper.writeValueAsBytes(listeTemp);

        //REPONSE
        exchange.sendResponseHeaders(200, hackathonConverti.length);
        OutputStream output = exchange.getResponseBody();
        output.write(hackathonConverti);
        output.close();
    }

    private void ajouterHackathon(HttpExchange exchange) throws IOException {
        System.out.println("POST HACKATHON !");
        InputStream input = exchange.getRequestBody();
        Hackathon hackathon = mapper.readValue(input.readAllBytes(), Hackathon.class);
        input.close();
        System.out.println(hackathon.toString());
        listeHackathonTemp.put(hackathon.getNom(), hackathon);

        //REPONSE
        String response = "Hackathon ajouté";
        byte[] octetsReponse = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, octetsReponse.length);
        OutputStream output = exchange.getResponseBody();
        output.write(octetsReponse);
        output.close();
    }

    private void modifierHackathon(HttpExchange exchange) throws IOException{
        System.out.println("PUT/PATCH HACKATHON !");
        InputStream input = exchange.getRequestBody();
        Hackathon hackathon = mapper.readValue(input.readAllBytes(), Hackathon.class);
        input.close();
        System.out.println(hackathon.toString());
        listeHackathonTemp.replace(hackathon.getNom(), hackathon);


        //REPONSE
        String response = "Hackathon modifié";
        byte[] octetsReponse = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, octetsReponse.length);
        OutputStream output = exchange.getResponseBody();
        output.write(octetsReponse);
        output.close();
    }

    private void supprimerHackathon(HttpExchange exchange) throws IOException{
        System.out.println("DELETE HACKATHON !");
        String input = exchange.getRequestURI().getQuery();

        String response = "Hackathon supprimé" + " " + input;
        byte[] octetsReponse = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, octetsReponse.length);
        OutputStream output = exchange.getResponseBody();
        output.write(octetsReponse);
        output.close();
    }
}
