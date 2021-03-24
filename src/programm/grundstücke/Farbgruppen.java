package programm.grundstücke;

import programm.system.spieler.Spieler;

public class Farbgruppen {
    private Farben farbe;
    private Straße[] zugehörigeStraßen;
    private boolean istKomplett;
    private Grundbuch grundbuch;

    public Farbgruppen(Farben farbe, Straße[] zugehörigeStraßen, Grundbuch grundbuch) {
        this.farbe = farbe;
        this.zugehörigeStraßen = zugehörigeStraßen;
        this.istKomplett = false;
        this.grundbuch = grundbuch;
    }


    public boolean istkomplett() {
        if(istKomplett){
            return true;
        }
        checkStatus();
        return istKomplett;
    }
    private void checkStatus(){
        Spieler letzteSpieler = null;
        for (Straße s : zugehörigeStraßen){
            if (letzteSpieler != null && !letzteSpieler.equals(grundbuch.getBesitzerVon(s))) {
                return;
            }
            letzteSpieler = grundbuch.getBesitzerVon(s);
        }
        istKomplett = true;
    }
}
