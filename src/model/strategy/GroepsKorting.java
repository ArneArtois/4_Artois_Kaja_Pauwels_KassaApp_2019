package model.strategy;

import model.Artikel;

import java.util.List;

public class GroepsKorting extends KortingStrategy {

    @Override
    public double berekenKorting(List<Artikel> artikelen, String groep, double percentage, double kortingBedrag) {
        double prijs = 0;
        double percent = percentage / 100;
        for(Artikel a : artikelen) {
            if(a.getArtikelGroep().equals("gr" +groep)) {
                prijs += a.getVerkoopprijs();
            }
        }

        return round(prijs * percent);
    }
}
