package model.strategy;

import model.Artikel;

import java.util.List;

public interface KortingStrategy {
    double berekenPrijsMetKorting(List<Artikel> artikelen, String groep, double duurstePrijs, int kortingsPercentage, double minKortingBedrag);
}
