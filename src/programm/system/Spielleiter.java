package programm.system;

public class Spielleiter {
    private Spieler[] alleSpieler = new Spieler[] {
            new Spieler("Günther", '#', Felder.Los, false, 1500),
            new Spieler("Monika", '?', Felder.Los, false, 1500),
            new Spieler("Detlef", '!', Felder.Los, false, 1500),
    };
    private int geradeDran = 0;

    public Spieler getGeradeDran(){
        return alleSpieler[geradeDran];
    }

    public Spieler[] getAlleSpieler() {
        return alleSpieler;
    }

    public void weiter(){
        geradeDran += 1;
    }

    // public void spielerBewegen(int wert){ }
}
