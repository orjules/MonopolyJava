package programm.consoleUI;

import programm.statemashine.io.AusgabeModell;
import programm.statemashine.io.EingabeModell;

public interface KontextGrenze {
    AusgabeModell erstelleModell(EingabeModell eingabe);
}
