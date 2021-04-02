package programm.newSystem;

import java.util.ArrayList;

public class AusgabeModell {

    ArrayList<MöglicheAusgaben> ausgaben;

    public AusgabeModell(ArrayList<MöglicheAusgaben> ausgaben) {
        this.ausgaben = ausgaben;
    }

    public ArrayList<MöglicheAusgaben> getAusgaben() {
        return ausgaben;
    }
}
