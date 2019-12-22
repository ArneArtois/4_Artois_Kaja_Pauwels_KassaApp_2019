package view.panels;

import controller.VerkoopController;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class LogPane extends GridPane {
    private VerkoopController controller;
    private int lijnNummer = 0;

    public LogPane(VerkoopController controller) {
        this.controller = controller;
    }

    public void addEntry(String lijn) {
        this.add(new Label(lijn), 0,lijnNummer);
        lijnNummer++;
    }
}
