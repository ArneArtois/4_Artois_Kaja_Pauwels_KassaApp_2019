package view.panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.KortingFactory;
import model.strategy.KortingOpties;
import model.strategy.KortingStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PropertiesPane extends GridPane {
    Label keuzeLabel = new Label("Kies hier uw opslagkeuze: ");
    CheckBox tekstBox = new CheckBox("Tekst");
    CheckBox excelBox = new CheckBox("Excel");
    Button submitChoice = new Button("Bevestig");
    ObservableList<String> kortingOpties = FXCollections.observableArrayList(getValues());
    ComboBox<String> kortingsCombo = new ComboBox<>(kortingOpties);
    Label kortingsLabel = new Label("Kies uw kortingstype: ");
    Label percentageLabel = new Label("Voer uw kortingspercentage in: ");
    TextField percentageField = new TextField();
    Label bedragLabel = new Label("Voer uw aankoopbedrag in: ");
    TextField bedragField = new TextField();
    Label errorLabel = new Label();
    Properties properties = new Properties();
    Label groepLabel = new Label("Voer een groep in: ");
    TextField groepField = new TextField();
    InputStream is;
    OutputStream os;
    Label headerLabel = new Label("Header toevoegen");
    Label footerLabel = new Label("Footer toevoegen");
    CheckBox footerCheck = new CheckBox();
    CheckBox headerCheck = new CheckBox();
    TextField headerText = new TextField();
    TextField footerText = new TextField();
    public PropertiesPane() {
        try {
           is = new FileInputStream("src/bestanden/instellingen.properties");
            //os = new FileOutputStream("src/bestanden/instellingen.properties");
           properties.load(is);
           /*if(properties.getProperty("method") == null) {
               properties.setProperty("method", "Tekst");
               properties.store(os, "LoadSaveMethod");
           }*/

           if(properties.getProperty("method").equals("Tekst")) {
               tekstBox.setSelected(true);
           } else if(properties.getProperty("method").equals("Excel")) {
               excelBox.setSelected(true);
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
        this.add(submitChoice,1,18);
        this.add(kortingsLabel, 2,0);
        this.add(kortingsCombo, 2,1);
        this.add(percentageLabel,0,3);
        this.add(percentageField,0,4);
        this.add(bedragLabel, 0,5);
        this.add(bedragField, 0,6);
        this.add(groepLabel,0,7);
        this.add(groepField,0,8);
        this.add(headerCheck, 1,10);
        this.add(headerLabel, 0,10);
        this.add(headerText, 0,11);

        this.add(footerCheck,1,13);
        this.add(footerLabel,0,13);
        this.add(footerText,0,14);

        submitChoice.setOnAction(event -> {
            KortingStrategy korting = KortingFactory.createStrategy(kortingsCombo.getValue());
            //System.out.println(korting);

            try {
                //is = new FileInputStream("src/bestanden/instellingen.properties");
                os = new FileOutputStream("src/bestanden/instellingen.properties");
                String choice = "";
                if(tekstBox.isSelected()) {
                    choice = tekstBox.getText();
                    properties.setProperty("method",choice);
                    //properties.store(os, "LoadSaveMethod");
                } else if(excelBox.isSelected()) {
                    choice = excelBox.getText();
                    properties.setProperty("method", choice);
                    //properties.store(os, "LoadSaveMethod");
                }

                if(!bedragField.getText().equals("")) {
                    properties.setProperty("bedrag", bedragField.getText());
                }

                if(!percentageField.getText().equals("")) {
                    properties.setProperty("percentage", percentageField.getText());
                }

                if(!groepField.getText().equals("")) {
                    properties.setProperty("groep", groepField.getText());
                }

                if(headerCheck.isSelected() && !headerText.getText().equals("")) {

                    properties.setProperty("header", headerText.getText());
                } else {
                    properties.remove("header");

                }

                if(footerCheck.isSelected() && !footerText.getText().equals("")) {
                    properties.setProperty("footer", footerText.getText());
                } else {
                    properties.remove("footer");
                }

                properties.setProperty("kortingsType", kortingsCombo.getValue());

                properties.store(os, "Instellingen van de KassaApp");
                os.close();

            } catch (FileNotFoundException e) {
                errorLabel.setText(e.getMessage());
            } catch (IOException e) {
                errorLabel.setText(e.getMessage());
            }


        });
    }

    public List<String> getValues() {
        List<String> values = new ArrayList<>();
        for(KortingOpties k : KortingOpties.values()) {
            values.add(k.toString());
        }
        return values;
    }

    public Properties getInstellingen() {
        return properties;
    }

   /* public OutputStream getOut() {
        return this.os;
    }*/


}
