package database;

import database.strategy.ArtikelDBStrategy;
import database.strategy.LoadSaveStrategy;
import model.Artikel;

import java.util.List;

public abstract class TekstLoadSaveTemplate implements LoadSaveStrategy {
    public abstract List<Artikel> load();
    public abstract void save(List<Artikel> artikelen);
}
