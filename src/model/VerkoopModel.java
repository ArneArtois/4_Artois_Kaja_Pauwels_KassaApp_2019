package model;

import controller.VerkoopController;
import model.observer.Observer;
import model.observer.Subject;
import model.verkoop.*;

import java.util.ArrayList;
import java.util.List;

public class VerkoopModel implements Subject, java.io.Serializable {
    private List<Artikel> artikelen;
    private transient List<Observer> observers;
    private State beeindigdState;
    private State nietBetaaldState;
    private State onHoldState;
    private State newVerkoopState;
    private State betaaldState;
    private State genoegGeldState;
    private State nietGenoegGeldState;

    private State currentState;

    public VerkoopModel(VerkoopController verkoopController) {
        newVerkoopState = new NewVerkoop(this);
        this.currentState = newVerkoopState;
        beeindigdState = new Beeindigd(this);
        nietBetaaldState = new NietBetaald(this);
        onHoldState = new OnHoldVerkoop(this);
        betaaldState = new Betaald(this);
        genoegGeldState = new GenoegGeld(this);
        nietGenoegGeldState = new NietGenoegGeld(this);
        this.artikelen = new ArrayList<>();
        this.observers = new ArrayList<>();

        this.registerObserver(verkoopController);
    }

    public List<Artikel> getArtikelen() {
        return this.artikelen;
    }

    public Artikel getArtikel(int code) {
        Artikel a = null;
        if (code < 0) {
            throw new IllegalArgumentException("Code mag niet negatief zijn");
        }

        for(Artikel art : artikelen) {
            if(art.getCode() == code) {
                a = art;
            }
        }
        return a;
    }

    public State getGenoegGeldState() {
        return genoegGeldState;
    }

    public State getNietGenoegGeldState() {
        return nietGenoegGeldState;
    }

    public void setCurrentState(State state) {
        this.currentState = state;
    }

    public void beeindigVerkoop() {
        this.currentState.eindigVerkoop();
    }


   /* public void betaalVerkoop() {
        this.currentState.betaal(this.getArtikelen());
    }*/

    public void annuleerVerkoop() {
        this.currentState.annuleer();
    }



    public State getBeeindigdState() {
        return beeindigdState;
    }

    public State getNietBetaaldState() {
        return nietBetaaldState;
    }

    public State getOnHoldState() {
        return onHoldState;
    }

    public State getNewVerkoopState() {
        return newVerkoopState;
    }

    public State getBetaaldState() {
        return betaaldState;
    }

    public State getCurrentState() {
        return currentState;
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

    public int getTotaalAantal() {
        int aantal = 0;
        for(Artikel a : this.artikelen) {
            aantal += a.getAantalPerKeer();
        }

        return aantal;
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
