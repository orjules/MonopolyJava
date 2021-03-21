package programm.grundstücke;

public class GrammatikHandler {

    public static String artikelFür(Grundstück grundstück, boolean tNom_fDativ, boolean istGroß){
        if (tNom_fDativ){
            if (grundstück.getClass().equals(Bahnhof.class)){
                if (istGroß){
                    return "Der ";
                }else {
                    return "der ";
                }
            }else if (grundstück.getClass().equals(Straße.class)) {
                if (istGroß){
                    return "Die ";
                }else {
                    return "die ";
                }
            }else if (grundstück.getClass().equals(Werk.class)){
                if (istGroß){
                    return "Das ";
                }else {
                    return "das ";
                }
            }else
                throw new IllegalArgumentException("Gegebenes Grundstück ist kein Grundstück oder es ist null.");
        }else {
            if (grundstück.getClass().equals(Bahnhof.class) || grundstück.getClass().equals(Werk.class)){
                if (istGroß){
                    return "Dem ";
                }else {
                    return "dem ";
                }
            }else if (grundstück.getClass().equals(Straße.class)){
                if (istGroß){
                    return "Der ";
                }else {
                    return "der ";
                }
            }else
                throw new IllegalArgumentException("Gegebenes Grundstück ist kein Grundstück oder es ist null.");
        }
    }

    public static String pronomenFür(Grundstück grundstück, boolean istGroß){
        if (grundstück.getClass().equals(Bahnhof.class)){
            if (istGroß){
                return "Er ";
            }else {
                return "er ";
            }
        }else if (grundstück.getClass().equals(Straße.class)) {
            if (istGroß){
                return "Sie ";
            }else {
                return "sie ";
            }
        }else if (grundstück.getClass().equals(Werk.class)){
            if (istGroß){
                return "Es ";
            }else {
                return "es ";
            }
        }else
            throw new IllegalArgumentException("Gegebenes Grundstück ist kein Grundstück oder es ist null.");
    }
}
