package tests.system.mocks;

import programm.system.Darsteller;
import programm.system.spieler.Spielleiter;

import java.util.ArrayList;

public class MockDarsteller extends Darsteller {

    private String mitschrift = "";
    private ArrayList<String> festeAusgaben;

    public MockDarsteller(Spielleiter spielleiter, ArrayList<String> festeAusgaben) {
        super(spielleiter);
        this.festeAusgaben = festeAusgaben;
    }


    public String getMitschrift() {
        return mitschrift;
    }

    @Override
    public String eingabeFragen(String ausgabe, String[] erlaubteEingaben){
        mitschrift += ausgabe;
        if (festeAusgaben.size() > 1){
            return festeAusgaben.remove(0);
        }else{
            return festeAusgaben.get(0);
        }
    }

    @Override
    public void ausgabe(String text){
        mitschrift += text;
    }
}
