package model;

import model.strategy.KortingStrategy;

import java.lang.reflect.InvocationTargetException;

public class KortingFactory {
    public static KortingStrategy createStrategy(String type) {
        KortingStrategy strategy;
        try {
            Class strategyClass = Class.forName("model.strategy."+type);
            Object strategyObject = strategyClass.getConstructor().newInstance();
            strategy = (KortingStrategy) strategyObject;
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new DomainException(e.getMessage());
        }
        return strategy;
    }
}
