package model;


import database.ArtikelDBContext;
import database.ArtikelDBInMemory;

import java.util.ArrayList;

public class Model {
    ArtikelDBContext context;
    public Model(ArtikelDBContext context){
        this.context = context;
    }

    public ArrayList<Artikel> getArtikelsInMemory(){
        return (ArrayList<Artikel>) context.getCart();
    }
}
