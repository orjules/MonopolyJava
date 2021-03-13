package tests.system.mocks;

import programm.system.Würfel;

import java.util.Random;

public class MockWürfel extends Würfel {

    // Muss zwischen 2 und 12 liegen
    int festerWert;

    public MockWürfel(int festerWert) {
        this.festerWert = festerWert;
    }

    @Override
    public int[] würfeln(){
        int ersteZahl = festerWert / 2;
        int zweiteZahl = ersteZahl;
        int summe = ersteZahl + zweiteZahl;
        if (summe != festerWert){
            zweiteZahl ++;
        }else {
            hatPasch = true;
            anzahlPasche ++;
        }
        int [] ausgabe = {ersteZahl, zweiteZahl, summe};
        letzterWurf = summe;
        return ausgabe;
    }
}
