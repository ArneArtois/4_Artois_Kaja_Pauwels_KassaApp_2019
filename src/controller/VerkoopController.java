package controller;

import database.ArtikelDBContext;
import database.strategy.ArtikelDBStrategy;
import javafx.scene.control.TableView;
import model.Artikel;
import model.Verkoop;
import model.observer.Observer;
import view.panels.VerkoopPane;

import java.util.List;

public class VerkoopController{
    private Verkoop verkoop;
    private VerkoopPane verkoopPane;
    private double totalePrijs = 0;
    private ArtikelDBContext context;

    public VerkoopController(Verkoop verkoopModel, ArtikelDBContext context) {
        this.verkoop = verkoopModel;
        this.context = context;
    }

    public void setVerkoopPane(VerkoopPane verkoopPane) {
        if(verkoopPane == null) {
            throw new IllegalArgumentException("VerkoopPane mag niet leeg zijn");
        }

        this.verkoopPane = verkoopPane;
    }

    public double getTotalePrijs() {
        return this.totalePrijs;
    }

    public void codeEnter(int code) {
        Artikel a = context.get(code);
        verkoop.addArtikel(context.get(code));
    }
}
