package model;

import java.lang.reflect.Array;

public class Artikel {
    private int code;
    private String omschrijving;
    private String artikelGroep;
    private double verkoopprijs;
    private final double btw = 0.06;
    private int inVoorraad;
    private int aantalPerKeer;

    public Artikel(int code, String omschrijving, String artikelGroep, double prijs, int aantal) {
        setCode(code);
        setOmschrijving(omschrijving);
        setArtikelGroep(artikelGroep);
        setVerkoopprijs(prijs);
        setInVoorraad(aantal);
        setAantalPerKeer(1);
    }
    public Artikel(Artikel artikel){
        setCode(artikel.getCode());
        setOmschrijving(artikel.getOmschrijving());
        setArtikelGroep(artikel.getArtikelGroep());
        setVerkoopprijs(artikel.getVerkoopprijs());
        setInVoorraad(artikel.getInVoorraad());
    }

    public int getAantalPerKeer() {
        return aantalPerKeer;
    }

    private void setInVoorraad(int aantal) {
        if(aantal < 0) {
            throw new IllegalArgumentException("Aantal cannot be less than zero");
        }

        this.inVoorraad = aantal;
    }

    public void setCode(int code) {
        if(code < 0) {
            throw new IllegalArgumentException("Code cannot be less than zero");
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

    public int getCode() {
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

    public int getInVoorraad() {
        return inVoorraad;
    }
    public void setAantalPerKeer(int aantalPerKeer){ this.aantalPerKeer = aantalPerKeer;}

    public void addOtherArtikel(int aantalPerKeer){ this.aantalPerKeer+= aantalPerKeer;}
    @Override
    public String toString() {
        //8,artikel8,gr2,18.5,10
        return code+","+omschrijving+","+artikelGroep+","+verkoopprijs+","+inVoorraad;
    }
}
