package model.observer;

import model.Artikel;

import java.io.Serializable;
import java.util.List;

public interface Observer extends Serializable {
    void update(Artikel a, List<Artikel> artikelen);
}
