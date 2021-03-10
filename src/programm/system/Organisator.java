package programm.system;

import java.util.ArrayList;

public class Organisator {
    Spielleiter spielleiter;
    Darsteller darsteller;
    Würfel würfel;

    public Organisator(Spielleiter spielleiter, Darsteller darsteller, Würfel würfel) {
        this.spielleiter = spielleiter;
        this.darsteller = darsteller;
        this.würfel = würfel;
    }

    public void gameLoop(){
        Boolean spielLäuft = true;

        // Außenloop, der das ganze Spiel läuft
        while (spielLäuft){
            Boolean zugBeendet = false;

            // Innenloop läuft solange der Spieler seinen Zug nicht beendet hat
            while (!zugBeendet){
                String ausgabeText = "";
                ArrayList<String> erlaubteEingaben = new ArrayList<>();

                // Als erstes muss ein Spieler der im Gefängnis sitzt versuchen raus zu kommen
                if (spielleiter.getGeradeDran().getIstImGefängnis()){
                    gefängnisScipt();
                }
                // Nach dem Gefängnisscript ist der Spieler entweder draußen oder immernoch drin.
                if (!spielleiter.getGeradeDran().getIstImGefängnis()){
                    // Würfeln und wenn pasch, das nochmalige Würfeln hinzufügen (aber nur wenn man nicht schon gefürfelt hat)
                    if (würfel.darfNochmalWerfen()){
                        ausgabeText += "'w' um zu würfeln.\n";
                        erlaubteEingaben.add("w");
                    }

                    // Herausfinden wo man gelandet ist (Boolean Muss feld oder nicht)
                    Boolean istMussFeld = feldTypErkennen();

                    if (istMussFeld){
                        mussFeldScript();
                    }else {
                        // TODO An das Grundbuch senden, er soll den String geben für "Du bist auf ... gelandet, sie kostet..."
                        ausgabeText += "'a' um das neue Grundstück zu kaufen.\n";
                        erlaubteEingaben.add("a");
                    }
                }
                // Hier ist es egal ob man noch im Gefängnis ist oder nicht und alle Muss-Sachen wurden abgehandelt
                ausgabeText += "'ü' um die Übersicht über das Kapital zu öffnen.\n";
                erlaubteEingaben.add("ü");
                ausgabeText += "'z' um deinen Zug zu beenden.\n";
                erlaubteEingaben.add("z");

                // Eingabe erfragen und verarbeiten
                String eingabe = darsteller.eingabeFragen(ausgabeText,
                        erlaubteEingaben.toArray(new String[erlaubteEingaben.size()]));
                switch (eingabe){
                    case "a":
                        // TODO neues Grundstück kaufen implementieren
                        System.out.println("Hier würde ein neues Grundstück gekauft werden.");
                        break;
                    case "ü":
                        // TODO übersicht Aufrufen implementieren
                        System.out.println("Hier würde die Übersicht stehen.");
                        break;
                    case "w":
                        int [] wurf = würfel.würfeln();
                        if (würfel.mussInsGefängnis()){
                            // TODO ins Gefängnis für zu viele Pasche
                        }else {
                            spielleiter.spielerBewegen(wurf[2]);
                        }
                        darsteller.brettZeichnen();
                        darsteller.spielerHatGeworfen(wurf);
                        break;
                    case "z":
                        zugBeendet = true;
                        würfel.reset();
                        break;
                    default:
                        throw new IllegalStateException("Falscheingabe wurde nicht korrekt abgefangen!");
                }
            }
        }
    }

    private void gefängnisScipt(){
        System.out.println("Spieler ist im Gefängnis, kann aber noch nicht raus.");
    }

    // Unterscheidet zwischen Muss-Feld (Karten und Miete zahlen) und Kann-Feld (Neues Grundstück kaufen)
    private Boolean feldTypErkennen(){
        Boolean istMussFeld = false;
        // TODO Code zum erkennen ob Muss oder Kann Feld implementieren
        // aktuelles Feld von gerade dran nehmen und bei Kartenmanager und Grundbuch nachfragen
        return istMussFeld;
    }

    private void mussFeldScript(){
        // TODO Loop bis Muss Teil erfüllt ist
    }
}
