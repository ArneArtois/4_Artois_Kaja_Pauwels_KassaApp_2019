package model.strategy;

import model.Artikel;

import java.util.List;

public abstract class KortingStrategy {
    public abstract double berekenKorting(List<Artikel> artikelen, String groep, double kortingsPercentage, double minKortingBedrag);
    double round(double getal){
        getal = Math.round(getal*100);
        getal = getal /100;
        return getal;
    }
}
