package model.decorator;

import model.Artikel;

import java.util.List;

public abstract class KassaTicketDecorator implements KassaTicket {
    private KassaTicket ticket;

    public KassaTicketDecorator(KassaTicket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String print(List<Artikel> artikelen, double betaaldePrijs) {
        return ticket.print(artikelen, betaaldePrijs);
    }
}
