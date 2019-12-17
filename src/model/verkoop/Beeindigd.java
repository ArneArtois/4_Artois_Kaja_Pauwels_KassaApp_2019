package model.verkoop;

import controller.VerkoopController;
import model.VerkoopModel;

public class Beeindigd implements State {
    private VerkoopModel model;

    public Beeindigd(VerkoopModel model) {
        this.model = model;
    }

    @Override
    public void volgendeVerkoop() {
        model.setCurrentState(model.getNewVerkoopState());
        model.volgendeVerkoop();
    }
}
