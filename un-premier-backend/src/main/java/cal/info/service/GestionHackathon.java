package cal.info.service;
import cal.info.modele.Equipe;
import cal.info.modele.Etudiant;
import cal.info.modele.Hackathon;

import java.util.HashMap;
import java.util.List;

public class GestionHackathon {
    //private hackathon[] listeHackathons;
 //   private HashMap<String, Hackathon> listeHackathons;
    private List<Hackathon> listeHackathons;

    public GestionHackathon() {
        Hackathon[] listeHackathons = new Hackathon[2];
   //     listeHackathons = new HashMap<String, Hackathon>();
    }

    public void ajouterHackathon(Hackathon hackathon) {
     //   listeHackathons.put(hackathon.obtenirNom(), hackathon);
    }

    public void modifierHackathon(String nom) {
     //   listeHackathons.get(nom);
    }

    public void supprimerHackathon(String nom) {
        listeHackathons.remove(nom);
    }

    public void afficherHackathon(    ) {}
    public void creerEtudiants(Etudiant[] etudiants) {}
    public Equipe formerEquipe(Etudiant[] etudiants, String nomEquipe) {
        return null;
    }
}
