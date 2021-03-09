package programm.system;

public class Organisator {
    Spielleiter spielleiter;
    Darsteller darsteller;
    Würfel würfel;

    public Organisator(Spielleiter spielleiter, Darsteller darsteller, Würfel würfel) {
        this.spielleiter = spielleiter;
        this.darsteller = darsteller;
        this.würfel = würfel;
    }

    public void testWurf(){
        darsteller.brettZeichnen();
        String rückgabe = darsteller.eingabeFragen(spielleiter.getGeradeDran().getName() + " ("
                + Character.toString(spielleiter.getGeradeDran().getSymbol()) + ") ist gerade dran." +
                "\n'w' um zu würfeln.", new String[]{"w"});
        if (rückgabe.equals("w")){
            int[] wurf = würfel.würfeln();
            darsteller.spielerHatGeworfen(wurf);
            spielleiter.spielerBewegen(wurf[2]);
            darsteller.brettZeichnen();
        }

    }
}
