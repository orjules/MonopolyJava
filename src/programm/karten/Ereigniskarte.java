package programm.karten;


public abstract class Ereigniskarte{

    protected String beschreibung;

    protected Ereigniskarte(String beschreibung){
        this.beschreibung = beschreibung;
    }

    public String getBeschreibung(){
        return beschreibung;
    }
}
