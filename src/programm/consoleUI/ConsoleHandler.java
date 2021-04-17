package programm.consoleUI;

import programm.statemashine.enums.EingabeBeschreibungen;
import programm.statemashine.enums.Eingaben;

import java.util.HashMap;
import java.util.Scanner;

public class ConsoleHandler {

    private Scanner scanner = new Scanner(System.in);

    public Eingaben getEingabe(Eingaben[] erlaubteEingabeWerte){

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

    public void ausgeben(String text){
        System.out.println(text);
    }
}
