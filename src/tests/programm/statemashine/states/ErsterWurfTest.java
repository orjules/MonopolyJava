package programm.statemashine.states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programm.grundstücke.Grundbuch;
import programm.grundstücke.Grundstück;
import programm.karten.Ereigniskarte;
import programm.karten.Kartenmanager;
import programm.statemashine.Kontext;
import programm.statemashine.io.*;
import programm.system.Felder;
import programm.system.Würfel;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ErsterWurfTest {

    Kontext kontext = mock(Kontext.class);
    Spielleiter spielleiter = mock(Spielleiter.class);
    Spieler spieler1 = mock(Spieler.class);
    Spieler spieler2 = mock(Spieler.class);
    Würfel würfel = mock(Würfel.class);
    Grundbuch grundbuch = mock(Grundbuch.class);
    Grundstück grundstück = mock(Grundstück.class);
    Kartenmanager kartenmanager = mock(Kartenmanager.class);
    Ereigniskarte karte = mock(Ereigniskarte.class);
    Felder freiesGrundstückFeld = Felder.Badstraße;
    Felder besetzesGrundstückFeld = Felder.Turmstraße;
    Felder karteZiehenFeld = Felder.Ereignisfeld1;
    S_FreiesGrundstück SFreiesGrundstück = mock(S_FreiesGrundstück.class);
    S_BesetzesGrundstück SBesetzesGrundstück = mock(S_BesetzesGrundstück.class);
    S_KarteZiehen SKarteZiehen = mock(S_KarteZiehen.class);

    int[] wurfOhnePasch = new int[]{2,3,5};

    S_ErsterWurf SErsterWurf;

    @BeforeEach
    public void init(){
        when(spielleiter.getGeradeDran()).thenReturn(spieler1);
        SErsterWurf = new S_ErsterWurf(kontext, spielleiter, würfel, grundbuch, kartenmanager);
    }

    @Test
    public void übersichtIstNichtErlaubt(){
        assertTrue(false);
    }
    @Test
    public void bestätigenIstNichtErlaubt(){
        assertTrue(false);
    }
    @Test
    public void zurückIstNichtErlaubt(){
        assertTrue(false);
    }

    @Test
    public void werfenBisFreiesGrundstück(){
        assertTrue(false);
    }
    @Test
    public void werfenBisBesetztesGrundstück(){
        assertTrue(false);
    }
    @Test
    public void werfenBisKarte(){
        assertTrue(false);
    }

    // Werfen beim dritten Pasch Test schreiben

}