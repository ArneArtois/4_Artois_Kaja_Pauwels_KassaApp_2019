package model.verkoop;

import controller.VerkoopController;
import model.VerkoopModel;

public class OnHoldVerkoop implements State {
    private VerkoopModel model;

    public OnHoldVerkoop(VerkoopModel model) {
        this.model = model;
    }

    @Override
    public void reset() {
    }
}
