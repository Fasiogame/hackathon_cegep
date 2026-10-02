package cal.info.modele;

import java.time.LocalDate;

public class Hackathon {
    private int idHackathon;
    private String nomHackathon;
    private LocalDate dateHackathon;
    private String lieuHackathon;

    public Hackathon() {}

    public Hackathon(String nomHackathon, LocalDate dateHackathon, String lieuHackathon){
        setNom(nomHackathon);
        setDate(dateHackathon);
        setLieu(lieuHackathon);
    }

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

    @Override
    public String toString() {
        return "Hackathon{" +
                "nomHackathon='" + nomHackathon + '\n' +
                ", dateHackathon=" + dateHackathon +
                ", lieuHackathon='" + lieuHackathon + '\n' +
                '}';
    }
}
