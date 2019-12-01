package database.strategy;

import model.Artikel;

import java.util.HashMap;
import java.util.List;

public interface LoadSaveStrategy {
    List<Artikel> load();
    void save(List<Artikel> artikelen);
}
