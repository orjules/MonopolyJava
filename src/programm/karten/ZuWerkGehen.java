package programm.karten;


import programm.grundstücke.Grundbuch;
import programm.system.Würfel;
import programm.system.spieler.Spielleiter;

public class ZuWerkGehen extends Ereigniskarte implements MussZahlen{


    protected ZuWerkGehen(String beschreibung) {
        super(beschreibung);
    }


    public String losgehen(Würfel würfel, Spielleiter spielleiter, Grundbuch grundbuch){
        // 1. nächstes Werk finden
        // 2. rausfinden ob es wem gehört
        // 2.1 Ja - return "Das Werk gehört niemandem"
        // 3. Würfeln
        // 4. Mit dem Wurf bei dem Werk die Miete berechnen
        // 5. Miete mit 10 multiplizieren und bei geradeDran abziehen und beim Besitzer dazu geben
        // 6. return "(Spieler) hat (wurf) gewürfelt und (Besitzer) (Miete mal 10) gezahlt."

        return "DEBUG: losgehen in ZuWerkGehen ist noch nicht implementiert.";
    }

    @Override
    public int getWert() {
        // TODO getWert soll den Wert ausrechnen, der als field gespeichert wird und hier zurückgegeben - getBeschreibung wird immer danach aufgerufen
        return 0;
    }

    @Override
    public String getBestätigung() {
        return null;
    }
}
