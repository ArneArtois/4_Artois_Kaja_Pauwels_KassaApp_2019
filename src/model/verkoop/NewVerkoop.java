package model.verkoop;

import controller.VerkoopController;

public class NewVerkoop implements State {
    private VerkoopController verkoopController;

    public NewVerkoop(VerkoopController verkoopController) {
        setVerkoopController(verkoopController);
    }

    public void setVerkoopController(VerkoopController verkoopController) {
        this.verkoopController = verkoopController;
    }

    @Override
    public void reset() {
    }
}