package database.strategy;

import jxl.write.WriteException;
import model.Artikel;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface LoadSaveStrategy {
    List<Artikel> load();
    void save(List<Artikel> artikelen) throws WriteException, IOException;
}
