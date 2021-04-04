package programm.consoleUI;

import programm.statemashine.enums.EingabeBeschreibungen;
import programm.statemashine.enums.Eingaben;
import programm.statemashine.io.NeuesAusgabeModell;
import programm.statemashine.io.NeuesEingabeModell;

import java.util.HashMap;

public class Controller {

    KontextGrenze kontext;
    ConsoleHandler consoleHandler;
    NeuesAusgabeModell letzteAusgabe;

    public Controller(KontextGrenze kontext, ConsoleHandler consoleHandler) {
        this.kontext = kontext;
        this.consoleHandler = consoleHandler;
        letzteAusgabe = null;
    }

    public NeuesAusgabeModell eingabeErfragen(){
        Eingaben eingabe;
        if (letzteAusgabe == null){
            HashMap<Eingaben, EingabeBeschreibungen> ersteEingabe = new HashMap<>();
            ersteEingabe.put(Eingaben.werfen, EingabeBeschreibungen.ersterWurf);
            eingabe = consoleHandler.getEingabe(ersteEingabe);
        }else {
            eingabe = consoleHandler.getEingabe(letzteAusgabe.getErlaubteEingaben());
        }
        NeuesEingabeModell eingabeModell = new NeuesEingabeModell(eingabe);
        NeuesAusgabeModell ausgabeModell = kontext.erstelleModell(eingabeModell);
        letzteAusgabe = ausgabeModell;
        return ausgabeModell;
    }
}
