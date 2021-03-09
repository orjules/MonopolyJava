package programm;

import programm.system.Darsteller;
import programm.system.Organisator;
import programm.system.Spielleiter;
import programm.system.Würfel;

public class Main {

    public static void main(String[] args) {
        Würfel würfel = new Würfel();
        Spielleiter spielleiter = new Spielleiter();
        Darsteller darsteller = new Darsteller(spielleiter);
        Organisator organisator = new Organisator(spielleiter, darsteller, würfel);
        organisator.testWurf();

    }
}
