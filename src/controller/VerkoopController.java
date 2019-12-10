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
    private ArtikelDBContext context;
    private VerkoopModel verkoopModel;

    private VerkoopPane verkoopPane;
    private KlantViewPane klantViewPane;

    private KassaView kassaView;
    private KlantView klantView;

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
    public void codeEnter(int code) {
        Artikel a = context.get(code);
        if(a != null) {
            verkoopModel.addArtikel(a);
            verkoopPane.artikelWelGevonden();
        } else {
            verkoopPane.artikelNietGevonden();
        }
    }

    public void codeRemove(int code) {
        Artikel a = context.get(code);
        if(a != null && verkoopModel.getArtikelen().contains(a)) {
            verkoopModel.removeArtikel(a);
            verkoopPane.artikelWelGevonden();
        } else {
            verkoopPane.artikelNietGevonden();
        }
    }



    @Override
    public void update(Artikel a, List<Artikel> artikelen) {
        double totalePrijs = verkoopModel.getTotalePrijs();
        verkoopPane.updateDisplay(totalePrijs, artikelen);
        klantViewPane.updateDisplay(totalePrijs, artikelen);
    }
}
