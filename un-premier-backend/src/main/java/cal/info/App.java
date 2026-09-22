package cal.info;

import cal.info.modele.Equipe;
import cal.info.modele.Etudiant;
import cal.info.modele.Hackathon;
import cal.info.service.GestionHackathon;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App 
{
//    public static void main(String[] args ){
//        Hackathon hackathon = new Hackathon("J.D.I Games", LocalDate.now(), "Sherbrooke");
//        GestionHackathon gestionHackathon = new GestionHackathon();
//        Equipe equipe1 = new Equipe();
//        Etudiant etudiant1 = new Etudiant("Nathan", 19, 60);
//        Etudiant etudiant2 = new Etudiant("Julie", 20, 80);
//        Etudiant etudiant3 = new Etudiant("Mark", 21, 75);
//        Etudiant etudiant4 = new Etudiant("Joseph", 23, 30);
//        //System.out.println( "Hello World!" );
//        etudiant1.ajouterPreferenceHackathon(hackathon);
//
//        equipe1.ajouterMembres(etudiant1);
//        equipe1.ajouterMembres(etudiant2);
//        equipe1.ajouterMembres(etudiant3);
//        equipe1.ajouterMembres(etudiant4);
//        equipe1.afficherMembres();
//
//        gestionHackathon.ajouterHackathon(hackathon);
//        etudiant1.afficherPreferences();
//
//    }
public static void main(String[] args) throws IOException {
    // Création du serveur HTTP qui écoutera sur le port 8000
    HttpServer serveur = HttpServer.create(new InetSocketAddress(8000), 0);

    // Première route "/accueil" :
    serveur.createContext("/accueil", new HttpHandler() {
        @Override
        public void handle(HttpExchange echange) throws IOException {
            String response = "Bienvenue sur la page d'accueil !";
            echange.sendResponseHeaders(200, response.length());
            OutputStream os = echange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    });

    serveur.createContext("/cheminexample", new Example());

    // Démarrer le serveur
    serveur.setExecutor(null); // Créer un exécuteur par défaut
    serveur.start();

    System.out.println("Serveur démarré et en écoute sur le port 8000");
}

}
