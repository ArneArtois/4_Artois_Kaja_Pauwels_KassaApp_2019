package database;

import database.strategy.ArtikelDBStrategy;
import database.strategy.LoadSaveStrategy;
import jxl.write.WriteException;
import model.Artikel;

import java.io.IOException;
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

    public List<Artikel> getCart(){
        return this.db.cart();
    }

    public void remove(int code) {
        if(code < 0) {
            throw new IllegalArgumentException("The given code cannot be less than zero");
        }

        this.db.remove(code);
    }

    public Artikel get(int code) {
        if(code < 0) {
            throw new IllegalArgumentException("The given code cannot be less than zero");
        }
        Artikel artikel = this.db.search(code);
        db.cart().add(artikel);
        return artikel;

    }

    public void save(List<Artikel> artikelen) {
        if (artikelen == null) {
            throw new IllegalArgumentException("The given list of articles cannot be empty");
        }
        try {
            this.strategy.save(artikelen);
        } catch (WriteException | IOException e) {
            throw new DbException(e.getMessage());
        }
    }
}
