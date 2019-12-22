package model.verkoop;

import model.VerkoopModel;

public class NieuweVerkoop implements State {
    private VerkoopModel verkoopModel;

    public NieuweVerkoop(VerkoopModel model) {
        this.verkoopModel = model;
    }

    @Override
    public void sluitAf() {
        verkoopModel.setCurrentState(verkoopModel.getAfgeslotenState());
    }

    @Override
    public void onHold() {
        verkoopModel.setCurrentState(verkoopModel.getOnHold());
    }
}
