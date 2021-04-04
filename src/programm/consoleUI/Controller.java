package programm.consoleUI;

import programm.statemashine.enums.EingabeBeschreibungen;
import programm.statemashine.enums.Eingaben;
import programm.statemashine.io.AusgabeModell;
import programm.statemashine.io.EingabeModell;

import java.util.HashMap;

public class Controller {

    KontextGrenze kontext;
    ConsoleHandler consoleHandler;
    AusgabeModell letzteAusgabe;

    public Controller(KontextGrenze kontext, ConsoleHandler consoleHandler) {
        this.kontext = kontext;
        this.consoleHandler = consoleHandler;
        letzteAusgabe = null;
    }

    public AusgabeModell eingabeErfragen(){
        Eingaben eingabe;
        if (letzteAusgabe == null){
            HashMap<Eingaben, EingabeBeschreibungen> ersteEingabe = new HashMap<>();
            ersteEingabe.put(Eingaben.werfen, EingabeBeschreibungen.ersterWurf);
            eingabe = consoleHandler.getEingabe(ersteEingabe);
        }else {
            eingabe = consoleHandler.getEingabe(letzteAusgabe.getErlaubteEingaben());
        }
        EingabeModell eingabeModell = new EingabeModell(eingabe);
        AusgabeModell ausgabeModell = kontext.erstelleModell(eingabeModell);
        letzteAusgabe = ausgabeModell;
        return ausgabeModell;
    }
}
