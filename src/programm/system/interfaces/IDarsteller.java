package programm.system.interfaces;

public interface IDarsteller {

    public String eingabeFragen(String ausgabe, String[] erlaubteEingaben);
    public void spielerHatGeworfen(int[] wurf);
    public void brettZeichnen();
    public void ausgabe(String text);
    public void umbruch();
}
