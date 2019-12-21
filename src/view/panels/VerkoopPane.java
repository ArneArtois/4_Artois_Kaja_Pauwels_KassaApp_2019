package view.panels;

import controller.VerkoopController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Artikel;

import java.util.List;

public class VerkoopPane extends GridPane {
    private Label enterLabel = new Label("Geef de artikelcode in: ");
    private TextField codeTextField = new TextField();
    private TextField codeTextFieldRemove = new TextField();
    private Button onHold = new Button("Sla verkoop op");
    private Button loadVerkoop = new Button("Zet verkoop terug");
    private Button afsluit = new Button("AFSLUIT");
    private Label errorLabel = new Label();
    private TableView<model.Artikel> tableView = new TableView<>();
    private Label prijs = new Label("Totale prijs: ");
    private Label korting = new Label("Totale korting: ");
    private Label bedrag = new Label("Totale bedrag: ");
    private VerkoopController verkoopController;
    private double prijsDouble = 0;
    private double kortingDouble = 0;
    private Button betaaldBtn = new Button("BETAALD");
    private Button annuleerBtn = new Button("ANNULEER");

    TableColumn omschrCol = new TableColumn<model.Artikel, Object>("Omschrijving");
    TableColumn prijsCol = new TableColumn("prijs");
    TableColumn aantalCol = new TableColumn("Aantal");

    public VerkoopPane(VerkoopController verkoopController) {
        this.verkoopController = verkoopController;
        this.add(enterLabel,0,0);
        this.add(codeTextField,0,1);
        this.add(codeTextFieldRemove, 0, 2);
        this.add(errorLabel, 1, 0);
        omschrCol.setCellValueFactory(
                new PropertyValueFactory<Artikel,String>("omschrijving")
        );
        prijsCol.setCellValueFactory(
                new PropertyValueFactory<Artikel,Double>("verkoopprijs")
        );
        aantalCol.setCellValueFactory(
                new PropertyValueFactory<Artikel,Integer>("aantalPerKeer")
        );
        tableView.getColumns().addAll(omschrCol, prijsCol, aantalCol);
        this.add(tableView, 0,3);
        this.add(prijs,0,4);
        this.add(korting, 0, 5);
        this.add(bedrag, 0, 6);
        codeTextField.setOnAction(event -> {
            verkoopController.codeEnter(Integer.parseInt(codeTextField.getText()));
            this.codeTextField.clear();
        });
        codeTextFieldRemove.setOnAction(event -> {
            verkoopController.codeRemove(Integer.parseInt(codeTextFieldRemove.getText()));
            this.codeTextFieldRemove.clear();
            if(afsluit.isDisabled()){
                korting();
            }
        });
        this.add(onHold, 1,1);
        onHold.setOnAction(event -> {
            this.loadVerkoop.setVisible(true);
            this.loadVerkoop.setDisable(false);
            verkoopController.slaVerkoopOp();
        });
        this.add(loadVerkoop, 1,2);
        loadVerkoop.setVisible(false);
        loadVerkoop.setDisable(false);
        loadVerkoop.setOnAction(event -> {
            verkoopController.leesVerkoop();
            loadVerkoop.setVisible(false);
            loadVerkoop.setDisable(true);
        });
        this.add(afsluit, 1, 3);
        afsluit.setOnAction(event -> {
            this.afsluit.setDisable(true);
            this.codeTextField.setDisable(true);
            verkoopController.update();
            //verkoopController.printKassaTicket();
        });

        this.add(betaaldBtn,1,4);
        betaaldBtn.setOnAction(event -> {
            verkoopController.eindigVerkoop();
        });
        this.add(annuleerBtn, 1, 5);
        annuleerBtn.setOnAction(event ->{
           verkoopController.eindigVerkoop();
        });

    }

    public void updateDisplay(double prijs, List<Artikel> artikelen, double korting, boolean afgesloten) {
        this.prijs.setText("Totale prijs: " + prijs);
        this.prijsDouble = prijs;
        ObservableList<Artikel> observableList =  FXCollections.observableArrayList(artikelen);
        this.tableView.setItems(observableList);
        this.kortingDouble = korting;
        if(afgesloten) {
            this.korting.setText("Totale korting: " + korting);
            this.bedrag.setText("Totale bedrag: " + (prijs - korting));
        }
    }

    public void artikelNietGevonden() {
        this.errorLabel.setText("Artikel bestaat niet");
    }

    public void artikelWelGevonden() {
        this.errorLabel.setText("");
    }
    public void korting(){
    }
    public boolean getAfgesloten(){
        return this.afsluit.isDisabled();
    }

    /*public Label getPrijs() {
        return prijs;
    }

    public double getSom() {
        return som;
    }

    public double addSom(double val) {
        this.som += val;
        return this.som;
    }

    public TextField getCodeTextField() {
        return codeTextField;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public TableView<model.Artikel> getTableView() {
        return tableView;
    }*/
}
