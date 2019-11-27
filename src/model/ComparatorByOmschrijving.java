package model;

import java.util.Comparator;

public class ComparatorByOmschrijving implements Comparator<Artikel> {
    @Override
    public int compare(Artikel a1, Artikel a2) {
        if (a1==null) return -1;
        if (a2==null) return 1;
        int res = a1.getOmschrijving().compareTo(a2.getOmschrijving());
        if (res!=0) return res;
        return -1;
    }
}
