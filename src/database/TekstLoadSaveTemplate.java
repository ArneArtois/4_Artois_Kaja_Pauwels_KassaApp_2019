package database;

import database.strategy.ArtikelDBStrategy;
import model.Artikel;

import java.util.List;

public abstract class TekstLoadSaveTemplate implements ArtikelDBStrategy {
    public abstract List<Artikel> load();
    public abstract void save(List<Artikel> artikelen);
}
