package model.verkoop;

import controller.VerkoopController;
import model.VerkoopModel;

public class NewVerkoop implements State {
    private VerkoopModel verkoop;

    public NewVerkoop(VerkoopModel model) {
        this.verkoop = model;
        //setVerkoopController(verkoopController);
    }

   /* public void setVerkoopController(VerkoopController verkoopController) {
        this.verkoopController = verkoopController;
    }*/

    @Override
    public void genoegGeld() {
        verkoop.setCurrentState(verkoop.getGenoegGeldState());
    }

    @Override
    public void onHold() {
        verkoop.setCurrentState(verkoop.getOnHoldState());
    }
}