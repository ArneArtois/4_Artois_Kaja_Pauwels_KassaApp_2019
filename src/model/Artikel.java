package model;

public class Artikel {
    private String code;
    private String omschrijving;
    private String artikelGroep;
    private double verkoopprijs;
    private final double btw = 0.06;

    public Artikel(String code, String omschrijving, String artikelGroep, double prijs) {
        setCode(code);
        setOmschrijving(omschrijving);
        setArtikelGroep(artikelGroep);
        setVerkoopprijs(prijs);
    }

    public void setCode(String code) {
        if(code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Code cannot be empty");
        }
        this.code = code;
    }

    public void setOmschrijving(String omschrijving) {
        if(omschrijving == null || omschrijving.trim().isEmpty()) {
            throw new IllegalArgumentException("Omschrijving cannot be empty");
        }
        this.omschrijving = omschrijving;
    }

    public void setArtikelGroep(String artikelGroep) {
        if(artikelGroep == null || artikelGroep.trim().isEmpty()) {
            throw new IllegalArgumentException("Artikelgroep cannot be empty");
        }
        this.artikelGroep = artikelGroep;
    }

    public void setVerkoopprijs(double verkoopprijs) {
        if(verkoopprijs < 0) {
            throw new IllegalArgumentException("Verkoopprijs cannot be less than zero");
        }
        this.verkoopprijs = verkoopprijs;
    }

    public String getCode() {
        return code;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public String getArtikelGroep() {
        return artikelGroep;
    }

    public double getVerkoopprijs() {
        return verkoopprijs;
    }
}
