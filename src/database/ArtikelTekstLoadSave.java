/**
 * @author Hans Pauwels
 *
 */

package database;

import model.Artikel;
import model.DomainException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
        scanner.useDelimiter(",");
        while(scanner.hasNextLine()) {
            Scanner scannerLine = new Scanner(scanner.nextLine());
            int artikelNr = scannerLine.nextInt();
            String artikelNaam = scannerLine.next();
            String artikelGroep = scannerLine.next();
            double prijs = scannerLine.nextDouble();
            System.out.println(artikelNr);
        }
//        while(scanner.hasNext()) {
//            int artikelNr = scanner.nextInt();
//            String artikelNaam = scanner.next();
//            String artikelGroep = scanner.next();
//            double prijs = scanner.nextDouble();
//            System.out.println(artikelNr);
//            String voorraadStr = scanner.next();
//            int voorraad = Integer.parseInt(voorraadStr.replace())
//
//
//            Artikel a = new Artikel(artikelNr,artikelNaam,artikelGroep, prijs, voorraad);
//            artikelen.add(a);
//
//        }

        return artikelen;
    }
}
