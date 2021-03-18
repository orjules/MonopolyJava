package programm.karten;

import programm.grundstücke.Grundbuch;
import programm.system.Felder;
import programm.system.Würfel;
import programm.system.spieler.Spielleiter;

import java.util.HashMap;

public class KartenFactory {
    Spielleiter spielleiter;
    Würfel würfel;
    Grundbuch grundbuch;

    public KartenFactory(Spielleiter spielleiter, Würfel würfel, Grundbuch grundbuch) {
        this.spielleiter = spielleiter;
        this.würfel = würfel;
        this.grundbuch = grundbuch;
    }

    public HashMap<Felder, Ereigniskarte> erstelleFesteKarten(){
        HashMap<Felder, Ereigniskarte> ausgabe = new HashMap<>();
        ausgabe.put(Felder.Einkommenssteuer, new BankGeld("Einkommenssteuer. Zahle 200€.", spielleiter, 200));
        return ausgabe;
    }

    public Ereigniskarte[] erstelleRandomKarten(){
        return new Ereigniskarte[]{
                new BankGeld("Platzhalter für zufällige Karte", spielleiter, 200)
        };
    }
}
