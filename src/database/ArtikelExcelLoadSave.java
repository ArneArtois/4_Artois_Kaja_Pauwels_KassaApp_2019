package database;

import com.sun.rowset.internal.Row;
import database.strategy.LoadSaveStrategy;
import excel.ExcelPlugin;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import model.Artikel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArtikelExcelLoadSave implements LoadSaveStrategy {
    private ExcelPlugin plugin;

    public ArtikelExcelLoadSave() {
        this.plugin = new ExcelPlugin();
    }

    @Override
    public List<Artikel> load() {
        File file = new File("src/bestanden/artikel.xls");
        try {
            ArrayList<ArrayList<String>> info = plugin.read(file);
            ArrayList<Artikel> artikelArrayList = new ArrayList<>();
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

    @Override
    public void save(List<Artikel> artikelen) throws WriteException, IOException {
        if (artikelen == null) {
            throw new IllegalArgumentException("The given list cannot be empty");
        }
        File file = new File("src/bestanden/artikel.xls");
        WritableWorkbook workbook = Workbook.createWorkbook(file);
        workbook.createSheet("artikel", 0);
        WritableSheet sheet = workbook.getSheet(0);
        /*for(int i = 0; i < args.size(); i++){
            ArrayList<String> parameters = args.get(i);
            for(int j = 0; j < parameters.size(); j++){
                Label label = new Label(j, i, parameters.get(j));
                sheet.addCell(label);
            }
        }
        workbook.write();
        workbook.close();
    }*/
        for (int i = 0; i < artikelen.size(); i++) {
            Label productId = new Label(0, i, Integer.toString(artikelen.get(i).getCode()));
            sheet.addCell(productId);
            Label productOmschr = new Label(1,i,artikelen.get(i).getOmschrijving());
            sheet.addCell(productOmschr);
            Label productGr = new Label(2, i, artikelen.get(i).getArtikelGroep());
            sheet.addCell(productGr);
            Label prijs = new Label(3, i, Double.toString(artikelen.get(i).getVerkoopprijs()));
            sheet.addCell(prijs);
            Label voorraad = new Label(4,i,Integer.toString(artikelen.get(i).getInVoorraad()));
            sheet.addCell(voorraad);
            /*Label productNaam = new Label(i + 1, i + 1, artikelen.get(i).getOmschrijving());
            sheet.addCell(productNaam);*/

        }

        workbook.write();
        workbook.close();
    }
}
