package programm.grundstücke;

public class Farbgruppen {
    private Farben farbe;
    private Straße[] zugehörigeStraßen;
    private boolean istKomplett;

    public Farbgruppen(Farben farbe, Straße[] zugehörigeStraßen) {
        this.farbe = farbe;
        this.zugehörigeStraßen = zugehörigeStraßen;
        this.istKomplett = false;
    }
}
