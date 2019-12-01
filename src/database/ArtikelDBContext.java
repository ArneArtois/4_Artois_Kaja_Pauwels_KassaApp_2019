package database;

import database.strategy.ArtikelDBStrategy;
import database.strategy.LoadSaveStrategy;
import model.Artikel;

import java.util.HashMap;
import java.util.List;
//Facade class to use in application
public class ArtikelDBContext {
    private LoadSaveStrategy strategy;
    private ArtikelDBStrategy db;
    //private HashMap<String, Artikel> artikelen;

    public ArtikelDBContext() {
        //this.artikelen = new HashMap<>();
    }

    public void setLoadSaveStrategy(LoadSaveStrategy strategy) {
        if(strategy == null) {
            throw new IllegalArgumentException("The given strategy cannot be empty");
        }

        this.strategy = strategy;
    }

    public void setDBStrategy(ArtikelDBStrategy strategy) {
        if(strategy == null) {
            throw new IllegalArgumentException("The given strategy cannot be empty");
        }

        this.db = strategy;
    }

   /* public HashMap<String, Artikel> getArtikelen() {
        return this.artikelen;
    }*/

    public void add(Artikel a) {
        if(a == null) {
            throw new IllegalArgumentException("The given Artikel cannot be empty");
        }
        this.db.add(a);
    }

    public List<Artikel> load() {
        return this.strategy.load();
    }

    public void remove(int code) {
        if(code < 0) {
            throw new IllegalArgumentException("The given code cannot be less than zero");
        }

        this.db.remove(code);
    }

    public void save(List<Artikel> artikelen) {
        if (artikelen == null) {
            throw new IllegalArgumentException("The given list of articles cannot be empty");
        }
        this.strategy.save(artikelen);
    }
}
