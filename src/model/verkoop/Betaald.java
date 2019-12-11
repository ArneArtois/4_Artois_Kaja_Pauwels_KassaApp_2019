package model.verkoop;

import controller.VerkoopController;

public class Betaald implements State {
    private VerkoopController verkoopController;

    public Betaald(VerkoopController verkoopController)
    {setVerkoopController(verkoopController);}

    public void setVerkoopController(VerkoopController verkoopController) {
        this.verkoopController = verkoopController;
    }
    @Override
    public void maak(){}

    @Override
    public void brengTerug(){}
}
