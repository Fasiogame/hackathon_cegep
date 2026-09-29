package cal.info.modele;
import java.util.ArrayList;
import java.util.List;

public class Etudiant {
    private String nomEtudiant;
    private int ageEtudiant;
    private double noteEtudiant;
    private List<Hackathon> preferences = new ArrayList<Hackathon>(5);

    public Etudiant() {
    }

    public Etudiant(String nomEtudiant, int ageEtudiant, double noteEtudiant) {
        //this.nomEtudiant = nomEtudiant;
        //this.ageEtudiant = ageEtudiant;
        //this.noteEtudiant = noteEtudiant;
        setNom(nomEtudiant);
        setAge(ageEtudiant);
        setNote(noteEtudiant);
    }

    public void setNom(String nomEtudiant) {
        if (nomEtudiant.isBlank()) {
            throw new IllegalArgumentException("Le nom ne peux pas être vide!");
        }
        this.nomEtudiant = nomEtudiant;
    }

    public void setAge(int ageEtudiant) {
        this.ageEtudiant = ageEtudiant > 0 ? ageEtudiant : 0;
    }

    public void setNote(double noteEtudiant) {
        if (noteEtudiant >= 0.0 && noteEtudiant <= 100.0) {
            this.noteEtudiant = noteEtudiant;
        } else {
        this.noteEtudiant = 0;
        }
    }

    public String getNom(){
        return this.nomEtudiant;
    }
    public int getAge(){
        return this.ageEtudiant;
    }
    public double getNote(){
        return this.noteEtudiant;
    }
    public void ajouterPreferenceHackathon(Hackathon hackathon) {
        preferences.add(hackathon);
    }

    public Hackathon getPreferences(){
       return preferences.get(0);
    }

    public void afficherPreferences(){
        for (Hackathon hackathon : preferences) {
            System.out.println(hackathon.getNom());
        }
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "nomEtudiant='" + nomEtudiant + '\n' +
                ", ageEtudiant=" + ageEtudiant +
                ", note='" + noteEtudiant + '\n' +
                '}';
    }
}
