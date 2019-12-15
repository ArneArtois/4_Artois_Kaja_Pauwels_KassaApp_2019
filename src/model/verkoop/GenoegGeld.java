package model.verkoop;

import model.VerkoopModel;

public class GenoegGeld implements State {
    private VerkoopModel model;


    public GenoegGeld(VerkoopModel model) {
        this.model = model;
    }

    @Override
    public void onHold() {
        model.setCurrentState(model.getOnHoldState());
    }

    @Override
    public void betaal() {
        model.setCurrentState(model.getBetaaldState());
    }
}
