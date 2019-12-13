package model.decorator;

import model.Artikel;

import java.util.List;

public class KassaTicketDecoratorHeader extends KassaTicketDecorator{

    private String header;

    public KassaTicketDecoratorHeader(KassaTicket ticket, String header) {
        super(ticket);
        this.header = header;
    }
    
    public String print(List<Artikel> artikelen, double betaaldePrijs) {
        return this.header+"\n" + super.print(artikelen, betaaldePrijs);
    }

}
