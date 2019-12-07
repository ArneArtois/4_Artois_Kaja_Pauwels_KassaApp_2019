//package view.domain;
//
//import model.Artikel;
//import model.Model;
//import view.KlantView;
//
//import java.util.ArrayList;
//
//public class Controller {
//    private KlantView klantView;
//    private Model model;
//    public Controller(KlantView klantView, Model model){
//        this.klantView = klantView;
//        this.model = model;
//        this.klantView.setController(this);
//    }
//    public void handler(){
//        ArrayList<Artikel> artikels = model.getArtikelsInMemory();
//        klantView.setMemory(artikels);
//    }
//}
