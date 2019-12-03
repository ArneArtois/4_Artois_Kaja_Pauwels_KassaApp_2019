package database.factory;

import database.ArtikelTekstLoadSave;
import database.DbException;
import database.ArtikelExcelLoadSave;
import database.strategy.LoadSaveStrategy;

import java.lang.reflect.InvocationTargetException;

public class LoadSaveStrategyFactory {
    public static LoadSaveStrategy createStrategy(String type) {
        LoadSaveStrategy strategy;
       /* if(type.equals("Tekst")) {
            strategy = new ArtikelTekstLoadSave();
        } else if(type.equals("Excel")) {
            strategy = new ArtikelExcelLoadSave();
        }*/
       try {
           Class strategyClass = Class.forName("database.Artikel" + type + "LoadSave");
           Object strategyObj = strategyClass.getConstructor().newInstance();
           strategy = (LoadSaveStrategy) strategyObj;
       } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
           throw new DbException(e.getMessage());
       }

        return strategy;
    }
}
