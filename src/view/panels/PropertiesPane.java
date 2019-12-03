package view.panels;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.util.Properties;

public class PropertiesPane extends GridPane {
    Label keuzeLabel = new Label("Kies hier uw opslagkeuze: ");
    CheckBox tekstBox = new CheckBox("Tekst");
    CheckBox excelBox = new CheckBox("Excel");
    Button submitChoice = new Button("Bevestig");
    Label errorLabel = new Label();
    Properties properties = new Properties();
    InputStream is;
    OutputStream os;
    public PropertiesPane() {
        try {
           is = new FileInputStream("src/bestanden/instellingen.properties");
           properties.load(is);
           if(properties.getProperty("method").equals("Excel")) {
               excelBox.setSelected(true);
           } else if(properties.getProperty("method").equals("Tekst")) {
               tekstBox.setSelected(true);
           }
           is.close();
        } catch(FileNotFoundException e) {
            errorLabel.setText(e.getMessage());
        } catch (IOException e) {
            errorLabel.setText(e.getMessage());
        }
        this.add(keuzeLabel,0,0);
        this.add(tekstBox,0,1);
        this.add(excelBox,0,2);
        this.add(submitChoice,0,4);
        submitChoice.setOnAction(event -> {
            try {
                //is = new FileInputStream("src/bestanden/instellingen.properties");
                os = new FileOutputStream("src/bestanden/instellingen.properties");
                String choice = "";
                if(tekstBox.isSelected()) {
                    choice = tekstBox.getText();
                    properties.setProperty("method",choice);
                    properties.store(os, "LoadSaveChoice");
                } else if(excelBox.isSelected()) {
                    choice = excelBox.getText();
                    properties.setProperty("method", choice);
                    properties.store(os, "LoadSaveChoice");
                }
                os.close();

            } catch (FileNotFoundException e) {
                errorLabel.setText(e.getMessage());
            } catch (IOException e) {
                errorLabel.setText(e.getMessage());
            }


        });
    }

    public Properties getInstellingen() {
        return properties;
    }


}
