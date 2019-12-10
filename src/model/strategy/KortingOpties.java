package model.strategy;

import java.util.ArrayList;
import java.util.List;

public enum KortingOpties {
    DrempelKorting ("DrempelKorting"),
    GroepsKorting ("GroepsKorting"),
    DuursteKorting ("DuursteKorting");

    private String stringVal;

    private KortingOpties(String value) {
        this.stringVal = value;
    }

    public String getStringValue() {
        return stringVal;
    }


}
