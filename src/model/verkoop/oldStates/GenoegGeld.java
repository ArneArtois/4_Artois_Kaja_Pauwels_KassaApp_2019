package model.verkoop.oldStates;

import model.VerkoopModel;
import model.verkoop.State;

public class GenoegGeld implements State {
    private VerkoopModel model;


    public GenoegGeld(VerkoopModel model) {
        this.model = model;
    }

    @Override
    public void onHold() {
        model.setCurrentState(model.getOnHoldState());
    }

   /* @Override
    public void betaal() {
        model.setCurrentState(model.getBeeindigdState());
        model.betaalVerkoop();
    }*/
}
