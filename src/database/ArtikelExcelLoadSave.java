package database;

import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import model.Artikel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ArtikelExcelLoadSave extends ExcelPlugin {
    ExcelPlugin excelPlugin;
    public ArtikelExcelLoadSave(){
        excelPlugin = new ExcelPlugin();
    }
    public ArrayList<Artikel> load(){
        File file = new File("src/bestanden/artikel.xls");
        try {
            ArrayList<ArrayList<String>> info = excelPlugin.read(file);
            ArrayList<Artikel> artikelArrayList= new ArrayList<>();
            for (ArrayList<String> strings : info) {
                int code = Integer.parseInt(strings.get(0));
                String omschrijving = strings.get(1);
                String artikelGroep = strings.get(2);
                double prijs = Double.parseDouble(strings.get(3));
                int aantal = Integer.parseInt(strings.get(4));
                Artikel artikel = new Artikel(code, omschrijving, artikelGroep, prijs, aantal);
                artikelArrayList.add(artikel);
            }
            return artikelArrayList;
        } catch (BiffException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
