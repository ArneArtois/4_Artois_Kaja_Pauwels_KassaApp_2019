package model.verkoop;

import model.VerkoopModel;

public class OnHold implements State {
    private VerkoopModel verkoopModel;

    public OnHold(VerkoopModel model) {
        this.verkoopModel = model;
    }

    @Override
    public void zetNietOnHold() {
        verkoopModel.setCurrentState(verkoopModel.getNieuweVerkoopState());
        //verkoopModel.zetVerkoopTerug();
    }
}
