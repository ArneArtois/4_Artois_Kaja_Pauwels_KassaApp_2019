package model.verkoop;

import model.VerkoopModel;

public class Einde implements State {
    private VerkoopModel verkoopModel;

    public Einde(VerkoopModel model) {
        this.verkoopModel = model;
    }

    @Override
    public void reset() {
        verkoopModel.setCurrentState(verkoopModel.getNieuweVerkoopState());
        //verkoopModel.volgendeVerkoop();
    }
}
