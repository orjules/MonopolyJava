package programm.newSystem;

public class ErsterVerwalter extends Verwalter implements ControllerGrenze {

    @Override
    public AusgabeModell modellErstellen(EingabeModell eingabe) {
        if (eingabe.getAntwort().equals(MöglicheEingaben.start)){
            return new AusgabeModell(MöglicheAusgaben.würfeln);
        }
        else
            return null;
    }
}
