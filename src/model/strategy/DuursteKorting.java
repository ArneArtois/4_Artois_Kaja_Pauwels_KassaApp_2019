package model.strategy;

import model.Artikel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DuursteKorting extends KortingStrategy {

    @Override
    public double berekenKorting(List<Artikel> artikelen, String groep, double kortingsPercentage, double minKortingBedrag) {
        double prijs = 0;
        double percent = kortingsPercentage / 100;
        Artikel max = Collections.max(artikelen, new Comparator<Artikel>() {
            @Override
            public int compare(Artikel o1, Artikel o2) {
                if (o1 == null) {
                    return -1;
                }

                if(o2 == null) {
                    return 1;
                }

                double res = o1.getVerkoopprijs() - o2.getVerkoopprijs();
                if(res != 0) {
                    return (int) res;
                }
                return -1;
            }
        });
       prijs = round(percent * max.getVerkoopprijs());
       return prijs;
    }
}
