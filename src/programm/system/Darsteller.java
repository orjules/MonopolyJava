package programm.system;

import java.util.LinkedList;
import java.util.Scanner;

public class Darsteller {

    Spielleiter spielleiter;
    Scanner scanner = new Scanner(System.in);

    public Darsteller(Spielleiter spielleiter) {
        this.spielleiter = spielleiter;
    }

    public String eingabeFragen(String ausgabe, String[] erlaubteEingaben){
        System.out.println(ausgabe);
        while (true) {
            String eingabe = scanner.nextLine();
            for (String erlaubt : erlaubteEingaben){
                if (erlaubt.equals(eingabe)){
                    return erlaubt;
                }
            }
            System.out.println("Ungültige Eingabe, bitte noch einmal eingeben.");
        }
    }

    public void spielerHatGeworfen(int[] wurf){
        System.out.println(spielleiter.getGeradeDran().getName() + " hat eine " + wurf[0] + ", " + wurf[1] + " gewürfelt.");
    }

    public void brettZeichnen(){
        Tabelle tab = new Tabelle(new String[]{"Felder", "Spieler"});
        for (Felder feld : Felder.values()){
            LinkedList<String> spielerSymbole = new LinkedList<>();
            int i = 0;
            for (Spieler spieler : spielleiter.getAlleSpieler()){
                if (spieler.getAktuellePos() == feld){
                    spielerSymbole.add(Character.toString(spieler.getSymbol()));
                }
                i++;
            }
            tab.addZeile(new String[]{feld.name(), String.join(", ", spielerSymbole)});
        }
        tab.printTabelle();
    }

    public void ausgabe(String text){
        System.out.println(text);
    }

    public void umbruch(){
        for (int i = 0; i <40; i++){
            System.out.print("-");
        }
        System.out.print("\n");
    }
}
