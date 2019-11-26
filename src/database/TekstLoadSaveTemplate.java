package database;

import model.Artikel;

import java.util.List;

public interface TekstLoadSaveTemplate {
    List<Artikel> load();
    void save(List<Artikel> artikelen);
}
