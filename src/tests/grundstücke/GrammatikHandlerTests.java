package tests.grundstücke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import programm.grundstücke.Bahnhof;
import programm.grundstücke.GrammatikHandler;
import programm.grundstücke.Straße;
import programm.grundstücke.Werk;
import programm.system.Felder;

import static org.junit.jupiter.api.Assertions.*;

public class GrammatikHandlerTests {


    Straße testStraße;
    Bahnhof testBahnhof;
    Werk testWerk;

    @BeforeEach
    public void setUp(){
        testStraße = new Straße("straße", null, 0,0, null, 0,
                0,0,0,0,0,0);
        testBahnhof = new Bahnhof("bahnhof", null);
        testWerk = new Werk("werk", null, 0, 0);
    }

    @Test
    public void großePronomen(){
        assertEquals("Er ", GrammatikHandler.getGroßesPronomenFür(testBahnhof));
        assertEquals("Sie ", GrammatikHandler.getGroßesPronomenFür(testStraße));
        assertEquals("Es ", GrammatikHandler.getGroßesPronomenFür(testWerk));
    }

    @Test
    public void kleinePronomen(){
        assertEquals("er ", GrammatikHandler.getkleinesPronoemenFür(testBahnhof));
        assertEquals("sie ", GrammatikHandler.getkleinesPronoemenFür(testStraße));
        assertEquals("es ", GrammatikHandler.getkleinesPronoemenFür(testWerk));
    }

    @Test
    public void großeNominativArtikel(){
        assertEquals("Der ", GrammatikHandler.getGroßeNominativArtikel(testBahnhof));
        assertEquals("Die ", GrammatikHandler.getGroßeNominativArtikel(testStraße));
        assertEquals("Das ", GrammatikHandler.getGroßeNominativArtikel(testWerk));
    }

    @Test
    public void kleineNominativArtikel(){
        assertEquals("der ", GrammatikHandler.getkleineNominativArtikel(testBahnhof));
        assertEquals("die ", GrammatikHandler.getkleineNominativArtikel(testStraße));
        assertEquals("das ", GrammatikHandler.getkleineNominativArtikel(testWerk));
    }

    @Test
    public void großeDativArtikel(){
        assertEquals("Dem ", GrammatikHandler.getGroßeDativArtikel(testBahnhof));
        assertEquals("Der ", GrammatikHandler.getGroßeDativArtikel(testStraße));
        assertEquals("Dem ", GrammatikHandler.getGroßeDativArtikel(testWerk));
    }

    @Test
    public void kleineDativArtikel(){
        assertEquals("dem ", GrammatikHandler.getkleineDativArtikel(testBahnhof));
        assertEquals("der ", GrammatikHandler.getkleineDativArtikel(testStraße));
        assertEquals("dem ", GrammatikHandler.getkleineDativArtikel(testWerk));
    }


}
