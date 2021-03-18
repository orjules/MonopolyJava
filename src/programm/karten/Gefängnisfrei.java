package programm.karten;

public class Gefängnisfrei extends Ereigniskarte{

    protected Gefängnisfrei(String beschreibung) {
        super(beschreibung);
    }

    @Override
    public String getBestätigung() {
        return null;
    }
}
