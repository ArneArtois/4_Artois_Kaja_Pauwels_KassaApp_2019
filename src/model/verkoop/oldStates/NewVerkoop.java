//package model.verkoop.oldStates;
//
//import controller.VerkoopController;
//import database.ArtikelDBContext;
//import database.factory.ArtikelDBStrategyFactory;
//import database.factory.LoadSaveStrategyFactory;
//import model.Artikel;
//import model.VerkoopModel;
//import model.verkoop.State;
//import view.panels.PropertiesPane;
//
//import java.util.List;
//import java.util.Properties;
//
//public class NewVerkoop implements State {
//    private VerkoopModel verkoop;
//    private ArtikelDBContext context;
//    private PropertiesPane propertiesPane;
//    private Properties properties;
//
//    public NewVerkoop(VerkoopModel model) {
//        this.propertiesPane = new PropertiesPane();
//        this.properties = propertiesPane.getInstellingen();
//        this.verkoop = model;
//        this.context = new ArtikelDBContext();
//        this.context.setDBStrategy(ArtikelDBStrategyFactory.createStrategy("InMemory"));
//        this.context.setLoadSaveStrategy(LoadSaveStrategyFactory.createStrategy(properties.getProperty("method")));
//
//        //setVerkoopController(verkoopController);
//    }
//
//   /* public void setVerkoopController(VerkoopController verkoopController) {
//        this.verkoopController = verkoopController;
//    }*/
//
//    @Override
//    public void betaal() {
//        //if(verkoop.getArtikelen().containsAll(artikelen)) {
//            for(Artikel a: verkoop.getArtikelen()) {
//                for(Artikel art : context.getAll()) {
//                    if(a.equals(art)) {
//                        art.setInVoorraad(art.getInVoorraad()-a.getAantalPerKeer());
//                        context.updateArtikel(art);
//                    }
//                }
//            }
//            context.save(context.getAll());
//            verkoop.setCurrentState(verkoop.getBetaaldState());
//            verkoop.notifyObservers(null, context.getAll(), 0, false);
//        //}
//
//    }
//
//    @Override
//    public void genoegGeld() {
//        verkoop.setCurrentState(verkoop.getGenoegGeldState());
//    }
//
//    @Override
//    public void onHold() {
//        verkoop.setCurrentState(verkoop.getOnHoldState());
//    }
//
//    @Override
//    public void annuleer() {
//        verkoop.setCurrentState(verkoop.getBeeindigdState());
//        verkoop.volgendeVerkoop();
//    }
//}