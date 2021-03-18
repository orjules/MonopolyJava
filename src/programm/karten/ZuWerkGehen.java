package programm.karten;


import programm.grundstücke.Grundbuch;
import programm.grundstücke.Grundstück;
import programm.system.Felder;
import programm.system.Würfel;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

public class ZuWerkGehen extends Ereigniskarte implements MussZahlen{

    private Spielleiter spielleiter;
    private Grundbuch grundbuch;

    private String zweiteBeschreibung;
    private Felder werk;
    private int miete;
    private Spieler besitzer;

    public ZuWerkGehen(String beschreibung, Spielleiter spielleiter, Grundbuch grundbuch) {
        super(beschreibung);
        this.spielleiter = spielleiter;
        this.grundbuch = grundbuch;
    }

    @Override
    public int getWert() {
        feldVomNächstenWerkFinden();
        besitzerFindenVon(werk);
        if (besitzer == null){
            zweiteBeschreibung =  "Du bist auf dem " + werk.name() + " gelandet. Es geöhrt noch niemandem.";
        }
        mieteBerechnen();
        zweiteBeschreibung = "Du bist auf dem " + werk.name() + " von " + besitzer.toString() +
                " gelandet und musst " + miete + "€ zahlen.";
        return miete;
    }

    @Override
    public String getBeschreibung(){
        return beschreibung + "\n" + zweiteBeschreibung;
    }

    public void bestätigen(){
        spielleiter.kapitalÄndernVon(spielleiter.getGeradeDran(), -miete);
        spielleiter.spielerSetzten(werk);
    }

    @Override
    public String getBestätigung() {
        return spielleiter.getGeradeDran().toString() + " ist auf dem " + werk.name() + " von " + besitzer.toString() +
                " gelandet und hat " + miete + "€ gezahlt.";
    }

    private void feldVomNächstenWerkFinden(){
        for (Felder feld : Felder.values()){
            if (feld.ordinal() > spielleiter.getGeradeDran().getAktuellePos().ordinal()){
                if (feld.equals(Felder.Elektrizitätswerk)) {  // Oder das Wasserwerk
                    werk = feld;
                    return;
                }
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
        Würfel würfel = new Würfel();
        int[] wurf = würfel.würfeln();
        miete = wurf[2] * 10;
    }
}
