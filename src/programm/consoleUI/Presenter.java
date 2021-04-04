package programm.consoleUI;

import programm.statemashine.io.Ausgabe;
import programm.statemashine.io.NeuesAusgabeModell;

import java.util.ArrayList;

public class Presenter {

    ConsoleHandler consoleHandler;

    public Presenter(ConsoleHandler consoleHandler) {
        this.consoleHandler = consoleHandler;
    }

    public void present(NeuesAusgabeModell ausgabe) {
        ArrayList<Ausgabe> ausgaben = ausgabe.getAusgaben();
        if (ausgaben.isEmpty()){
            consoleHandler.ausgeben("Hello World!");
        }
    }
}
