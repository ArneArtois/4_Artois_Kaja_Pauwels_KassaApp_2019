package model.verkoop;

import controller.VerkoopController;

public class NietBetaald implements State {
    private VerkoopController verkoopController;

    public NietBetaald(VerkoopController verkoopController)
    {setVerkoopController(verkoopController);}

    public void setVerkoopController(VerkoopController verkoopController) {
        this.verkoopController = verkoopController;
    }
    @Override
    public void betaal(){

    }
    @Override
    public void annuleer(){
    }
}
