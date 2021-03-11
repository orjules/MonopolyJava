package programm.grundstücke;

public class Farbgruppen {
    Farben farbe;
    Straße[] zugehörigeStraßen;
    boolean istKomplett;

    public Farbgruppen(Farben farbe, Straße[] zugehörigeStraßen) {
        this.farbe = farbe;
        this.zugehörigeStraßen = zugehörigeStraßen;
        this.istKomplett = false;
    }
}
