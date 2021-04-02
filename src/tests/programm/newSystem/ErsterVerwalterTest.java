package programm.newSystem;

import org.junit.jupiter.api.Test;
import programm.grundstücke.Grundbuch;
import programm.grundstücke.Grundstück;
import programm.system.Felder;
import programm.system.Würfel;
import programm.system.spieler.Spieler;
import programm.system.spieler.Spielleiter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ErsterVerwalterTest {

    Spieler spieler1 = mock(Spieler.class);
    Spieler spieler2 = mock(Spieler.class);
    Spielleiter spielleiter = mock(Spielleiter.class);
    Würfel würfel = mock(Würfel.class);
    Grundbuch grundbuch = mock(Grundbuch.class);
    Grundstück grundstück = mock(Grundstück.class);
    int grundstückPreis = 100;
    int grundstückMiete = 5;
    EingabeModell eingabe = mock(EingabeModell.class);

    ErsterVerwalter ersterVerwalter = new ErsterVerwalter(würfel, spielleiter, grundbuch);

    public void initialerZustandErstellen(){
        when(spielleiter.getGeradeDran()).thenReturn(spieler1);
        when(spieler1.getAktuellePos()).thenReturn(Felder.Los);
    }

    @Test
    public void initialeEingabe(){
        initialerZustandErstellen();
        when(eingabe.getAntwort()).thenReturn(MöglicheEingaben.start);
        ArrayList<MöglicheAusgaben> erwarteteAusgaben = new ArrayList<>();
        erwarteteAusgaben.add(MöglicheAusgaben.würfeln);

        AusgabeModell ausgabe = ersterVerwalter.modellErstellen(eingabe);
        assertEquals(erwarteteAusgaben, ausgabe.getAusgaben());
    }


    @Test
    public void initialerWurfBisFreiesGrundstück(){
        initialerZustandErstellen();
        werfenBisBadstraßeSetzen();
        when(grundbuch.getBesitzerVon(grundstück)).thenReturn(null);
        ArrayList<MöglicheAusgaben> erwarteteAusgaben = new ArrayList<>();
        erwarteteAusgaben.add(MöglicheAusgaben.kaufen);
        erwarteteAusgaben.add(MöglicheAusgaben.übersichtErmöglichen);

        assertVerwalterMachtSeinenJobBeimWerfen(erwarteteAusgaben);
    }
    @Test
    public void übersichtNachWurfBisFreiesGrundstück(){
        initialerWurfBisFreiesGrundstück();
        ArrayList<MöglicheAusgaben> erwarteteAusgaben = getErwartetBeiÜbersichtAnzeigen();

        AusgabeModell zweiteAusgabe = ersterVerwalter.modellErstellen(eingabe);
        assertEquals(erwarteteAusgaben, zweiteAusgabe.getAusgaben());
    }
    @Test
    public void bestätigenNachWurfBisFreiesGrundstück(){
        initialerWurfBisFreiesGrundstück();
        ArrayList<MöglicheAusgaben> erwarteteAusgaben = getErwartetBeiKaufBestätigung();

        AusgabeModell zweiteAusgabe = ersterVerwalter.modellErstellen(eingabe);
        assertEquals(erwarteteAusgaben, zweiteAusgabe.getAusgaben());
        verify(grundbuch, times(1)).übertragenAn(grundstück, spieler1);
        verify(spielleiter, times(1)).kapitalÄndernVon(spieler1, -grundstückPreis);
    }


    @Test
    public void initialerWurfBisBesetzesGrundstück(){
        initialerZustandErstellen();
        werfenBisBadstraßeSetzen();
        when(grundbuch.getBesitzerVon(grundstück)).thenReturn(spieler2);
        ArrayList<MöglicheAusgaben> erwarteteAusgaben = new ArrayList<>();
        erwarteteAusgaben.add(MöglicheAusgaben.mieteZahlen);
        erwarteteAusgaben.add(MöglicheAusgaben.übersichtErmöglichen);

        assertVerwalterMachtSeinenJobBeimWerfen(erwarteteAusgaben);
    }
    @Test
    public void übersichtNachWurfBisBesetztesGrundstück(){
        initialerWurfBisBesetzesGrundstück();
        ArrayList<MöglicheAusgaben> erwarteteAusgaben = getErwartetBeiÜbersichtAnzeigen();

        AusgabeModell zweiteAusgabe = ersterVerwalter.modellErstellen(eingabe);
        assertEquals(erwarteteAusgaben, zweiteAusgabe.getAusgaben());
    }
    @Test
    public void bestätigenNachWurfBisBesetzesGrundstück(){
        // Hier einen Test, dass nach dem Vorherigen Test die nächste Eingabe 'bestätigen' ist
    }


    @Test
    public void initialerWurfBisFreiesGrundstückMitZuWenigGeld(){

    }

    @Test
    public void initialerWurfBisBesetzesGrundstückMitZuWenigGeld(){

    }

    private void werfenBisBadstraßeSetzen(){
        when(eingabe.getAntwort()).thenReturn(MöglicheEingaben.wurf);
        when(würfel.würfeln()).thenReturn(new int[]{1,2,3});
        when(spieler1.getAktuellePos()).thenReturn(Felder.Badstraße);
        when(grundbuch.grundstückVon(Felder.Badstraße)).thenReturn(grundstück);
    }

    private void assertVerwalterMachtSeinenJobBeimWerfen(ArrayList<MöglicheAusgaben> erwarteteAusgaben){
        AusgabeModell ausgabe = ersterVerwalter.modellErstellen(eingabe);
        assertEquals(erwarteteAusgaben, ausgabe.getAusgaben());
        verify(würfel, times(1)).würfeln();
        verify(spielleiter, times(1)).spielerBewegen(3);
        verify(grundbuch, times(1)).grundstückVon(Felder.Badstraße);
    }

    private ArrayList<MöglicheAusgaben> getErwartetBeiÜbersichtAnzeigen(){
        when(eingabe.getAntwort()).thenReturn(MöglicheEingaben.übersicht);
        ArrayList<MöglicheAusgaben> erwarteteAusgaben = new ArrayList<>();
        erwarteteAusgaben.add(MöglicheAusgaben.übersichtAnzeigen);
        return erwarteteAusgaben;
    }

    private ArrayList<MöglicheAusgaben> getErwartetBeiKaufBestätigung(){
        when(eingabe.getAntwort()).thenReturn(MöglicheEingaben.bestätigen);
        ArrayList<MöglicheAusgaben> erwarteteAusgaben = new ArrayList<>();
        erwarteteAusgaben.add(MöglicheAusgaben.kaufbestätigung);
        erwarteteAusgaben.add(MöglicheAusgaben.übersichtAnzeigen);
        erwarteteAusgaben.add(MöglicheAusgaben.zugBeenden);
        return erwarteteAusgaben;
    }
}