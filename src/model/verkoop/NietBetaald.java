package model.verkoop;

import controller.VerkoopController;
import model.VerkoopModel;

public class NietBetaald implements State {
    private VerkoopModel model;

    public NietBetaald(VerkoopModel model)
    {this.model = model;
    }

    @Override
    public void betaal(){
        model.setCurrentState(model.getBetaaldState());
    }
    @Override
    public void annuleer(){
        model.setCurrentState(model.getNewVerkoopState());
    }
}
