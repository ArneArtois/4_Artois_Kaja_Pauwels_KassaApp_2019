package database;

import model.Artikel;

import java.util.List;

public abstract class TekstLoadSaveTemplate {
    abstract List<Artikel> load();
    abstract void save(List<Artikel> artikelen);
}
