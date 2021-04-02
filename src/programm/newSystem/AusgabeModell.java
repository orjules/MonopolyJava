package programm.newSystem;

public class AusgabeModell {

    MöglicheAusgaben ausgabe;

    public AusgabeModell(MöglicheAusgaben ausgabe) {
        this.ausgabe = ausgabe;
    }

    public MöglicheAusgaben getFrage() {
        return ausgabe;
    }
}
