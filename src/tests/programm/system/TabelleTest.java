package programm.system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabelleTest {

    Tabelle tabelle;
    String[] kopf;

    public void init(){
        tabelle = new Tabelle(kopf);
    }

    @Test
    public void leereTabelleprinten(){
        kopf = new String[]{""};
        init();
        assertEquals("",tabelle.toString());
    }

    @Test
    public void eineKopfzelleEins(){
        kopf = new String[]{"A"};
        init();
        assertEquals("=====\n| A |\n|---|\n=====\n",tabelle.toString());
    }

    @Test
    public void eineKopfzelleZwei(){
        kopf = new String[]{"AA"};
        init();
        assertEquals("======\n| AA |\n|----|\n======\n",tabelle.toString());
    }

    @Test
    public void eineKopfzelleDrei(){
        kopf = new String[]{"AAA"};
        init();
        assertEquals("=======\n| AAA |\n|-----|\n=======\n",tabelle.toString());
    }


    @Test
    public void eineSpalteGefüllt(){
        kopf = new String[]{"AA"};
        init();
        tabelle.addZeile(new String[]{"BB"});
        assertEquals("======\n| AA |\n|----|\n| BB |\n======\n",tabelle.toString());
    }

    @Test
    public void eineSpalteLeererKopf(){
        kopf = new String[]{""};
        init();
        tabelle.addZeile(new String[]{"BB"});
        assertEquals("======\n|    |\n|----|\n| BB |\n======\n",tabelle.toString());
    }

    @Test
    public void doppelteKopfzeile(){
        kopf = new String[]{"AA", "BB"};
        init();
        assertEquals("===========\n| AA | BB |\n|---------|\n===========\n",tabelle.toString());
    }

    @Test
    public void doppelteKopfzeileUnterschiedlich(){
        kopf = new String[]{"A", "BBB"};
        init();
        assertEquals("===========\n| A | BBB |\n|---------|\n===========\n",tabelle.toString());
    }

    @Test
    public void doppelteKopfzeileEinerLeer(){
        kopf = new String[]{"AA", ""};
        init();
        assertEquals("=========\n| AA |  |\n|-------|\n=========\n",tabelle.toString());
    }

    @Test
    public void dreiReihen(){
        kopf = new String[]{"AA"};
        init();
        tabelle.addZeile(new String[]{"BB"});
        tabelle.addZeile(new String[]{"CC"});
        assertEquals("======\n| AA |\n|----|\n| BB |\n| CC |\n======\n",tabelle.toString());
    }

    @Test
    public void ungleicheTabelleGerade(){
        kopf = new String[]{"AAA"};
        init();
        tabelle.addZeile(new String[]{"B"});
        assertEquals("=======\n| AAA |\n|-----|\n|  B  |\n=======\n",tabelle.toString());
    }

    @Test
    public void ungleicheTabelleUngerade(){
        kopf = new String[]{"AAAA"};
        init();
        tabelle.addZeile(new String[]{"B"});
        assertEquals("========\n| AAAA |\n|------|\n|  B   |\n========\n",tabelle.toString());
    }

    @Test
    public void illegaleAnzahlSpalten(){
        kopf = new String[]{"AA"};
        init();
        assertThrows(IllegalArgumentException.class, ()->tabelle.addZeile(new String[]{"BB","CC"}));
    }

}