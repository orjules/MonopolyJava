package programm.grundstücke;

import java.util.HashMap;
import java.util.Map;

public class GrammatikHandler {

    // Braucht die Bounded Wild Card damit es funktioniert wie ich will
    private static Map<Grundstück, String[]> pronomen = listeHolen(1);
    private static Map<Grundstück, String[]> artikel = listeHolen(2);

    private static Map<Grundstück, String[]> listeHolen(int nrDerList) {
        Map<Grundstück, String[]> rückgabe = new HashMap<>();
        Straße vergleichsStraße = new Straße("straße", null, 0,0,null,
                0,0,0,0,0,0, 0);
        Bahnhof vergleichsBahnhof = new Bahnhof("bahnhof", null);
        Werk vergleichswerk = new Werk("werk", null, 0,0);

        if (nrDerList == 1){
            rückgabe.put(vergleichsStraße, new String[]{"Sie ","sie "});
            rückgabe.put(vergleichsBahnhof, new String[]{"Er ","er "});
            rückgabe.put(vergleichswerk, new String[]{"Es ","es "});
            return rückgabe;
        }else {
            rückgabe.put(vergleichsStraße, new String[]{"Die ", "die ", "Der ", "der "});
            rückgabe.put(vergleichsBahnhof, new String[]{"Der ", "der ", "Dem ", "dem "});
            rückgabe.put(vergleichswerk, new String[]{"Das ", "das ", "Dem ", "dem "});
            return rückgabe;
        }
    }

    public static String getGroßesPronomenFür(Grundstück grundstück){
        return iterater(grundstück, pronomen, 0);
    }

    public static String getkleinesPronoemenFür(Grundstück grundstück){
        return iterater(grundstück, pronomen, 1);
    }

    public static String getGroßeNominativArtikel(Grundstück grundstück) {
        return iterater(grundstück, artikel, 0);
    }

    public static String getkleineNominativArtikel(Grundstück grundstück) {
        return iterater(grundstück, artikel, 1);
    }

    public static String getGroßeDativArtikel(Grundstück grundstück) {
        return iterater(grundstück, artikel, 2);
    }

    public static String getkleineDativArtikel(Grundstück grundstück) {
        return iterater(grundstück, artikel, 3);
    }

    private static String iterater(Grundstück grundstück, Map<Grundstück, String[]> map, int index){
        for (Grundstück grund: map.keySet()){
            if (grundstück.getClass().equals(grund.getClass()))
                return map.get(grund)[index];
        }
        throw new IllegalArgumentException("Dieses Grundstück sollte es nicht geben!");
    }
}
