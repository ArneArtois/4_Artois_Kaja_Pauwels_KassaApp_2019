package model.verkoop;

import controller.VerkoopController;

public class Beeindigd implements State {
    private VerkoopController verkoopController;

    public Beeindigd(VerkoopController verkoopController) {
        setVerkoopController(verkoopController);
    }

    public void setVerkoopController(VerkoopController verkoopController) {
        this.verkoopController = verkoopController;
    }
}
