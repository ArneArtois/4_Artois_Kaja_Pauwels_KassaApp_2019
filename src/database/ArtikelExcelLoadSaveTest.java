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

public class ArtikelExcelLoadSaveTest {
    public ArtikelExcelLoadSaveTest() {
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
}