package database;

import database.strategy.ArtikelDBStrategy;
import database.strategy.LoadSaveStrategy;
import model.Artikel;
import model.DomainException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArtikelDBInMemory implements ArtikelDBStrategy{
    private HashMap<Integer, Artikel> artikelen;
    private LoadSaveStrategy loadSaveStrategy;
    private ArrayList<Artikel> cart = new ArrayList<>();

    public ArtikelDBInMemory() {
        //TODO replace with factory
        loadSaveStrategy = new ArtikelTekstLoadSave();
        artikelen = loadArtikelen();
    }




    public HashMap<Integer, Artikel> loadArtikelen() {
        HashMap<Integer, Artikel> artikelen = new HashMap<>();

        //ArtikelTekstLoadSave loader = new ArtikelTekstLoadSave();
        List<Artikel> artikelList = loadSaveStrategy.load();
        for (Artikel a : artikelList) {
            artikelen.put(a.getCode(), a);
        }
        return artikelen;
    }

    @Override
    public Artikel search(int code){
        if(code < 0){
            throw new DomainException("Code mag niet negatief zijn");
        }
        Artikel artikel = artikelen.get(code);
        System.out.println(artikel);
        cart.add(artikel);
        return artikel;
    }

    @Override
    public List<Artikel> cart() {
        return this.cart;
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

    public void remove(int code){
        if(code < 0){
            throw new DomainException("Code mag niet negatief zijn");
        }
        artikelen.remove(code);
    }

    @Override
    public void setStrategy(LoadSaveStrategy strategy) {
        if(strategy == null) {
            throw new IllegalArgumentException("Strategy mag niet leeg zijn");
        }

        this.loadSaveStrategy = strategy;
    }

    public int getNumberOfArtikelen() {
        return artikelen.size();
    }
}
