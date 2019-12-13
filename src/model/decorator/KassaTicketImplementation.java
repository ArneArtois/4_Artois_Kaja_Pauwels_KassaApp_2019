package model.decorator;

import model.Artikel;

import java.util.List;

public class KassaTicketImplementation implements KassaTicket {

    public String print(List<Artikel> artikelen, double betaaldePrijs) {
        String result = "Omschrijving\t\tAantal\tPrijs\n****************************************\n";
        for(Artikel a: artikelen) {
            result += a.getOmschrijving()+"\t\t\t\t\t"+a.getAantalPerKeer()+"\t\t\t"+a.getVerkoopprijs()+"\n";
        }
        result += "****************************************\nBetaald (inclusief korting): " + betaaldePrijs;

        return result;
    }
}
