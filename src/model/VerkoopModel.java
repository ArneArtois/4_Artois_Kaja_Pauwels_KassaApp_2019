package model;

import controller.VerkoopController;
import model.observer.Observer;
import model.observer.Subject;
import model.verkoop.*;
import model.verkoop.oldStates.*;

import java.util.ArrayList;
import java.util.List;

public class VerkoopModel implements Subject, java.io.Serializable {
    private List<Artikel> artikelen;
    private transient List<Observer> observers;
    /*private State beeindigdState;
    private State nietBetaaldState;
    private State onHoldState;
    private State newVerkoopState;
    private State betaaldState;
    private State genoegGeldState;
    private State nietGenoegGeldState;*/
    private State nieuweVerkoopState;
    private State onHold;
    private State eindeState;
    private State afgeslotenState;
    private VerkoopController verkoopController;

    private State currentState;

    public VerkoopModel(VerkoopController verkoopController) {
        this.verkoopController = verkoopController;
        this.nieuweVerkoopState = new NieuweVerkoop(this);
        //newVerkoopState = new NewVerkoop(this);
        this.currentState = nieuweVerkoopState;
        this.onHold = new OnHold(this);
        this.eindeState = new Einde(this);
        this.afgeslotenState = new Afgesloten(this);
        /*beeindigdState = new Beeindigd(this);
        nietBetaaldState = new NietBetaald(this);
        onHoldState = new OnHoldVerkoop(this);
        betaaldState = new Betaald(this);
        genoegGeldState = new GenoegGeld(this);
        nietGenoegGeldState = new NietGenoegGeld(this);*/
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
    public void clearArtikelen(){artikelen.clear();
    }

    public State getNieuweVerkoopState() {
        return nieuweVerkoopState;
    }

    public State getOnHold() {
        return onHold;
    }

    public State getEindeState() {
        return eindeState;
    }

    public State getAfgeslotenState() {
        return afgeslotenState;
    }

    /*public State getGenoegGeldState() {
        return genoegGeldState;
    }

    public State getNietGenoegGeldState() {
        return nietGenoegGeldState;
    }*/

    public void setCurrentState(State state) {
        this.currentState = state;
    }

    public void startNieuweVerkoop() {
        this.currentState.reset();
    }

    public void zetOnHoldTerug() {
        this.currentState.zetNietOnHold();
    }

    public void sluitVerkoopAf() {
        this.currentState.sluitAf();
    }

    /*public void zetVerkoopOnHold() {
        this.verkoopController.slaVerkoopOp();
    }*/

    public void zetVerkoopTerug() {
        this.verkoopController.leesVerkoop();
    }


   /* public void betaalVerkoop() {
        this.currentState.betaal(this.getArtikelen());
    }*/

    public void annuleerVerkoop() {
        this.currentState.annuleer();
    }



   /* public State getBeeindigdState() {
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
    }*/

    public State getCurrentState() {
        return currentState;
    }

    public void volgendeVerkoop() {
        //this.artikelen.clear();
        this.artikelen = new ArrayList<>();
        notifyObservers(null, this.artikelen, 0, false);
    }

    public void laadVerkoop(VerkoopModel verkoop) {
        if(verkoop == null) {
            throw new IllegalArgumentException("Verkoop mag niet null zijn");
        }

        this.artikelen = verkoop.getArtikelen();
        notifyObservers(null, this.artikelen, 0, false);
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
        notifyObservers(a, this.artikelen, 0, false);
    }

    public void removeArtikel(Artikel a) {
        if(a == null) {
            throw new IllegalArgumentException("Artikel mag niet leeg zijn");
        }
        this.artikelen.remove(a);
        notifyObservers(a, this.artikelen, 0, verkoopController.getVerkoopPane().getAfgesloten());
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
            o.update(a, artikelen, korting, afgesloten);
        }
    }
}
