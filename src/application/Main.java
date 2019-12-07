package application;
	
import controller.VerkoopController;
import javafx.application.Application;
import javafx.stage.Stage;
import jxl.read.biff.BiffException;

import java.io.IOException;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException, BiffException {
		VerkoopController verkoopController = new VerkoopController();

		/*ArtikelTekstLoadSave tekstLoadSave = new ArtikelTekstLoadSave();
			tekstLoadSave.load();
		ArtikelExcelLoadSaveTest excelLoadSave = new ArtikelExcelLoadSaveTest();
		File file = new File("src/bestanden/artikel.xls");*/

		//excelLoadSave.read(file);

		//For testing purposes
		/*ArtikelDBContext context = new ArtikelDBContext();
		context.setLoadSaveStrategy(new ArtikelTekstLoadSave());
		context.setDBStrategy(new ArtikelDBInMemory());*/
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
