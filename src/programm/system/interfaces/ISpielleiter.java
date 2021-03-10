package programm.system.interfaces;

import programm.system.Spieler;

public interface ISpielleiter {
    public Spieler getGeradeDran();
    public void weiter();
    public void spielerBewegen(int wert);
}
