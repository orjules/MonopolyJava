package programm.grundstücke;

import programm.system.spieler.Spieler;

public class Farbgruppen {
    private Farben farbe;
    private Straße[] zugehörigeStraßen;
    private boolean istKomplett;

    public Farbgruppen(Farben farbe, Straße[] zugehörigeStraßen) {
        this.farbe = farbe;
        this.zugehörigeStraßen = zugehörigeStraßen;
        this.istKomplett = false;
    }


    public boolean istkomplett(Grundbuch grundbuch) {
        Spieler letzteSpieler = null;
        for (Straße s : zugehörigeStraßen){
            if (letzteSpieler != null && !letzteSpieler.equals(grundbuch.getBesitzerVon(s))) {
                return false;
            }
            letzteSpieler = grundbuch.getBesitzerVon(s);
        }
        return true;
    }
}
