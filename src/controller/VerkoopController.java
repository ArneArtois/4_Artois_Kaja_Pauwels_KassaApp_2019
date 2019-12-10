package controller;

import database.ArtikelDBContext;
import database.strategy.ArtikelDBStrategy;
import model.Artikel;
import model.VerkoopModel;
import model.observer.Observer;
import view.KassaView;
import view.KlantView;
import view.KlantViewPane;
import view.panels.VerkoopPane;

import java.io.*;
import java.util.List;

public class VerkoopController implements Observer {
    private VerkoopModel verkoopModel;
    private VerkoopPane verkoopPane;
    private KlantView klantView;
    private KlantViewPane klantViewPane;
    private double totalePrijs = 0;
    private ArtikelDBContext context;
    private KassaView kassaView;

    public VerkoopController() {

        this.verkoopModel = new VerkoopModel();
        verkoopModel.registerObserver(this);
        this.context = new ArtikelDBContext();

        this.klantView = new KlantView(this);
        this.verkoopPane = new VerkoopPane(this);
        this.kassaView = new KassaView(this, context);
    }

    public void slaVerkoopOp() {
        try(FileOutputStream fileOut = new FileOutputStream("src/bestanden/verkoop.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this.verkoopModel);
            //Testing
            System.out.println("Verkoop on hold gezet");
            verkoopModel.volgendeVerkoop();
            this.verkoopModel = new VerkoopModel();
            this.totalePrijs = 0;
            this.verkoopModel.registerObserver(this);
            //verkoopModel.volgendeVerkoop();
        } catch (IOException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    public void leesVerkoop() {
        VerkoopModel verkoop;
        try(FileInputStream fileIn = new FileInputStream("src/bestanden/verkoop.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn)) {
            verkoop = (VerkoopModel) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new ControllerException(e.getMessage());
        }

        this.verkoopModel.laadVerkoop(verkoop);
    }

    public void setVerkoopPane(VerkoopPane verkoopPane) {
        if(verkoopPane == null) {
            throw new IllegalArgumentException("VerkoopPane mag niet leeg zijn");
        }

        this.verkoopPane = verkoopPane;
    }

    public VerkoopPane getVerkoopPane() {
        return verkoopPane;
    }

    public void setKlantViewPane(KlantViewPane klantViewPane) {
        if(klantViewPane == null) {
            throw new IllegalArgumentException("KlantView mag niet leeg zijn");
        }
        this.klantViewPane = klantViewPane;
    }

    public double getTotalePrijs() {
        return this.totalePrijs;
    }

    public void codeEnter(int code) {
        Artikel a = context.get(code);
        if(a != null) {
            this.totalePrijs += a.getVerkoopprijs();
            verkoopModel.addArtikel(a);
            verkoopPane.artikelWelGevonden();
        } else {
            verkoopPane.artikelNietGevonden();
        }
    }

    public void codeEnter2(int code) {
        Artikel a = context.get(code);
        if(a != null && verkoopModel.getArtikelen().contains(a)) {
            this.totalePrijs -= a.getVerkoopprijs();
            verkoopModel.removeArtikel(a);
            verkoopPane.artikelWelGevonden();
        } else {
            verkoopPane.artikelNietGevonden();
        }
    }



    @Override
    public void update(Artikel a, List<Artikel> artikelen) {
        if(a != null) {
            verkoopPane.updateDisplay(this.totalePrijs, artikelen);
            klantViewPane.updateDisplay(this.totalePrijs, artikelen);
        } else if(a == null && artikelen.isEmpty()) {
            this.totalePrijs = 0;
            verkoopPane.updateDisplay(this.totalePrijs, artikelen);
            klantViewPane.updateDisplay(this.totalePrijs, artikelen);
        } else {
            for(Artikel artikel : artikelen) {
                this.totalePrijs += artikel.getVerkoopprijs();
            }
            verkoopPane.updateDisplay(this.totalePrijs, artikelen);
            klantViewPane.updateDisplay(this.totalePrijs, artikelen);
        }
    }
}
