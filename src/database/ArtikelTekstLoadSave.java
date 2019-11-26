/**
 * @author Hans Pauwels
 *
 */

package database;

import model.Artikel;
import model.DomainException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ArtikelTekstLoadSave {
    private HashMap<String, Artikel> artikelen;

    public ArtikelTekstLoadSave() {
        this.artikelen = new HashMap<>();
    }

    public List<Artikel> load() {
       List<Artikel> artikelen = new ArrayList<>();
       InputStream artikelFile = ArtikelTekstLoadSave.class.getResourceAsStream("/bestanden/artikel.txt");
        Scanner scanner = new Scanner(artikelFile);

        while(scanner.hasNextLine()) {
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
        scanner.close();

        return artikelen;
    }

    public void save(List<Artikel> artikelen) throws IOException {
        FileOutputStream fs = new FileOutputStream("src/bestanden/artikel.txt");
        OutputStreamWriter os = new OutputStreamWriter(fs, StandardCharsets.UTF_8);
        List<String> strList = new ArrayList<>();
        for(Artikel a : artikelen) {
            strList.add(a.toString());
        }

        String artikelStrs = String.join("\n", strList);
        os.write(artikelStrs);
        os.close();
    }
}
