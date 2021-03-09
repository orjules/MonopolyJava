package programm.system;

import java.util.Arrays;

public class Darsteller {

    Spielleiter spielleiter;

    public Darsteller(Spielleiter spielleiter) {
        this.spielleiter = spielleiter;
    }

    public void brettZeichnen(){
        Tabelle tab = new Tabelle(new String[]{"Felder", "Spieler"});
        for (Felder feld : Felder.values()){
            String[] spielerSymbole = new String[spielleiter.getAlleSpieler().length];
            int i = 0;
            for (Spieler spieler : spielleiter.getAlleSpieler()){
                if (spieler.getAktuellePos() == feld){
                    spielerSymbole[i] = Character.toString(spieler.getSymbol());
                }
                i++;
            }
            if (isEmpty(spielerSymbole)){
                tab.addZeile(new String[]{feld.name(), ""});
            }else {
                tab.addZeile(new String[]{feld.name(), String.join(", ", spielerSymbole)});
            }
        }
        tab.printTabelle();
    }

    private Boolean isEmpty(String[] liste){
        for(String wert : liste){
            if (wert != null){
                return false;
            }
        }
        return true;
    }
}
