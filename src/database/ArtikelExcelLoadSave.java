package database;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import model.Artikel;

public class ArtikelExcelLoadSave {
    public ArtikelExcelLoadSave() {
    }

    public void write(File file, ArrayList<ArrayList<String>> args)
            throws BiffException, IOException, RowsExceededException, WriteException {

        WritableWorkbook workbook = Workbook.createWorkbook(file);
        workbook.createSheet("sheet1", 0);
        WritableSheet sheet = workbook.getSheet(0);
        for (int i = 0; i < args.size(); i++) {
            ArrayList<String> parameters = args.get(i);
            for (int j = 0; j < parameters.size(); j++) {
                Label label = new Label(j, i, parameters.get(j));
                sheet.addCell(label);
            }
        }
        workbook.write();
        workbook.close();

    }

    public ArrayList<Artikel> read(File file)
            throws BiffException, IOException {

        Workbook workbook = Workbook.getWorkbook(file);
        Sheet sheet = workbook.getSheet(0);
        int row = 0;

        ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
        while (row < sheet.getRows()) {
            int column = 0;
            ArrayList<String> rowinfo = new ArrayList<String>();
            while (column < sheet.getColumns()) {
                Cell cell = sheet.getCell(column, row);
                String information = cell.getContents();
                System.out.println(information);
                rowinfo.add(information);
                column++;
            }
            info.add(rowinfo);
            row++;
        }
        workbook.close();
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

    }

}