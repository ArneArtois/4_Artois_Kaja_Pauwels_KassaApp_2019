package model.decorator;

import model.Artikel;

import java.util.List;

public interface KassaTicket {
    String print(List<Artikel> artikelen, double betaaldePrijs);
}
