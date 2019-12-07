package controller;

import database.ArtikelDBContext;
import database.strategy.ArtikelDBStrategy;
import javafx.scene.control.TableView;
import model.Artikel;
import model.Model;
import model.Verkoop;
import model.observer.Observer;
import view.KlantViewPane;
import view.panels.VerkoopPane;

import java.util.List;

public class VerkoopController implements Observer {
    private Verkoop verkoop;
    private VerkoopPane verkoopPane;
    private KlantViewPane klantView;
    private double totalePrijs = 0;
    private ArtikelDBContext context;

    public VerkoopController(Verkoop verkoopModel, ArtikelDBContext context) {
        this.verkoop = verkoopModel;
        verkoopModel.registerObserver(this);
        this.context = context;
    }

    public void setVerkoopPane(VerkoopPane verkoopPane) {
        if(verkoopPane == null) {
            throw new IllegalArgumentException("VerkoopPane mag niet leeg zijn");
        }

        this.verkoopPane = verkoopPane;
    }

    public void setKlantView(KlantViewPane klantView) {
        if(klantView == null) {
            throw new IllegalArgumentException("KlantView mag niet leeg zijn");
        }
        this.klantView = klantView;
    }

    public double getTotalePrijs() {
        return this.totalePrijs;
    }

    public void codeEnter(int code) {
        Artikel a = context.get(code);
        verkoop.addArtikel(context.get(code));
    }

    @Override
    public void update(Artikel a, List<Artikel> artikelen) {
        this.totalePrijs += a.getVerkoopprijs();
        verkoopPane.updateDisplay(this.totalePrijs, artikelen);
        klantView.updateDisplay(this.totalePrijs, artikelen);
    }
}
