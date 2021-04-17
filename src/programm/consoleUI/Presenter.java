package programm.consoleUI;

import programm.statemashine.Kontext;
import programm.statemashine.enums.Ausgaben;
import programm.statemashine.enums.EingabeBeschreibungen;
import programm.statemashine.enums.Eingaben;
import programm.statemashine.io.AusgabeModell;
import programm.statemashine.io.EingabeModell;
import programm.system.spieler.Spieler;

import java.util.ArrayList;
import java.util.HashMap;

public class Presenter {

    private ConsoleHandler consoleHandler;
    private KontextGrenze kontextGrenze;

    private String spielerDaten = "";
    private String feldMenü = "";
    private String beschreibung = "";
    private String inputFrage = "";

    private AusgabeModell ausgabeModell;

    public Presenter(KontextGrenze kontextGrenze, ConsoleHandler consoleHandler) {
        this.kontextGrenze = kontextGrenze;
        this.consoleHandler = consoleHandler;
    }

    public void spielLaufenLassen(){
        // Wird in einem loop in main am laufen gehalten

        ausgabeModell = kontextGrenze.erstelleModell(new EingabeModell(Eingaben.bestätigen));

        feldMenüErstellen();
        spielerDatenErstellen();
        beschreibungErstellen();
        inputFrageErstellen();

        // Eingabe erfragen und speichern
    }

    // TODO nach der Ausgabe müssen die Strings zurückgesetzt werden

    private void feldMenüErstellen(){

    }

    private void spielerDatenErstellen(){
        Spieler geradeDran = ausgabeModell.getGeradeDran();
        spielerDaten = geradeDran.toString() + " ist dran und besitzt momentan " + geradeDran.getKapital() + "€.";
    }

    private void beschreibungErstellen(){
        ArrayList<Ausgaben> ausgaben = ausgabeModell.getAusgaben();
        for (Ausgaben a : ausgaben){
            switch (a){
                case mussErstWürfeln -> beschreibung += "Du bist dran und musst als erster Würfeln.";
            }
        }
    }

    private void inputFrageErstellen(){
        HashMap<Eingaben, EingabeBeschreibungen> erlaubteEingaben = ausgabeModell.getErlaubteEingaben();
        for (Eingaben e : erlaubteEingaben.keySet()){
            switch (erlaubteEingaben.get(e)){
                case ersterWurf -> inputFrage += "'w' um zu würfeln";
            }
        }
    }

    public String getFeldMenü() {
        return feldMenü;
    }

    public String getSpielerDaten() {
        return spielerDaten;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public String getInputFrage() {
        return inputFrage;
    }
}
