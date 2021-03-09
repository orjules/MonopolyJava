package programm;

import programm.system.Tabelle;
import programm.system.Würfel;

public class Main {

    public static void main(String[] args) {
        Tabelle tab = new Tabelle(new String[]{"Erster Kopf", "Zweiter Kopf"});
        tab.addZeile(new String[]{"kurz", "auch kurz"});
        tab.addZeile(new String[]{"kur 2", "sehr langes Wort"});
        tab.addZeile(new String[]{"mh", "super langes Testwort"});
        tab.printTabelle();
    }
}
