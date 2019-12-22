package model.verkoop;

import model.VerkoopModel;

public class Afgesloten implements State {
    private VerkoopModel verkoopModel;

    public Afgesloten(VerkoopModel model) {
        this.verkoopModel = model;
    }

    @Override
    public void betaal() {
        verkoopModel.setCurrentState(verkoopModel.getEindeState());
    }

    @Override
    public void annuleer() {
        verkoopModel.setCurrentState(verkoopModel.getEindeState());
        verkoopModel.volgendeVerkoop();
    }
}
