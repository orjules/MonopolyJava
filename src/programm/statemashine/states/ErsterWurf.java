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

        Object[] ausgabeEingabe = eingabeUndAusgabeErstellen(neuesFeld, geradeDran);

        return new AusgabeModell(neuesFeld, geradeDran, brett, wurf,
                (HashMap<Eingaben, EingabeBeschreibungen>) ausgabeEingabe[0], (ArrayList<Ausgaben>)ausgabeEingabe[1]);
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
        return new AusgabeModell(null, geradeDran, brett, null, erlaubteEingaben, ausgaben);
    }

    private Object[] eingabeUndAusgabeErstellen(Feld neuesFeld, Spieler geradeDran){
        HashMap<Eingaben, EingabeBeschreibungen> erlaubteEingaben = new HashMap<>();
        ArrayList<Ausgaben> ausgaben = new ArrayList<>();

        erlaubteEingaben.put(Eingaben.übersicht, EingabeBeschreibungen.übersicht);
        if (neuesFeld.istGrundstück()){
            Spieler besitzer = neuesFeld.getBesitzer();
            if (besitzer != null){
                if (besitzer != geradeDran){
                    erlaubteEingaben.put(Eingaben.bestätigen, EingabeBeschreibungen.mieteZahlen);
                    ausgaben.add(Ausgaben.aufBesetztemGrundstück);
                    kontext.setAktuellerState(kontext.getAufBesetztemGrundstück());
                }else {
                    erlaubteEingaben.put(Eingaben.zurück, EingabeBeschreibungen.zugBeenden);
                    ausgaben.add(Ausgaben.nichtsPassiert);
                    kontext.setAktuellerState(kontext.getAllesErledigt());
                }
            }else {
                erlaubteEingaben.put(Eingaben.bestätigen, EingabeBeschreibungen.kaufen);
                erlaubteEingaben.put(Eingaben.zurück, EingabeBeschreibungen.versteigern);
                ausgaben.add(Ausgaben.aufFreiemGrundstück);
                kontext.setAktuellerState(kontext.getAufFreiemGrundstück());
            }
        }else {
            erlaubteEingaben.put(Eingaben.bestätigen, EingabeBeschreibungen.karteBestätigen);
            ausgaben.add(Ausgaben.aufKartenFeld);
            kontext.setAktuellerState(kontext.getAufKarte());
        }

        if (brett.istÜberLosGegangen()){
            ausgaben.add(Ausgaben.überLosGegangen);
        }

        return new Object[]{erlaubteEingaben, ausgaben};
    }
}
