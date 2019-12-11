package model.verkoop;

import controller.VerkoopController;

public class OnHoldVerkoop implements State {
    private VerkoopController verkoopController;

    public OnHoldVerkoop(VerkoopController verkoopController) {
        setVerkoopController(verkoopController);
    }

    public void setVerkoopController(VerkoopController verkoopController) {
        this.verkoopController = verkoopController;
    }

    @Override
    public void reset() {
    }
}
