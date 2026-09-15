package cal.info;
import java.util.List;

public class etudiant {
    private String nomEtudiant;
    private int ageEtudiant;
    private double noteEtudiant;
    private List<hackathon> preferences;

    public etudiant(String nomEtudiant, int ageEtudiant, double noteEtudiant) {
        //this.nomEtudiant = nomEtudiant;
        //this.ageEtudiant = ageEtudiant;
        //this.noteEtudiant = noteEtudiant;
        setNomEtudiant(nomEtudiant);
        setAgeEtudiant(ageEtudiant);
        setNoteEtudiant(noteEtudiant);
    }

    public void setNomEtudiant(String nomEtudiant) {
        if (!nomEtudiant.isBlank()) {
            throw new IllegalArgumentException("Le nom ne peux pas être vide!");
        }
        this.nomEtudiant = nomEtudiant;
    }

    public void setAgeEtudiant(int ageEtudiant) {
        this.ageEtudiant = ageEtudiant > 0 ? ageEtudiant : 0;
    }

    public void setNoteEtudiant(double noteEtudiant) {
        if (noteEtudiant >= 0 || noteEtudiant <= 100) {
            this.noteEtudiant = noteEtudiant;
        }
        this.noteEtudiant = 0;
    }

    public String obtenirNom(){
        return this.nomEtudiant;
    }
    public int obtenirAge(){
        return this.ageEtudiant;
    }
    public double obtenirNote(){
        return this.noteEtudiant;
    }
    public void ajouterPreferenceHackathon(hackathon hackathon) {
        preferences.add(hackathon);
    }

    public hackathon getPreferences(){
       return preferences.get(0);
    }

    public void afficherPreferences(){
        for (hackathon hackathon : preferences) {
            System.out.println(hackathon);
        }
    }
}
