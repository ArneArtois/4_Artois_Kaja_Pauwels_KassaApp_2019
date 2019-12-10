package model;

import model.observer.Observer;
import model.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class VerkoopModel implements Subject, java.io.Serializable {
    private List<Artikel> artikelen;
    private transient List<Observer> observers;

    public VerkoopModel() {
        this.artikelen = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public List<Artikel> getArtikelen() {
        return this.artikelen;
    }

    public void volgendeVerkoop() {
        this.artikelen.clear();
        notifyObservers(null, this.artikelen);
    }

    public void laadVerkoop(VerkoopModel verkoop) {
        if(verkoop == null) {
            throw new IllegalArgumentException("Verkoop mag niet null zijn");
        }

        this.artikelen = verkoop.getArtikelen();
        notifyObservers(null, this.artikelen);
    }
    public double getTotalePrijs(){
        double result = 0;
        for(Artikel artikel: artikelen){
            result += artikel.getVerkoopprijs();
        }
        result = result*100;
        result = Math.round(result);
        result = result/100;
        return result;
    }

    public void addArtikel(Artikel a) {
        if(a == null) {
            throw new IllegalArgumentException("Artikel mag niet leeg zijn");
        }
        this.artikelen.add(a);
        notifyObservers(a, this.artikelen);
    }

    public void removeArtikel(Artikel a) {
        if(a == null) {
            throw new IllegalArgumentException("Artikel mag niet leeg zijn");
        }
        this.artikelen.remove(a);
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
