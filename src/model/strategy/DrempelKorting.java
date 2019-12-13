package model.strategy;

import model.Artikel;

import java.util.List;

public class DrempelKorting extends KortingStrategy {
    @Override
    public double berekenKorting(List<Artikel> artikelen, String groep, double kortingsPercentage, double kortingBedrag) {
        double prijs = 0;
        double percent = kortingsPercentage / 100;
        for(Artikel a : artikelen) {
            prijs += a.getVerkoopprijs();
        }
        if(prijs>=kortingBedrag) {
            double waarde = prijs*percent;
            return round(waarde);
        }
        return 0;
    }
}
