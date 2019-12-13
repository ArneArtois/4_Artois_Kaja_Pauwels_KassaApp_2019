package model.decorator;

import model.Artikel;

import java.util.List;

public class KassaTicketDecoratorFooter extends KassaTicketDecorator {
    private String footer;
    public KassaTicketDecoratorFooter(KassaTicket ticket, String footer) {
        super(ticket);
        this.footer = footer;
    }

    public String print(List<Artikel> artikelen, double betaaldePrijs) {
        return super.print(artikelen, betaaldePrijs) + "\n" + this.footer;
    }
}
