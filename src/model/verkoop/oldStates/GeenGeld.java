package model.verkoop.oldStates;

import model.VerkoopModel;
import model.verkoop.State;

public class GeenGeld implements State {
    private VerkoopModel model;

    public GeenGeld(VerkoopModel model) {
        this.model = model;
    }

    @Override
    public void annuleer() {
        model.setCurrentState(model.getBeeindigdState());
    }
}
