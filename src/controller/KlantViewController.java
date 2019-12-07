package controller;

import database.ArtikelDBContext;
import model.Artikel;
import model.Verkoop;
import model.observer.Observer;
import view.KlantView;
import view.KlantViewPane;

import java.util.List;

public class KlantViewController implements Observer {
    private ArtikelDBContext context;
    private KlantViewPane klantViewPane;
    private Verkoop verkoop;
    private double totalePrijs = 0;

    public KlantViewController(Verkoop verkoop, ArtikelDBContext context) {
        this.context = context;
        this.verkoop = verkoop;
        verkoop.registerObserver(this);
    }

    public void add(int code) {
        if (code < 0) {
            throw new IllegalArgumentException("Code mag niet negatief zijn");
        }

        Artikel artikel = context.get(code);
        verkoop.addArtikel(artikel);
    }

    public KlantViewPane getKlantView() {
        return klantViewPane;
    }

    public void setKlantView(KlantViewPane klantView) {
        this.klantViewPane = klantView;
    }

    public ArtikelDBContext getContext() {
        return context;
    }

    public void setContext(ArtikelDBContext context) {
        this.context = context;
    }

    public double getTotalePrijs() {
        return totalePrijs;
    }

    public void setTotalePrijs(double totalePrijs) {
        this.totalePrijs = totalePrijs;
    }

    @Override
    public void update(Artikel a, List<Artikel> artikelen) {
        this.totalePrijs += a.getVerkoopprijs();
        klantViewPane.updateDisplay(totalePrijs, artikelen);
    }
}
