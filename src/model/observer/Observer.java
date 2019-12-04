package model.observer;

import model.Artikel;

import java.util.List;

public interface Observer {
    void update(Artikel a, List<Artikel> artikelen);
}
