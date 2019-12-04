package model;


import database.ArtikelDBContext;
import database.ArtikelDBInMemory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Model {
    ArtikelDBContext context;
    public Model(ArtikelDBContext context){
        this.context = context;
    }

    public ArrayList<Artikel> getArtikelsInMemory(){
        ArrayList<Artikel> result = new ArrayList<>();
        ArrayList<Artikel> artikels = (ArrayList<Artikel>) context.getCart();
        HashMap<Artikel,Integer> artikelHashMap = new HashMap<>();
        for(Artikel item: artikels)
            if(artikelHashMap.containsKey(item)){
                artikelHashMap.put(item, artikelHashMap.get(item) +1);
            }
        else
            artikelHashMap.put(item, 1);

        for(Map.Entry hashmap: artikelHashMap.entrySet()){
           Artikel newArtikel = new Artikel((Artikel) hashmap.getKey());
           newArtikel.setAantalPerKeer((Integer) hashmap.getValue());
           result.add(newArtikel);
        }
        return result;
    }
}
