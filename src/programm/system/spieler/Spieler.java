package programm.system.spieler;

import programm.system.Felder;

public class Spieler {
    String name;
    char symbol;
    Felder aktuellePos;
    boolean istImGefängnis;
    int kapital;

    public Spieler(String name, char symbol, Felder aktuellePos, boolean istImGefängnis, int kapital) {
        this.name = name;
        this.symbol = symbol;
        this.aktuellePos = aktuellePos;
        this.istImGefängnis = istImGefängnis;
        this.kapital = kapital;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public Felder getAktuellePos() {
        return aktuellePos;
    }

    public boolean getIstImGefängnis() {
        return istImGefängnis;
    }

    public int getKapital() {
        return kapital;
    }

}
