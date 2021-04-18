package programm.consoleUI;

import programm.grundstücke.Fälle;
import programm.grundstücke.Grundstück;
import programm.statemashine.enums.Ausgaben;
import programm.statemashine.enums.EingabeBeschreibungen;
import programm.statemashine.enums.Eingaben;
import programm.statemashine.io.AusgabeModell;
import programm.statemashine.io.EingabeModell;
import programm.system.spieler.Spieler;

import java.util.ArrayList;
import java.util.LinkedHashMap;

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
                case aufFreiemGrundstück -> beschreibung += wurfBeschreibung() + beschreibungFürFreiesGrundstück();
                case aufBesetztemGrundstück -> beschreibung += wurfBeschreibung() + beschreibungFürBesetztesGrundstück();
                case aufKartenFeld -> beschreibung += wurfBeschreibung() + "";
                case nichtsPassiert -> beschreibung += wurfBeschreibung() + "";
            }
        }
    }
    private String wurfBeschreibung(){
        int[] wurf = ausgabeModell.getLetzterWurf();
        if (wurf == null)
            throw new IllegalArgumentException("Der Wurf darf in diesen Fällen nicht null sein!");
        return "Du hast eine " + wurf[2] + " (" + wurf[0] + "," + wurf[1] + ") gewürfelt.\n";
    }

    private void inputFrageErstellen(){
        LinkedHashMap<Eingaben, EingabeBeschreibungen> erlaubteEingaben = ausgabeModell.getErlaubteEingaben();
        for (Eingaben e : erlaubteEingaben.keySet()){
            switch (e){
                case bestätigen -> inputFrage += "'a' ";
                case werfen -> inputFrage += "'w' ";
                case übersicht -> inputFrage += "'ü' ";
                case zurück -> inputFrage += "'z' ";
            }
            switch (erlaubteEingaben.get(e)){
                case ersterWurf -> inputFrage += "um zu würfeln";
                case kaufen -> inputFrage += "um es zu kaufen";
                case versteigern -> inputFrage += "um es an die anderen Spieler zu versteigern";
                case übersicht -> inputFrage += "um die Übersicht zu öffnen";
                case mieteZahlen -> inputFrage += "um die Miete zu zahlen";
            }
            inputFrage += "\n";
        }
    }

    private String beschreibungFürFreiesGrundstück(){
        Grundstück grundstück = ausgabeModell.getFeld().getGrundstück();
        String rückgabe = gemeinsamerAnfang(grundstück);
        rückgabe += grundstück.getPronomenGroß() + " ist zu verkaufen und kostet " + grundstück.getGrundstücksWert() + "€.";
        return rückgabe;
    }
    private String beschreibungFürBesetztesGrundstück(){
        Grundstück grundstück = ausgabeModell.getFeld().getGrundstück();
        String rückgabe = gemeinsamerAnfang(grundstück);

        Spieler besitzer = ausgabeModell.getFeld().getBesitzer();
        if (besitzer == null)
            throw new IllegalArgumentException("Das Grundstück muss einen Besitzer haben!");
        rückgabe += grundstück.getPronomenGroß() + " gehört " + besitzer.getName() +
                " und die Miete kostet " + grundstück.mieteBerechnen(besitzer, ausgabeModell.getBrett(), ausgabeModell.getLetzterWurf()[2]) + "€.";
        return rückgabe;
    }
    private String gemeinsamerAnfang(Grundstück grundstück){
        if (grundstück == null)
            throw new IllegalArgumentException("Das Feld muss ein Grundstück enthalten.");
        String rückgabe = "Du bist auf ";
        rückgabe += grundstück.getArtikelKlein(Fälle.Dativ) + " " + grundstück.getName() + " gelandet. ";
        return rückgabe;
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
