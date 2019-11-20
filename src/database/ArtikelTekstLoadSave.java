package database;

import model.Artikel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArtikelTekstLoadSave {
    private HashMap<String, Artikel> artikelen;

    public ArtikelTekstLoadSave() {
        this.artikelen = new HashMap<>();
    }

    public List<Artikel> load() {
       List<Artikel> artikelen = new ArrayList<>();
       File artikelFile = new File("bestanden/artikel.txt");

       return artikelen;
    }
}
