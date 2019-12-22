//package model.verkoop.oldStates;
//
//import controller.VerkoopController;
//import model.VerkoopModel;
//import model.verkoop.State;
//
//public class Beeindigd implements State {
//    private VerkoopModel model;
//
//    public Beeindigd(VerkoopModel model) {
//        this.model = model;
//    }
//
//    @Override
//    public void volgendeVerkoop() {
//        if(model.getCurrentState() == model.getBetaaldState())
//        model.setCurrentState(model.getNewVerkoopState());
//        model.volgendeVerkoop();
//    }
//}
