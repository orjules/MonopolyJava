package programm.karten;

import programm.system.Felder;

public class Kartenmanager {

    Felder[] zufälligeFelder;
    Ereigniskarte[] festeKarten;
    Ereigniskarte[] randomKarten;

    public Kartenmanager() {
        zufälligeFelder = new Felder[]{
                Felder.Gemeinschaftsfeld1,
                Felder.Ereignisfeld1
        };
        festeKarten = new Ereigniskarte[]{
                new BankGeld("Einkommenssteuer. Zahle 200€.")
        };
        randomKarten = new Ereigniskarte[]{
                new BankGeld("Platzhalter für zufällige Karte")
        };
    }

    public Ereigniskarte karteZiehen(Felder feld){
        // Feste Felder überprüfen
        switch (feld){
            case Einkommenssteuer:
                return festeKarten[0];
            default:
                break;
        }
        // Random Felder überprüfen
        for (Felder zufall : zufälligeFelder){
            if (feld.equals(zufall)){
                return getRandomKarte();
            }
        }
        // Ansonsten null zurückgeben
        return null;
    }

    private Ereigniskarte getRandomKarte(){
        return randomKarten[0];
    }
}
