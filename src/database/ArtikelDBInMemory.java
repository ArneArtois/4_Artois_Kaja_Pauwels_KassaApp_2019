package database;

import model.Artikel;
import model.DomainException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArtikelDBInMemory {
    private HashMap<String, Artikel> artikelen;

    public ArtikelDBInMemory() {

    }

    public Artikel get(String code){
        if(code == null){
            throw new DomainException("Geef een code");
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
        artikelen.put(Integer.toString(artikel.getCode()), artikel);
    }

    public void update(Artikel artikel){
        if(artikel == null){
            throw new DbException("Geef een artikel");
        }
        if(!artikelen.containsKey(artikel.getCode())){
            throw new DbException("Geen artikel gevonden");
        }
        artikelen.put(Integer.toString(artikel.getCode()), artikel);
    }

    public void delete(String code){
        if(code == null){
            throw new DbException("Geef een code");
        }
        artikelen.remove(code);
    }

    public int getNumberOfArtikelen() {
        return artikelen.size();
    }
}
