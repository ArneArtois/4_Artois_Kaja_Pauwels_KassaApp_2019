package database;

import database.strategy.ArtikelDBStrategy;
import model.Artikel;
import model.DomainException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArtikelDBInMemory {
    private HashMap<Integer, Artikel> artikelen;

    public ArtikelDBInMemory() {
        this.artikelen = loadArtikelen();
        //loadArtikelen();
    }

    public HashMap<Integer, Artikel> loadArtikelen() {
        HashMap<Integer, Artikel> artikelen = new HashMap<>();
        ArtikelTekstLoadSave loader = new ArtikelTekstLoadSave();
        List<Artikel> artikelList = loader.load();
        for (Artikel a : artikelList) {
            artikelen.put(a.getCode(), a);
        }
        return artikelen;
    }

    public Artikel get(int code){
        if(code < 0){
            throw new DomainException("Code mag niet negatief zijn");
        }
        return artikelen.get(code);
    }

    public List<Artikel> getAll(){
        return new ArrayList<Artikel>(artikelen.values());
    }

    public void add(Artikel artikel){
        if(artikel == null){
            throw new DbException("Geef een artikel");
        }
        if (artikelen.containsKey(artikel.getCode())) {
            throw new DbException("Artikel bestaat al");
        }
        artikelen.put(artikel.getCode(), artikel);
    }

    public void update(Artikel artikel){
        if(artikel == null){
            throw new DbException("Geef een artikel");
        }
        if(!artikelen.containsKey(artikel.getCode())){
            throw new DbException("Geen artikel gevonden");
        }
        artikelen.put(artikel.getCode(), artikel);
    }

    public void delete(int code){
        if(code < 0){
            throw new DomainException("Code mag niet negatief zijn");
        }
        artikelen.remove(code);
    }

    public int getNumberOfArtikelen() {
        return artikelen.size();
    }
}
