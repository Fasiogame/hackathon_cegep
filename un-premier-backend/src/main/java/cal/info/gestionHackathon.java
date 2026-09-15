package cal.info;
import java.util.HashMap;

public class gestionHackathon {
    //private hackathon[] listeHackathons;
    private HashMap<String, hackathon> listeHackathons;

    public gestionHackathon() {
        //listeHackathons = new hackathon[99];
        listeHackathons = new HashMap<String, hackathon>();
    }

    public void ajouterHackathon(hackathon hackathon) {
        listeHackathons.put(hackathon.obtenirNom(), hackathon);
    }

    public void modifierHackathon(String nom) {
        hackathon temp = listeHackathons.get(nom);
    }

    public void supprimerHackathon(String nom) {
        listeHackathons.remove(nom);
    }

    public void afficherHackathon() {}
    public void creerEtudiants(etudiant[] etudiants) {}
    public equipe formerEquipe(etudiant[] etudiants, String nomEquipe) {
        return null;
    }
}
