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
import java.util.LinkedHashMap;

public class AufFreiemGrundstück extends State{

    public AufFreiemGrundstück(Kontext kontext, Würfel würfel, Spielleiter spielleiter, Brett brett) {
        super(kontext, würfel, spielleiter, brett);
    }

    @Override
    public AusgabeModell werfen(){
        return kontext.getLetzteAusgabe();
    }

    @Override
    public AusgabeModell bestätigen(){
        Spieler geradeDran = spielleiter.getGeradeDran();
        Feld aktuellesFeld = brett.getAktuellesFeldVon(geradeDran);

        spielleiter.kapitalÄndernVon(geradeDran, brett.mieteVon(aktuellesFeld));
        aktuellesFeld.setBesitzer(geradeDran);
        kontext.setAktuellerState(kontext.getAllesErledigt());

        LinkedHashMap<Eingaben, EingabeBeschreibungen> erlaubt = new LinkedHashMap<>();
        erlaubt.put(Eingaben.übersicht, EingabeBeschreibungen.übersicht);
        if (würfel.darfNochmalWerfen()){
            erlaubt.put(Eingaben.werfen, EingabeBeschreibungen.nochmalWerfen);
        }
        erlaubt.put(Eingaben.zurück, EingabeBeschreibungen.zugBeenden);
        ArrayList<Ausgaben> ausgaben = new ArrayList<>();
        ausgaben.add(Ausgaben.fertig);
        ausgaben.add(Ausgaben.gekauft);

        return new AusgabeModell(aktuellesFeld, geradeDran, brett, null, erlaubt, ausgaben);
    }

    @Override
    public AusgabeModell übersicht(){
        Spieler geradeDran = spielleiter.getGeradeDran();

        kontext.setAktuellerState(kontext.getÜbersicht());

        LinkedHashMap<Eingaben, EingabeBeschreibungen> erlaubt = new LinkedHashMap<>();
        erlaubt.put(Eingaben.zurück, EingabeBeschreibungen.zurück);
        ArrayList<Ausgaben> ausgaben = new ArrayList<>();
        ausgaben.add(Ausgaben.übersicht);

        return new AusgabeModell(null, geradeDran, brett, null, erlaubt, ausgaben);
    }
}
