package database.factory;

import database.DbException;
import database.strategy.ArtikelDBStrategy;
import database.strategy.LoadSaveStrategy;

import java.lang.reflect.InvocationTargetException;

public class ArtikelDBStrategyFactory {
    public static ArtikelDBStrategy createStrategy(String type) {
        ArtikelDBStrategy strategy;
       /* if(type.equals("Tekst")) {
            strategy = new ArtikelTekstLoadSave();
        } else if(type.equals("Excel")) {
            strategy = new ArtikelExcelLoadSave();
        }*/
        try {
            Class strategyClass = Class.forName("database.ArtikelDB" + type);
            Object strategyObj = strategyClass.getConstructor().newInstance();
            strategy = (ArtikelDBStrategy) strategyObj;
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new DbException(e.getMessage());
        }

        return strategy;
    }
}
