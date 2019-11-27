package database;

import database.strategy.ArtikelDBStrategy;
import model.Artikel;

import java.util.HashMap;
import java.util.List;

public class ArtikelDBContext {
    private ArtikelDBStrategy strategy;
    private HashMap<String, Artikel> artikelen;

    public ArtikelDBContext() {
        this.artikelen = new HashMap<>();
    }

    public void setStrategy(ArtikelDBStrategy strategy) {
        if(strategy == null) {
            throw new IllegalArgumentException("The given strategy cannot be empty");
        }

        this.strategy = strategy;
    }

    public HashMap<String, Artikel> getArtikelen() {
        return this.artikelen;
    }

    public List<Artikel> load() {
        return this.strategy.load();
    }

    public void save(List<Artikel> artikelen) {
        if (artikelen == null) {
            throw new IllegalArgumentException("The given list of articles cannot be empty");
        }
        this.strategy.save(artikelen);
    }
}
