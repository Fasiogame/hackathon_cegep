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
    HashMap<Integer, Hackathon> listeHackathonTemp = new HashMap<Integer, Hackathon>();
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
                if (exchange.getRequestURI().getQuery() == null) {
                    exchange.sendResponseHeaders(400, 0);
                }
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
        for (int i : listeHackathonTemp.keySet()) {
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
        Hackathon hackathon = lireRequest("POST HACKATHON !", exchange);
        System.out.println(hackathon.toString());
        listeHackathonTemp.put(hackathon.getId(), hackathon);

        //REPONSE
        ecrireReponse("Hackathon ajouté", exchange);
    }

    private Hackathon lireRequest(String x, HttpExchange exchange) throws IOException {
        System.out.println(x);
        InputStream input = exchange.getRequestBody();
        Hackathon hackathon = mapper.readValue(input.readAllBytes(), Hackathon.class);
        input.close();
        return hackathon;
    }

    private static void ecrireReponse(String response, HttpExchange exchange) throws IOException {
        byte[] octetsReponse = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, octetsReponse.length);
        OutputStream output = exchange.getResponseBody();
        output.write(octetsReponse);
        output.close();
    }

    private void modifierHackathon(HttpExchange exchange) throws IOException{
        Hackathon hackathon = lireRequest("PUT/PATCH HACKATHON !", exchange);
        System.out.println(hackathon.toString());
        listeHackathonTemp.replace(hackathon.getId(), hackathon);


        //REPONSE
        ecrireReponse("Hackathon modifié", exchange);
    }

    private void supprimerHackathon(HttpExchange exchange) throws IOException{
        System.out.println("DELETE HACKATHON !");
        String[] cleValeur = getCleValeurURI(exchange);
        int id = Integer.parseInt(cleValeur[1]);
        System.out.println("Supprimer HACKATHON ! : " + id);
        listeHackathonTemp.remove(id);

        ecrireReponse("Hackathon supprimé", exchange);
    }
    private static String[] getCleValeurURI(HttpExchange exchange) {
        String input = exchange.getRequestURI().getQuery();
        String[] cleValeur = input.split("=");
        for (int i = 0; i < cleValeur.length; i++) {
            System.out.print(cleValeur[i] + " ");
        }
        return cleValeur;
    }

}
