package model;


import controller.VerkoopController;
import database.ArtikelDBContext;
import database.ArtikelDBInMemory;
import model.observer.Observer;
import model.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class KlantModel implements Subject {
    private List<Artikel> artikelen;
    private List<Observer> observers;
    private VerkoopController verkoopController;

    public KlantModel(VerkoopController verkoopController){
        this.verkoopController = verkoopController;
        this.artikelen = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public List<Artikel> getArtikelsInMemory(){
        return this.artikelen;
    }

    @Override
    public void registerObserver(Observer o) {
        if(o == null) {
            throw new IllegalArgumentException("Observer mag niet leeg zijn");
        }
        this.observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if(o == null) {
            throw new IllegalArgumentException("Observer mag niet leeg zijn");
        }

        this.observers.remove(o);
    }

    @Override
    public void notifyObservers(Artikel a, List<Artikel> artikelen, double korting, boolean afgesloten) {
        for(Observer o : this.observers) {
            o.update(a, artikelen, korting, verkoopController.getVerkoopPane().getAfgesloten());
        }
    }
    public void addArtikel(Artikel artikel){
        artikelen.add(artikel);
        notifyObservers(artikel, artikelen, 0, verkoopController.getVerkoopPane().getAfgesloten());
    }
}
