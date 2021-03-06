package programm.karten;


import programm.grundstücke.Grundbuch;
import programm.grundstücke.Grundstück;
import programm.system.Felder;
import programm.system.Würfel;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

import java.util.Random;

public class ZuWerkGehen extends Ereigniskarte implements MussZahlen{

    private Spielleiter spielleiter;
    private Grundbuch grundbuch;

    private String zweiteBeschreibung = "";
    private Felder werk;
    private int miete;
    private Spieler besitzer;
    private int[] wurf;
    private boolean gehtÜberLos = false;

    public ZuWerkGehen(String beschreibung, Spielleiter spielleiter, Grundbuch grundbuch) {
        super(beschreibung);
        this.spielleiter = spielleiter;
        this.grundbuch = grundbuch;
    }

    @Override
    public int getWert() {
        feldVomNächstenWerkFinden();
        if (gehtÜberLos){
            zweiteBeschreibung += "Du wirst über Los gehen und 200€ einziehen. ";
        }
        besitzerFindenVon(werk);
        if (besitzer == null){
            zweiteBeschreibung += "Du wirst auf dem " + werk.name() + " landen. Es gehört noch niemandem.";
            return 0;
        }else{
            mieteBerechnen();
            zweiteBeschreibung += "Du wirst auf dem "+werk.name()+" landen, welches "+besitzer.toString()+
                    " gehört. Weil du eine "+wurf[2]+" gewürfelt hast, musst du "+miete+" zahlen.";
            return miete;
        }
    }

    @Override
    public String getBeschreibung(){
        return beschreibung + "\n" + zweiteBeschreibung;
    }

    public void bestätigen(){
        spielleiter.spielerSetzten(werk);
        if (gehtÜberLos)
            spielleiter.kapitalÄndernVon(spielleiter.getGeradeDran(), 200);
        spielleiter.kapitalÄndernVon(spielleiter.getGeradeDran(), -miete);
        spielleiter.kapitalÄndernVon(besitzer, miete);
    }

    @Override
    public String getBestätigung() {
        Spieler geradeDran = spielleiter.getGeradeDran();
        String ausgabe = "";
        if (gehtÜberLos){
            ausgabe += "Du bist über Los geganen und hast 200€ eingezogen. ";
        }
        if (besitzer == null){
            ausgabe += geradeDran.toString()+" ist nun auf dem "+werk.name()+".";
        }else
            ausgabe +=  geradeDran.toString() + " ist auf dem " + werk.name() + " von " + besitzer.toString() +
                " gelandet und hat " + miete + "€ gezahlt.";
        reset();
        return ausgabe;
    }

    private void feldVomNächstenWerkFinden(){
        int spielerPos = spielleiter.getGeradeDran().getAktuellePos().ordinal();

        for (int i = spielerPos; i < Felder.values().length; i++){
            if (Felder.values()[i].equals(Felder.Elektrizitätswerk)) {  // Oder das Wasserwerk
                werk = Felder.values()[i];
                return;
            }
        }
        gehtÜberLos = true;
        for (Felder feld : Felder.values()){
            if (feld.equals(Felder.Elektrizitätswerk)) {  // Oder das Wasserwerk
                werk = feld;
                return;
            }
        }
        throw new IllegalStateException("Es wurde kein Werk gefunden, was nicht passieren sollte");
    }
    private void besitzerFindenVon(Felder feld){
        Grundstück werk = grundbuch.grundstückVon(feld);
        besitzer = grundbuch.getBesitzerVon(werk);
    }
    private void mieteBerechnen(){
        // Neuer Würfel, weil die pasche nicht gespeichert werden sollen
        Würfel würfel = new Würfel(new Random());
        wurf = würfel.würfeln();
        miete = wurf[2] * 10;
    }

    private void reset(){
        zweiteBeschreibung = "";
        werk = null;
        miete = 0;
        besitzer = null;
        wurf = new int[]{0,0,0};
        gehtÜberLos = false;
    }

    // Für die Tests
    public int[] getWurf(){
        return wurf;
    }
    public Spieler getBesitzer(){
        return besitzer;
    }
}
