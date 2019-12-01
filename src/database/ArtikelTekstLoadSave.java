/**
 * @author Hans Pauwels
 *
 */

package database;

import database.strategy.LoadSaveStrategy;
import model.Artikel;
import model.DomainException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ArtikelTekstLoadSave extends TekstLoadSaveTemplate {
    private HashMap<String, Artikel> artikelen;

    public ArtikelTekstLoadSave() {
        this.artikelen = new HashMap<>();
    }



    @Override
    public List<Artikel> load() {
       List<Artikel> artikelen = new ArrayList<>();
       File artikelFile = new File("src/bestanden/artikel.txt");
    try(Scanner scanner = new Scanner(artikelFile)) {
        while (scanner.hasNextLine()) {
            Scanner scannerLine = new Scanner(scanner.nextLine());
            scannerLine.useDelimiter(",");
            int artikelNr = Integer.parseInt(scannerLine.next());
            String artikelNaam = scannerLine.next();
            String artikelGroep = scannerLine.next();
            double prijs = Double.parseDouble(scannerLine.next());
            int voorraad = Integer.parseInt(scannerLine.next());
            Artikel a = new Artikel(artikelNr, artikelNaam, artikelGroep, prijs, voorraad);
            artikelen.add(a);

        }
    } catch (IOException e) {
        throw new DbException(e.getMessage());
    }

        return artikelen;
    }

    @Override
    public void save(List<Artikel> artikelen){
        try {
            FileOutputStream fs = new FileOutputStream("src/bestanden/artikel.txt");
            OutputStreamWriter os = new OutputStreamWriter(fs, StandardCharsets.UTF_8);
            List<String> strList = new ArrayList<>();
            for (Artikel a : artikelen) {
                strList.add(a.toString());
            }

            String artikelStrs = String.join("\n", strList);
            os.write(artikelStrs);
            os.close();
        } catch (IOException e) {
            throw new DomainException(e.getMessage());
        }
    }
}
