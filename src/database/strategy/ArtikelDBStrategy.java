package database.strategy;

import model.Artikel;

import java.util.HashMap;
import java.util.List;

public interface ArtikelDBStrategy {
    //HashMap<Integer, Artikel> load();
    //void save(List<Artikel> artikelen);
    void add(Artikel a);
    void remove(int code);
    void setStrategy(LoadSaveStrategy strategy);
    List<Artikel> getAll();
}
