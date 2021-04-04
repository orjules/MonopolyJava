package programm.consoleUI;

import programm.statemashine.enums.EingabeBeschreibungen;
import programm.statemashine.enums.Eingaben;

import java.util.HashMap;
import java.util.Scanner;

public class ConsoleHandler {

    private Scanner scanner = new Scanner(System.in);

    public Eingaben getEingabe(HashMap<Eingaben, EingabeBeschreibungen> erlaubteEingaben){
        Eingaben[] erlaubteEingabeWerte = erlaubteEingaben.keySet().toArray(new Eingaben[0]);
        EingabeBeschreibungen[] beschreibungen = erlaubteEingaben.values().toArray(new EingabeBeschreibungen[0]);

        for (EingabeBeschreibungen b : beschreibungen){
            möglichkeitenAusgeben(b);
        }
        while (true){
            String input = scanner.nextLine();
            switch (input){
                case "w":
                    if (istErlaubt(erlaubteEingabeWerte, Eingaben.werfen)){
                        return Eingaben.werfen;
                    }
                case "ü":
                    if (istErlaubt(erlaubteEingabeWerte, Eingaben.übersicht)){
                        return Eingaben.übersicht;
                    }
                case "a":
                    if (istErlaubt(erlaubteEingabeWerte, Eingaben.bestätigen)){
                        return Eingaben.bestätigen;
                    }
                case "z":
                    if (istErlaubt(erlaubteEingabeWerte, Eingaben.zurück)){
                        return Eingaben.zurück;
                    }
                default:
                    ausgeben("Unerlaubte Eingabe. Bitte wiederholen.");
            }
        }
    }

    private boolean istErlaubt(Eingaben[] erlaubteEingabeWerte, Eingaben gefragteEingabe){
        for (Eingaben e : erlaubteEingabeWerte){
            if (e.equals(gefragteEingabe))
                return true;
        }
        return false;
    }

    private void möglichkeitenAusgeben(EingabeBeschreibungen beschreibungen){
        switch (beschreibungen){
            case standard -> ausgeben("Standart sollte nie ausgegeben werden. Etwas ist schiefgelaufen.");
            case ersterWurf -> ausgeben("'w' um zu werfen");
        }
    }

    public void ausgeben(String text){
        System.out.println(text);
    }
}
