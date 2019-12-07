package model;

import model.observer.Observer;
import model.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class VerkoopModel implements Subject {
    private List<Artikel> artikelen;
    private List<Observer> observers;

    public VerkoopModel() {
        this.artikelen = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public List<Artikel> getArtikelen() {
        return this.artikelen;
    }

    public void addArtikel(Artikel a) {
        if(a == null) {
            throw new IllegalArgumentException("Artikel mag niet leeg zijn");
        }
        this.artikelen.add(a);
        notifyObservers(a, this.artikelen);
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
    public void notifyObservers(Artikel a, List<Artikel> artikelen) {
        for(Observer o : this.observers) {
            o.update(a, artikelen);
        }
    }
}
