package programm.grundstücke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programm.grundstücke.*;

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
    public void nominativArtikel(){
        assertEquals("Der ", GrammatikHandler.getGroßeArtikel(testBahnhof, Fälle.Nominativ));
        assertEquals("Die ", GrammatikHandler.getGroßeArtikel(testStraße, Fälle.Nominativ));
        assertEquals("Das ", GrammatikHandler.getGroßeArtikel(testWerk, Fälle.Nominativ));

        assertEquals("der ", GrammatikHandler.getkleineArtikel(testBahnhof, Fälle.Nominativ));
        assertEquals("die ", GrammatikHandler.getkleineArtikel(testStraße, Fälle.Nominativ));
        assertEquals("das ", GrammatikHandler.getkleineArtikel(testWerk, Fälle.Nominativ));
    }

    @Test
    public void genitivArtikel(){
        assertEquals("Des ", GrammatikHandler.getGroßeArtikel(testBahnhof, Fälle.Genitiv));
        assertEquals("Der ", GrammatikHandler.getGroßeArtikel(testStraße, Fälle.Genitiv));
        assertEquals("Des ", GrammatikHandler.getGroßeArtikel(testWerk, Fälle.Genitiv));

        assertEquals("des ", GrammatikHandler.getkleineArtikel(testBahnhof, Fälle.Genitiv));
        assertEquals("der ", GrammatikHandler.getkleineArtikel(testStraße, Fälle.Genitiv));
        assertEquals("des ", GrammatikHandler.getkleineArtikel(testWerk, Fälle.Genitiv));
    }

    @Test
    public void dativArtikel(){
        assertEquals("Dem ", GrammatikHandler.getGroßeArtikel(testBahnhof, Fälle.Dativ));
        assertEquals("Der ", GrammatikHandler.getGroßeArtikel(testStraße, Fälle.Dativ));
        assertEquals("Dem ", GrammatikHandler.getGroßeArtikel(testWerk, Fälle.Dativ));

        assertEquals("dem ", GrammatikHandler.getkleineArtikel(testBahnhof, Fälle.Dativ));
        assertEquals("der ", GrammatikHandler.getkleineArtikel(testStraße, Fälle.Dativ));
        assertEquals("dem ", GrammatikHandler.getkleineArtikel(testWerk, Fälle.Dativ));
    }

    @Test
    public void akkusativArtikel(){
        assertEquals("Den ", GrammatikHandler.getGroßeArtikel(testBahnhof, Fälle.Akkusativ));
        assertEquals("Die ", GrammatikHandler.getGroßeArtikel(testStraße, Fälle.Akkusativ));
        assertEquals("Das ", GrammatikHandler.getGroßeArtikel(testWerk, Fälle.Akkusativ));

        assertEquals("den ", GrammatikHandler.getkleineArtikel(testBahnhof, Fälle.Akkusativ));
        assertEquals("die ", GrammatikHandler.getkleineArtikel(testStraße, Fälle.Akkusativ));
        assertEquals("das ", GrammatikHandler.getkleineArtikel(testWerk, Fälle.Akkusativ));
    }
}
