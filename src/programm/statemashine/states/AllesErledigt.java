package programm.statemashine.states;

import programm.statemashine.Kontext;
import programm.statemashine.enums.Ausgaben;
import programm.statemashine.enums.EingabeBeschreibungen;
import programm.statemashine.enums.Eingaben;
import programm.statemashine.io.AusgabeModell;
import programm.system.Würfel;
import programm.system.brett.Brett;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class AllesErledigt extends State{
    public AllesErledigt(Kontext kontext, Würfel würfel, Spielleiter spielleiter, Brett brett) {
        super(kontext, würfel, spielleiter, brett);
    }

    @Override
    public AusgabeModell bestätigen(){
        return kontext.getLetzteAusgabe();
    }

    @Override
    public AusgabeModell zurück(){
        spielleiter.weiter();
        kontext.setAktuellerState(kontext.getErsterWurf());
        würfel.reset();

        return modellFürZurückErstellen();
    }
    private AusgabeModell modellFürZurückErstellen(){
        Spieler geradeDran = spielleiter.getGeradeDran();
        LinkedHashMap<Eingaben, EingabeBeschreibungen> erlaubt = new LinkedHashMap<>();
        erlaubt.put(Eingaben.werfen, EingabeBeschreibungen.ersterWurf);
        ArrayList<Ausgaben> ausgaben = new ArrayList<>();
        ausgaben.add(Ausgaben.mussErstWürfeln);
        return new AusgabeModell(null, geradeDran, brett, null, erlaubt, ausgaben);
    }

    @Override
    public AusgabeModell übersicht(){
        kontext.setAktuellerState(kontext.getÜbersicht());

        Spieler geradeDran = spielleiter.getGeradeDran();
        LinkedHashMap<Eingaben, EingabeBeschreibungen> erlaubt = new LinkedHashMap<>();
        erlaubt.put(Eingaben.zurück, EingabeBeschreibungen.zurück);
        ArrayList<Ausgaben> ausgaben = new ArrayList<>();
        ausgaben.add(Ausgaben.übersicht);
        return new AusgabeModell(null, geradeDran, brett, null, erlaubt, ausgaben);
    }

    @Override
    public AusgabeModell werfen(){
        if (würfel.darfNochmalWerfen()){
            return kontext.getErsterWurf().werfen();
        }else {
            return kontext.getLetzteAusgabe();
        }
    }
}
