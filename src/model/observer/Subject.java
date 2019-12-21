package model.observer;

import model.Artikel;

import java.util.List;

public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(Artikel a, List<Artikel> artikelen, double korting, boolean afgesloten);
}
