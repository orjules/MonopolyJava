package programm;

import programm.system.Würfel;

public class Main {

    public static void main(String[] args) {
        Würfel würfel = new Würfel();
        int [] wurf = würfel.würfeln();
        System.out.println(wurf[0]);
        System.out.println(wurf[1]);
        System.out.println(wurf[2]);
    }
}
