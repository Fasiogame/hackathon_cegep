package cal.info;

import java.time.LocalDate;

public class hackathon {
    private String nomHackathon;
    private LocalDate dateHackathon;
    private String lieuHackathon;

    public hackathon(String nomHackathon, LocalDate dateHackathon, String lieuHackathon){
        this.dateHackathon = dateHackathon;
        this.nomHackathon = nomHackathon;
        this.lieuHackathon = lieuHackathon;
    }

    public String obtenirNom() {
        return nomHackathon;
    }

    public LocalDate obtenirDate() {
        return dateHackathon;
    }

    public String obtenirLieu() {
        return lieuHackathon;
    }
}
