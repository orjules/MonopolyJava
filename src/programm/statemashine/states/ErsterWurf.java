package programm.statemashine.states;

import programm.statemashine.Kontext;
import programm.statemashine.enums.Ausgaben;
import programm.statemashine.enums.EingabeBeschreibungen;
import programm.statemashine.enums.Eingaben;
import programm.statemashine.io.AusgabeModell;
import programm.system.Würfel;
import programm.system.brett.Brett;
import programm.system.brett.Feld;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

import java.util.ArrayList;
import java.util.HashMap;

public class ErsterWurf extends State {
    public ErsterWurf(Kontext kontext, Würfel würfel, Spielleiter spielleiter, Brett brett) {
        super(kontext, würfel, spielleiter, brett);
    }

    @Override
    public AusgabeModell werfen(){
        int[] wurf = würfel.würfeln();
        Feld neuesFeld = brett.spielerBewegen(wurf[2]);
        Spieler geradeDran = spielleiter.getGeradeDran();
        HashMap<Eingaben, EingabeBeschreibungen> erlaubteEingaben = erlaubteEingabenErstellen(neuesFeld, geradeDran);
        kontext.setAktuellerState(kontext.getAufNeuemFeld());

        return new AusgabeModell(neuesFeld, geradeDran, brett, wurf, erlaubteEingaben, ausgabenErstellen());
    }

    @Override
    public AusgabeModell bestätigen(){
        return gehtNichtErstellen();
    }

    @Override
    public AusgabeModell übersicht(){
        return gehtNichtErstellen();
    }

    @Override
    public AusgabeModell zurück(){
        return gehtNichtErstellen();
    }

    private AusgabeModell gehtNichtErstellen(){
        Spieler geradeDran = spielleiter.getGeradeDran();
        HashMap<Eingaben, EingabeBeschreibungen> erlaubteEingaben = new HashMap<>();
        erlaubteEingaben.put(Eingaben.werfen, EingabeBeschreibungen.ersterWurf);
        ArrayList<Ausgaben> ausgaben = new ArrayList<>();
        ausgaben.add(Ausgaben.mussErstWürfeln);
        return new AusgabeModell(null, geradeDran, brett, new int[]{0,0,0}, erlaubteEingaben, ausgaben);
    }

    private ArrayList<Ausgaben> ausgabenErstellen(){
        ArrayList<Ausgaben> ausgaben = new ArrayList<>();
        ausgaben.add(Ausgaben.aufNeuemFeldGelandet);
        return ausgaben;
    }

    private HashMap<Eingaben, EingabeBeschreibungen> erlaubteEingabenErstellen(Feld neuesFeld, Spieler geradeDran){
        HashMap<Eingaben, EingabeBeschreibungen> erlaubteEingaben = new HashMap<>();
        erlaubteEingaben.put(Eingaben.übersicht, EingabeBeschreibungen.übersicht);
        if (neuesFeld.istGrundstück()){
            Spieler besitzer = neuesFeld.getBesitzer();
            if (besitzer != null && besitzer != geradeDran){
                // Miete zahlen
            }else {
                erlaubteEingaben.put(Eingaben.bestätigen, EingabeBeschreibungen.kaufen);
            }
        }
        return erlaubteEingaben;
    }
}
