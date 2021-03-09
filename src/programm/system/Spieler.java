package programm.system;

public class Spieler {
    private String name;
    private char symbol;
    private Felder aktuellePos;
    private Boolean istImGefängnis;
    private double kapital;

    public Spieler(String name, char symbol, Felder aktuellePos, Boolean istImGefängnis, double kapital) {
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

    public Boolean getIstImGefängnis() {
        return istImGefängnis;
    }

    public double getKapital() {
        return kapital;
    }

    public void setAktuellePos(Felder aktuellePos) {
        this.aktuellePos = aktuellePos;
    }
}
