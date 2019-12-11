package model.strategy;

import model.Artikel;

import java.util.List;

public class GroepsKorting implements KortingStrategy {

    @Override
    public double berekenKorting(List<Artikel> artikelen, String groep, double percentage, double kortingBedrag) {
        double prijs = 0;
        double percent = percentage / 100;
        for(Artikel a : artikelen) {
            if(a.getArtikelGroep().equals(groep)) {
                prijs += a.getVerkoopprijs();
            }
        }

        return (prijs * percent);
    }
}
