package cal.info.modele;

import java.time.LocalDate;

public class Hackathon {
    private int idHackathon;
    private String nomHackathon;
    private LocalDate dateHackathon;
    private String lieuHackathon;

    public Hackathon() {}

    public Hackathon(int idHackathon, String nomHackathon, LocalDate dateHackathon, String lieuHackathon){
        setId(idHackathon);
        setNom(nomHackathon);
        setDate(dateHackathon);
        setLieu(lieuHackathon);
    }

    public void setId(int idHackathon) { this.idHackathon = idHackathon; }

    public void setNom(String nomHackathon) {
        this.nomHackathon = nomHackathon;
    }

    public void setDate(LocalDate dateHackathon) {
        this.dateHackathon = dateHackathon;
    }

    public void setLieu(String lieuHackathon) {
        this.lieuHackathon = lieuHackathon;
    }

    public String getNom() {
        return nomHackathon;
    }

    public LocalDate getDate() {
        return dateHackathon;
    }

    public String getLieu() {
        return lieuHackathon;
    }

    public int getId() { return idHackathon; }

    @Override
    public String toString() {
        return "{" +
                "id='" + getId() + '\n' +
                "nomHackathon='" + getNom() + '\n' +
                ", dateHackathon=" + getDate() + '\n' +
                ", lieuHackathon='" + getLieu() + '\n' +
                '}';
    }
}
