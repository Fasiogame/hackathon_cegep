package cal.info.service;

import cal.info.modele.Etudiant;
import cal.info.modele.Hackathon;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ControlleurEtudiant implements HttpHandler {
    ObjectMapper mapper = new ObjectMapper();
    HashMap<Integer, Etudiant> listeEtudiantsTemp = new HashMap<Integer, Etudiant>();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String typeRequest = exchange.getRequestMethod();
        switch (typeRequest) {
            case "POST":
                ajouterEtudiant(exchange);
                break;
            case "GET":
                if (exchange.getRequestURI().getQuery() == null) {
                    afficherEtudiant(exchange);
                }
                afficherEtudiantsHackathon(exchange);
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
    }

    private void afficherEtudiant(HttpExchange exchange) throws IOException {
        System.out.println("GET ETUDIANT 2!");
        try{
            //OBJET DEMO
            Etudiant etudiantTest = new Etudiant("Test", 29, 100.0, 123);
            //OBJET -> JSON=
            String listeTemp = "";
            for (int i : listeEtudiantsTemp.keySet()) {
                listeTemp += listeEtudiantsTemp.get(i).toString() + "\n";
            }
            byte[] etudiantConverti = mapper.writeValueAsBytes(listeTemp);

            //RESPONSE
            ecrireReponse(exchange, etudiantConverti);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void ecrireReponse(HttpExchange exchange, byte[] etudiantConverti) throws IOException {
        exchange.sendResponseHeaders(200, etudiantConverti.length);
        OutputStream output = exchange.getResponseBody();
        output.write(etudiantConverti);
        output.close();
    }

    public void afficherEtudiantsHackathon(HttpExchange exchange) throws IOException {
        String[] cleValeur = getCleValeurURI(exchange);
        int id = Integer.parseInt(cleValeur[1]);
        System.out.println("MATRICULE " + id);
        String etudiant ;
         List<Etudiant> listeEtudiantPref = new ArrayList<>();
        for (int i :  listeEtudiantsTemp.keySet()) {
            if(listeEtudiantsTemp.get(i).getPreferencesHackathons().contains(id)) {
                listeEtudiantPref.add(listeEtudiantsTemp.get(i));
            }
        }

        byte[] listeConvertie = mapper.writeValueAsBytes(listeEtudiantPref);

        ecrireReponse(exchange, listeConvertie);
    }

    public void ajouterPreferencesHackathon(HttpExchange exchange) throws IOException {
        Etudiant
    }

    private static String[] getCleValeurURI(HttpExchange exchange) {
        String input = exchange.getRequestURI().getQuery();
        String[] cleValeur = input.split("=");
        for (int i = 0; i < cleValeur.length; i++) {
            System.out.print(cleValeur[i] + " ");
        }
        return cleValeur;
    }

    private void ajouterEtudiant(HttpExchange exchange) throws IOException {
        Etudiant etudiant = lireRequest("POST ETUDIANT !", exchange);

        listeEtudiantsTemp.put(etudiant.getMatriculeEtudiant(), etudiant);

        //REPONSE
        String response = "Etudiant ajouté";
        byte[] octetsReponse = response.getBytes(StandardCharsets.UTF_8);
        ecrireReponse(exchange, octetsReponse);
    }

    private void modifierEtudiant(HttpExchange exchange) throws IOException {
        Etudiant etudiant = lireRequest("PUT/PATCH ETUDIANT !", exchange);

        listeEtudiantsTemp.replace(etudiant.getMatriculeEtudiant(),  etudiant);

        //REPONSE
        String response = "Etudiant modifié";
        byte[] octetsReponse = response.getBytes(StandardCharsets.UTF_8);
        ecrireReponse(exchange, octetsReponse);
    }

    private Etudiant lireRequest(String x, HttpExchange exchange) throws IOException {
        System.out.println(x);
        InputStream input = exchange.getRequestBody();
        Etudiant etudiant = mapper.readValue(input.readAllBytes(), Etudiant.class);
        System.out.println(etudiant.toString());
        return etudiant;
    }

    private void supprimerEtudiant(HttpExchange exchange) throws IOException{
        Etudiant etudiant = lireRequest("DELETE ETUDIANT !", exchange);
        listeEtudiantsTemp.remove(etudiant.getMatriculeEtudiant());

        String response = "Etudiant supprimé";
        byte[] octetsReponse = response.getBytes(StandardCharsets.UTF_8);
        ecrireReponse(exchange, octetsReponse);
    }

    public void ajouterPreferences (HttpExchange exchange) throws IOException {
        System.out.println("POST PREFERENCE !");


    }
}
