package programm.newSystem;

public class EingabeModell {

    MöglicheEingaben eingabe;

    public EingabeModell(MöglicheEingaben eingabe) {
        this.eingabe = eingabe;
    }

    public MöglicheEingaben getAntwort() {
        return eingabe;
    }
}
