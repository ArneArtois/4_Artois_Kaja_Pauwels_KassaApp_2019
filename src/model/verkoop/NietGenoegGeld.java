package model.verkoop;

import model.VerkoopModel;

public class NietGenoegGeld implements State {
    private VerkoopModel model;

    public NietGenoegGeld(VerkoopModel model) {
        this.model = model;
    }

    @Override
    public void nietGenoegGeld() {
        model.setCurrentState(model.getNietGenoegGeldState());
    }
}
