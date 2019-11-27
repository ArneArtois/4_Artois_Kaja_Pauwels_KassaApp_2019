package view;


import database.ArtikelDBInMemory;
import database.ArtikelTekstLoadSave;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.Artikel;
import model.ComparatorByOmschrijving;
import view.panels.ProductOverviewPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class KassaMainPane extends BorderPane {
	public KassaMainPane(){
	    TabPane tabPane = new TabPane(); 	    
        Tab kassaTab = new Tab("Kassa");
        ProductOverviewPane productOverviewPane = new ProductOverviewPane();
        Tab artikelTab = new Tab("Artikelen",productOverviewPane);
        Tab instellingTab = new Tab("Instellingen");
        Tab logTab = new Tab("Log");
        //ArtikelTekstLoadSave artikelTekstLoadSave = new ArtikelTekstLoadSave();
        //List<Artikel> artikelen = artikelTekstLoadSave.load();
        ArtikelDBInMemory db = new ArtikelDBInMemory();
        //HashMap<Integer, Artikel> artikelenMap = db.loadArtikelen();
        List<Artikel> artikelen = db.getAll();
        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
        Collections.sort(artikelen, new ComparatorByOmschrijving());
        //artikelen.sort(new ComparatorByOmschrijving());
        int i = 1;
        for(Artikel artikel : artikelen) {
            productOverviewPane.add(new Label(Integer.toString(artikel.getCode())), 0, i+1, 1, 1);
            productOverviewPane.add(new Label(artikel.getOmschrijving()), 1, i+1, 1, 1);
            productOverviewPane.add(new Label(artikel.getArtikelGroep()), 2, i+1, 1, 1);
            productOverviewPane.add(new Label(Double.toString(artikel.getVerkoopprijs())), 3, i+1, 1, 1);
            productOverviewPane.add(new Label(Integer.toString(artikel.getInVoorraad())), 4, i+1, 1, 1);
            i++;
        }

	    this.setCenter(tabPane);
	}
}
