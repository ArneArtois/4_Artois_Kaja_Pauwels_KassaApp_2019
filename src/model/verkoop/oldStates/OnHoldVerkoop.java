package model.verkoop.oldStates;

import controller.VerkoopController;
import model.VerkoopModel;
import model.verkoop.State;

public class OnHoldVerkoop implements State {
    private VerkoopModel model;

    public OnHoldVerkoop(VerkoopModel model) {
        this.model = model;
    }

    @Override
    public void reset() {
    }
}
