package programm.system.interfaces;

import programm.system.Spieler;

public interface Spielleiter {
    public Spieler getGeradeDran();
    public void weiter();
    public void spielerBewegen(int wert);
}
