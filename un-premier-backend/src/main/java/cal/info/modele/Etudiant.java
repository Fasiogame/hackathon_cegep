package cal.info.modele;
import java.util.ArrayList;
import java.util.List;

public class Etudiant {
    private int matriculeEtudiant;
    private String nomEtudiant;
    private int ageEtudiant;
    private double noteEtudiant;
    private List<Hackathon> preferencesHackathons;


    public Etudiant() {
    }

    public Etudiant(String nomEtudiant, int ageEtudiant, double noteEtudiant, int matriculeEtudiant) {
        setNom(nomEtudiant);
        setAge(ageEtudiant);
        setNote(noteEtudiant);
        setMatricule(matriculeEtudiant);
        this.preferencesHackathons = new ArrayList<>();
    }

    public void setMatricule(int matriculeEtudiant) {this.matriculeEtudiant = matriculeEtudiant;}

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

    public int getMatriculeEtudiant() {return matriculeEtudiant;}
    public String getNom(){
        return this.nomEtudiant;
    }
    public int getAge(){
        return this.ageEtudiant;
    }
    public double getNote(){
        return this.noteEtudiant;
    }

    public List<Hackathon> getPreferencesHackathons() {
        return this.preferencesHackathons;
    }
    public void ajouterUnePreference(Hackathon hackathon){
        this.preferencesHackathons.add(hackathon);
    }

    @Override
    public String toString() {
        return "{" +
                "nomEtudiant='" + nomEtudiant + '\n' +
                ", ageEtudiant=" + ageEtudiant +
                ", note='" + noteEtudiant + '\n' +
                ", matricule='" + matriculeEtudiant + '\n' +
                '}';
    }
}
