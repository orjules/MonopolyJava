package tests.system.Mocks;

import programm.system.interfaces.IDarsteller;

public class DarstellerMock implements IDarsteller {
    String festeEingabe;
    String ausgabeErgebis;

    public DarstellerMock(String festeEingabe) {
        this.festeEingabe = festeEingabe;
    }

    public String eingabeFragen(String ausgabe, String[] erlaubteEingaben){
        ausgabeErgebis = ausgabe;
        return festeEingabe;
    }
    public void spielerHatGeworfen(int[] wurf){}
    public void brettZeichnen(){}
    public void ausgabe(String text){
        ausgabeErgebis = text;
    }
    public void umbruch(){}

    public String getAusgabeErgebis(){
        return ausgabeErgebis;
    }
}
