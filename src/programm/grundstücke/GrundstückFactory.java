package programm.grundstücke;

import programm.system.Felder;

public class GrundstückFactory {

    public static Grundstück[] erstelleAlleGrundstücke(){
        Grundstück[] alleGrundstücke = new Grundstück[11]; // Vorläufig 11
        alleGrundstücke[0] = new Straße(Felder.Badstraße.name(), Felder.Badstraße, 60, 30, Farben.braun, 2, 10, 30,90, 160, 250, 50);
        alleGrundstücke[1] = new Straße(Felder.Turmstraße.name(), Felder.Turmstraße, 60, 30, Farben.braun, 4, 20, 60,180, 320, 450, 50);
        alleGrundstücke[2] = new Bahnhof(Felder.Südbahnhof.name(), Felder.Südbahnhof);
        alleGrundstücke[3] = new Straße(Felder.Chaussestraße.name(), Felder.Chaussestraße, 100, 50, Farben.hellblau, 6, 30, 90,270, 400, 550, 50);
        alleGrundstücke[4] = new Straße(Felder.Elisenstraße.name(), Felder.Elisenstraße, 100, 50, Farben.hellblau, 6, 30, 90,270, 400, 550, 50);
        alleGrundstücke[5] = new Straße(Felder.Poststraße.name(), Felder.Poststraße, 120, 60, Farben.hellblau, 8, 40, 100,300, 450, 600, 50);
        alleGrundstücke[6] = new Straße(Felder.Seestraße.name(), Felder.Seestraße, 140, 60, Farben.rosa, 8, 40, 100,300, 450, 600, 50);     // Bis auf Kaufpreis sind die Werte falsch
        alleGrundstücke[7] = new Werk(Felder.Elektrizitätswerk.name(), Felder.Elektrizitätswerk, 150, 60);                                                                                                                          // Hypothekswert ist falsch
        alleGrundstücke[8] = new Straße(Felder.Hafenstraße.name(), Felder.Hafenstraße, 120, 60, Farben.rosa, 8, 40, 100,300, 450, 600, 50); // Bis auf Kaufpreis sind die Werte falsch
        alleGrundstücke[9] = new Straße(Felder.NeueStraße.name(), Felder.NeueStraße, 120, 60, Farben.rosa, 8, 40, 100,300, 450, 600, 50);   // Bis auf Kaufpreis sind die Werte falsch
        alleGrundstücke[10] = new Bahnhof(Felder.Westbahnhof.name(), Felder.Westbahnhof);
        return alleGrundstücke;
    }

    public static Farbgruppen[] erstelleAlleFarbgruppen(){
        Grundstück[] alleGrundstücke = erstelleAlleGrundstücke();
        Farbgruppen[] alleFarbgruppen = new Farbgruppen[3];
        alleFarbgruppen[0] = new Farbgruppen(Farben.braun, new Straße[]{(Straße) alleGrundstücke[0], (Straße) alleGrundstücke[1]});
        alleFarbgruppen[1] = new Farbgruppen(Farben.hellblau, new Straße[]{(Straße) alleGrundstücke[3], (Straße) alleGrundstücke[4], (Straße) alleGrundstücke[5]});
        alleFarbgruppen[2] = new Farbgruppen(Farben.rosa, new Straße[]{(Straße) alleGrundstücke[6], (Straße) alleGrundstücke[8], (Straße) alleGrundstücke[9]});
        return alleFarbgruppen;
    }


}
