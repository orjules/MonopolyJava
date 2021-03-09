package programm;

import programm.system.Darsteller;
import programm.system.Spielleiter;

public class Main {

    public static void main(String[] args) {
        Spielleiter spielleiter = new Spielleiter();
        Darsteller darsteller = new Darsteller(spielleiter);
        darsteller.brettZeichnen();
    }
}
