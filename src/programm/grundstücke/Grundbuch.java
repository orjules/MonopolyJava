package programm.grundstücke;

import programm.system.spieler.Spieler;
import programm.system.Felder;

import java.util.HashMap;
import java.util.Map;


public class Grundbuch{

    Grundstück[] alleGrundstücke = new Grundstück[6]; // Vorläufig 6
    Farbgruppen[] alleFarbgruppen = new Farbgruppen[2]; // Vorläufig 2
    Map<Grundstück, Spieler> grundbuch = new HashMap<>();

    public Grundbuch() {
        // hier die Werte für alleGrundstücke initialisieren
        alleGrundstücke[0] = new Straße(Felder.Badstraße.name(), Felder.Badstraße, 60, 30, Farben.braun, 2, 10, 30,90, 160, 250, 50);
        alleGrundstücke[1] = new Straße(Felder.Turmstraße.name(), Felder.Turmstraße, 60, 30, Farben.braun, 4, 20, 60,180, 320, 450, 50);
        alleGrundstücke[2] = new Bahnhof(Felder.Südbahnhof.name(), Felder.Südbahnhof);
        alleGrundstücke[3] = new Straße(Felder.Chaussestraße.name(), Felder.Chaussestraße, 100, 50, Farben.hellblau, 6, 30, 90,270, 400, 550, 50);
        alleGrundstücke[4] = new Straße(Felder.Elisenstraße.name(), Felder.Elisenstraße, 100, 50, Farben.hellblau, 6, 30, 90,270, 400, 550, 50);
        alleGrundstücke[5] = new Straße(Felder.Poststraße.name(), Felder.Poststraße, 120, 60, Farben.hellblau, 8, 40, 100,300, 450, 600, 50);
        // hier die Werte für alleFarbgruppen initialisieren
        alleFarbgruppen[0] = new Farbgruppen(Farben.braun, new Straße[]{(Straße) alleGrundstücke[0], (Straße) alleGrundstücke[1]});
        alleFarbgruppen[1] = new Farbgruppen(Farben.hellblau, new Straße[]{(Straße) alleGrundstücke[3], (Straße) alleGrundstücke[4], (Straße) alleGrundstücke[5]});
        // hier die Werte für grundbuch initialisieren
        for (Grundstück grund : alleGrundstücke){
            grundbuch.put(grund, null);
        }
    }

    public Grundstück grundstückVon(Felder feld){
        for (Grundstück grund : alleGrundstücke){
            if (grund.feld == feld){
                return grund;
            }
        }
        return null;
    }

    public Boolean istZuVerkaufen(Grundstück grundstück){
        System.out.println("Debug: Abfrage ob zu verkaufen.");
        return true;
    }

    // Es gibt drei pronomen, die unterschieden werden müssen wobei werk und Bahnhof im Dativ beide "dem" sind
    public String textFürGelandetAuf(Grundstück grundstück){
        if (grundstück.getClass().equals(Bahnhof.class) || grundstück.getClass().equals(Werk.class)){
            return "Du bist auf dem " + grundstück.getName() + " gelandet.";
        }else if (grundstück.getClass().equals(Straße.class)){
            return "Du bist auf der " + grundstück.getName() + " gelandet.";
        }else
            throw new IllegalArgumentException("Gegebenes Grundstück ist kein Grundstück oder es ist null.");
    }

}
