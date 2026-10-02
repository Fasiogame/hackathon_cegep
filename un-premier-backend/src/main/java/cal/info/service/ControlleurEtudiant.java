package cal.info.service;

import cal.info.modele.Etudiant;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

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
                afficherEtudiantsMatricule(exchange);
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
            Etudiant etudiantTest = new Etudiant("Test", 29, 100.0, 123);
            //OBJET -> JSON=
            String listeTemp = "";
            for (int i : listeEtudiantsTemp.keySet()) {
                listeTemp += listeEtudiantsTemp.get(i).toString() + "\n";
            }
            byte[] etudiantConverti = mapper.writeValueAsBytes(listeTemp);

            //RESPONSE
            exchange.sendResponseHeaders(200, etudiantConverti.length);
            OutputStream output = exchange.getResponseBody();
            output.write(etudiantConverti);
            output.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void afficherEtudiantsMatricule (HttpExchange exchange) throws IOException {
        String input = exchange.getRequestURI().getQuery();
        String[] cleValeur = input.split("=");
        for (int i = 0; i < cleValeur.length; i++) {
            System.out.print(cleValeur[i] + " ");
        }
        int matricule = Integer.parseInt(cleValeur[1]);
        System.out.println("MATRICULE " + matricule);
        String etudiant ;
        if (listeEtudiantsTemp.get(matricule) == null) {
            etudiant = "";
        } else {
           etudiant =  listeEtudiantsTemp.get(matricule).toString();
        }
        byte[] cleValeurConvertie = mapper.writeValueAsBytes(etudiant);

        exchange.sendResponseHeaders(200, cleValeurConvertie.length);
        OutputStream output = exchange.getResponseBody();
        output.write(cleValeurConvertie);
        output.close();
    }

    private void ajouterEtudiant(HttpExchange exchange) throws IOException {
        System.out.println("POST ETUDIANT !");
        //JSON -> OBJET
        InputStream input = exchange.getRequestBody();
        Etudiant etudiant = mapper.readValue(input.readAllBytes(), Etudiant.class);
        System.out.println(etudiant.toString());

        listeEtudiantsTemp.put(etudiant.getMatriculeEtudiant(), etudiant);

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
        InputStream input = exchange.getRequestBody();
        Etudiant etudiant = mapper.readValue(input.readAllBytes(), Etudiant.class);
        System.out.println(etudiant.toString());

        listeEtudiantsTemp.replace(etudiant.getMatriculeEtudiant(),  etudiant);

        //REPONSE
        String response = "Etudiant modifié";
        byte[] octetsReponse = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, octetsReponse.length);
        OutputStream output = exchange.getResponseBody();
        output.write(octetsReponse);
        output.close();
    }
    private void supprimerEtudiant(HttpExchange exchange) throws IOException{
        System.out.println("DELETE ETUDIANT !");
        InputStream input = exchange.getRequestBody();
        Etudiant etudiant = mapper.readValue(input.readAllBytes(), Etudiant.class);
        System.out.println(etudiant.toString());
        listeEtudiantsTemp.remove(etudiant.getMatriculeEtudiant());

        String response = "Etudiant supprimé";
        byte[] octetsReponse = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, octetsReponse.length);
        OutputStream output = exchange.getResponseBody();
        output.write(octetsReponse);
        output.close();
    }
}
