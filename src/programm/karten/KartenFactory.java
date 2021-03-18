package programm.karten;

import programm.grundstücke.Grundbuch;
import programm.system.Felder;
import programm.system.Würfel;
import programm.system.spieler.Spielleiter;

import java.util.HashMap;

public class KartenFactory {
    Spielleiter spielleiter;
    Grundbuch grundbuch;

    public KartenFactory(Spielleiter spielleiter, Grundbuch grundbuch) {
        this.spielleiter = spielleiter;
        this.grundbuch = grundbuch;
    }

    public HashMap<Felder, Ereigniskarte> erstelleFesteKarten(){
        HashMap<Felder, Ereigniskarte> ausgabe = new HashMap<>();
        ausgabe.put(Felder.Einkommenssteuer, new BankGeld("Einkommenssteuer. Zahle 200€.", spielleiter, -200));
        // new FeldGehen("Gehen sie in das Gefängnis. Begeben Sie sich direkt dorthin. Gehen Sie nicht" +
        //                        " über Los. Ziehen Sie nicht 200€ ein.", spielleiter, Felder.Gefängnis_bzw_Besuch, true)
        return ausgabe;
    }

    public Ereigniskarte[] erstelleRandomKarten(){
        return new Ereigniskarte[]{
                new BankGeld("Schuldgeld. Zahlen sie 50€", spielleiter, -50),
                new BankGeld("Urlaubsgeld! Sie erhalten 100€", spielleiter, 100),
                new BankGeld("Ihre Lebensversicherung wird fällig. Sie erhalten 100€", spielleiter, 100),
                new BankGeld("Arzt-Kosten. Zahlen sie 50€", spielleiter, -50),
                new BankGeld("Einkommenssteuerrückerstattung! Sie erhalten 20€", spielleiter, 20),
                new BankGeld("Krankenhausgebüren! Zahlen sie 100€", spielleiter, -100),
                new FeldGehen("Gehen sie in das Gefängnis. Begeben Sie sich direkt dorthin. Gehen Sie nicht" +
                        " über Los. Ziehen Sie nicht 200€ ein.", spielleiter, Felder.Gefängnis_bzw_Besuch, true),
                new FeldGehen("Gehen sie in das Gefängnis. Begeben Sie sich direkt dorthin. Gehen Sie nicht" +
                        " über Los. Ziehen Sie nicht 200€ ein.", spielleiter, Felder.Gefängnis_bzw_Besuch, true),
                new BankGeld("Sie erhalten auf Vorzugs-Aktien 7% Dividende: 25€", spielleiter, 25),
                new GeldVonSpielern("Sie haben Geburtstag. Jeder Spieler schenkt Ihnen 10€", spielleiter, 10, true),
                new BankGeld("Sie erben 100€", spielleiter, 100),
                new BankGeld("Aus Lagerverkäufen erhalten sie 50€", spielleiter, 50),
                new BankGeld("Zweiter Preis im Schönheitswttbewerb. Sie erhalten 10€", spielleiter, 10),
                // Für Häuser und Hotels zahlen (2 mal)
                new FeldGehen("Rücken sie vor bis auf Los. (Ziehe 200€ ein)", spielleiter, Felder.Los),
                new FeldGehen("Rücken sie vor bis auf Los. (Ziehe 200€ ein)", spielleiter, Felder.Los), // zweites mal
                new BankGeld("Bank-Irrtum zu Ihren Gunsten. Ziehen Sie 200€ ein.", spielleiter, 200),
                // new FeldGehen("Rücken sie vor bis zur Schlossalle.", spielleiter, Felder.Schlossalle),
                new FeldGehen("Machen sie einen Ausflug zum Südbahnhof. Wenn Sie über Los kommen, ziehen Sie 200€ ein.", spielleiter, Felder.Südbahnhof),
                new BankGeld("Ihr Bausparvertrag wird fällig. Sie erhalten 200€.", spielleiter, 200),
                new BankGeld("Ihr Bausparvertrag wird fällig. Sie erhalten 200€.", spielleiter, 200),
                // new FeldGehen("Rücken sie vor bis zum Opernplatz. Wenn Sie über Los kommen, ziehen sie 200€ ein.", spielleiter, Felder.Opernplatz),
                new ZuWerkGehen("Rücken Sie vor bis zum nächsten Versorgungswerk. Werfen Sie den Würfel " +
                        "und zahlen dem Eigentümer den zehnfachen Betrag Ihres Wurfergebnisses. Wenn das Werk noch niemandem" +
                        "gehört, können Sie es von der Bank kaufen.", spielleiter, grundbuch),
                new BankGeld("Die Bank zahlt Ihnen eine Dividende von 50€.", spielleiter, 50),
                // Gefängnis frei (2 mal)
                new FeldGehen("Rücken sie vor bis zur Seestraße. Wenn Sie über Los kommen, ziehen Sie 200€ ein.", spielleiter, Felder.Seestraße),
                new GeldVonSpielern("Sie sind zum Vorstand gewählt worden. Zahlen Sie jedem Spieler 50€", spielleiter, 50, true),
                new DreiFelderZurück("Gehen Sie drei Felder zurück.", spielleiter),
                new BankGeld("Strafzettel! Zahlen Sie 15€.", spielleiter, 15)
        };
    }
}
