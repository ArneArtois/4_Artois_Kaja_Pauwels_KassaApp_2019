package controller;

import database.ArtikelDBContext;
import model.Artikel;
import model.VerkoopModel;
import model.observer.Observer;
import view.KlantView;
import view.KlantViewPane;
import view.panels.VerkoopPane;

import java.util.List;

public class VerkoopController implements Observer {
    private VerkoopModel verkoopModel;
    private VerkoopPane verkoopPane;
    private KlantView klantView;
    private KlantViewPane klantViewPane;
    private double totalePrijs = 0;
    private ArtikelDBContext context;

    public VerkoopController(ArtikelDBContext context) {
        this.verkoopModel = new VerkoopModel();
        verkoopModel.registerObserver(this);
        this.context = context;
        klantView = new KlantView(this);
    }

    public void setVerkoopPane(VerkoopPane verkoopPane) {
        if(verkoopPane == null) {
            throw new IllegalArgumentException("VerkoopPane mag niet leeg zijn");
        }

        this.verkoopPane = verkoopPane;
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
        verkoopModel.addArtikel(a);
    }

    @Override
    public void update(Artikel a, List<Artikel> artikelen) {
        this.totalePrijs += a.getVerkoopprijs();
        verkoopPane.updateDisplay(this.totalePrijs, artikelen);
        klantViewPane.updateDisplay(this.totalePrijs, artikelen);
    }
}
