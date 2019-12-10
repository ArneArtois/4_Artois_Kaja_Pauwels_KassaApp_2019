package model.strategy;

import model.Artikel;

import java.util.List;

public class GroepsKorting implements KortingStrategy {

    @Override
    public double berekenPrijsMetKorting(List<Artikel> artikelen, String groep, double duurstePrijs, int percentage, double kortingBedrag) {
        double prijs = 0;
        double percent = percentage / 100;
        for(Artikel a : artikelen) {
            if(a.getArtikelGroep().equals(groep)) {
                prijs += a.getVerkoopprijs();
            }
        }

        return prijs - (prijs * percent);
    }
}
