package programm.karten;


public abstract class Ereigniskarte{

    protected String beschreibung;

    protected Ereigniskarte(String beschreibung){
        this.beschreibung = beschreibung;
    }

    @Override
    public String toString(){
        return beschreibung;
    }
}
