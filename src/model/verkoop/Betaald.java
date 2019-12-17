package model.verkoop;

import controller.VerkoopController;
import model.VerkoopModel;

public class Betaald implements State {
    private VerkoopModel model;

    public Betaald(VerkoopModel model)
    {
        this.model = model;
    }

    @Override
    public void eindigVerkoop() {
        model.setCurrentState(model.getBeeindigdState());
        model.volgendeVerkoop();
    }
}
