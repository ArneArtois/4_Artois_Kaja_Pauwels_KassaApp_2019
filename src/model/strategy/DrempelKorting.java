package model.strategy;

import model.Artikel;

import java.util.List;

public class DrempelKorting implements KortingStrategy {
    @Override
    public double berekenKorting(List<Artikel> artikelen, String groep, int kortingsPercentage, double kortingBedrag) {
        double prijs = 0;
        double percent = kortingsPercentage / 100;
        for(Artikel a : artikelen) {
            prijs += a.getVerkoopprijs();
        }
        if(prijs >= kortingBedrag) {
            prijs = (prijs * percent);
        }

        return prijs;
    }
}
