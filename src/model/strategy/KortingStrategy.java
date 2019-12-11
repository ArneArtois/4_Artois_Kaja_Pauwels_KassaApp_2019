package model.strategy;

import model.Artikel;

import java.util.List;

public interface KortingStrategy {
    double berekenKorting(List<Artikel> artikelen, String groep, double kortingsPercentage, double minKortingBedrag);
}
