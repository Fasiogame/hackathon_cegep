package cal.info.modele;
import java.security.PrivateKey;
import java.util.HashMap;

public class Equipe {
    private Etudiant[] listeMembres;
    private String nomEquipe;
    private HashMap<String, Hackathon> listeHackathons; // Version avec Hash à faire
    private int etudiantCount;
    public Equipe() {
        listeMembres = new Etudiant[4]; //Exemple
        etudiantCount = 0;
    }

    public void ajouterMembres(Etudiant etudiants) {
        if (etudiantCount < listeMembres.length) {
            listeMembres[etudiantCount] = etudiants;
            etudiantCount++;
        }

    }

    public void afficherMembres() {
        for  (Etudiant etudiant : listeMembres) {
            System.out.println(etudiant.obtenirNom());
        }
    }
}
