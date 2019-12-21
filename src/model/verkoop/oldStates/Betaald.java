package model.verkoop.oldStates;

import controller.VerkoopController;
import model.VerkoopModel;
import model.verkoop.State;

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
