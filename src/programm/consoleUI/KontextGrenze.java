package programm.consoleUI;

import programm.statemashine.io.NeuesAusgabeModell;
import programm.statemashine.io.NeuesEingabeModell;

public interface KontextGrenze {
    NeuesAusgabeModell erstelleModell(NeuesEingabeModell eingabe);
}
