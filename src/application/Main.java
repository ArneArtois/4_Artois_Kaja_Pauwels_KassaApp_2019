package application;
	
import database.ArtikelExcelLoadSaveTest;
import database.ArtikelTekstLoadSave;
import javafx.application.Application;
import javafx.stage.Stage;
import jxl.read.biff.BiffException;
import view.KassaView;
import view.KlantView;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException, BiffException {
		KassaView kassaView = new KassaView();
		KlantView klantView = new KlantView();
		ArtikelTekstLoadSave tekstLoadSave = new ArtikelTekstLoadSave();
			tekstLoadSave.load();
		ArtikelExcelLoadSaveTest excelLoadSave = new ArtikelExcelLoadSaveTest();
		File file = new File("src/bestanden/artikel.xls");
		excelLoadSave.read(file);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
