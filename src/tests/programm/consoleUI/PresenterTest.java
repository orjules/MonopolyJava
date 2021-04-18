package programm.consoleUI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programm.grundstücke.Fälle;
import programm.grundstücke.Grundstück;
import programm.statemashine.enums.Ausgaben;
import programm.statemashine.enums.EingabeBeschreibungen;
import programm.statemashine.enums.Eingaben;
import programm.statemashine.io.AusgabeModell;
import programm.system.brett.Brett;
import programm.system.brett.Feld;
import programm.system.spieler.Spieler;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PresenterTest {
    // Erstellt 4 Dinge für den View: FeldMenü, SpielerDaten, Beschreibung, InputAnfrage
    // Ablauf: Modell mit (initialem) Input von Kontext holen, Modell zu Strings, Stings an View und einen Input bekommen

    KontextGrenze kontextGrenze = mock(KontextGrenze.class);
    ConsoleHandler consoleHandler = mock(ConsoleHandler.class);

    Presenter presenter = new Presenter(kontextGrenze, consoleHandler);

    Spieler spieler1 = mock(Spieler.class);
    Brett brett = mock(Brett.class);
    Feld feld = mock(Feld.class);
    Grundstück grundstück = mock(Grundstück.class);
    int[] wurf = new int[]{1,1,3};

    @BeforeEach
    public void init(){
        when(spieler1.toString()).thenReturn("Fred (#)");
        when(spieler1.getKapital()).thenReturn(1500);
    }

    @Test
    public void erstelltStringsAusInitialemModell(){
        AusgabeModell erwartetesModell = modellBeiInital();
        when(kontextGrenze.erstelleModell(any())).thenReturn(erwartetesModell);

        presenter.spielLaufenLassen();
        //assertEquals("Platzhalter für Tabelle", presenter.getFeldMenü());
        assertEquals("Fred (#) ist dran und besitzt momentan 1500€.", presenter.getSpielerDaten());
        assertEquals("Du bist dran und musst als erster Würfeln.", presenter.getBeschreibung());
        assertEquals("'w' um zu würfeln\n", presenter.getInputFrage());
    }
    private AusgabeModell modellBeiInital(){
        LinkedHashMap<Eingaben, EingabeBeschreibungen> erlaubteEingaben = new LinkedHashMap<>();
        erlaubteEingaben.put(Eingaben.werfen, EingabeBeschreibungen.ersterWurf);
        ArrayList<Ausgaben> ausgaben = new ArrayList<>();
        ausgaben.add(Ausgaben.mussErstWürfeln);
        return new AusgabeModell(null, spieler1, brett, null, erlaubteEingaben, ausgaben);
    }

    @Test
    public void stringWennFreieStraße(){
        when(grundstück.getArtikelKlein(Fälle.Dativ)).thenReturn("der");
        when(grundstück.getName()).thenReturn("Teststraße");
        when(grundstück.getPronomenGroß()).thenReturn("Sie");
        when(grundstück.getGrundstücksWert()).thenReturn(100);
        gleichesSetUpBeiFreiemGrundstück();

        presenter.spielLaufenLassen();
        // Brett checken
        assertEquals("Fred (#) ist dran und besitzt momentan 1500€.", presenter.getSpielerDaten());
        assertEquals("Du bist auf der Teststraße gelandet. Sie ist zu verkaufen und kostet 100€.", presenter.getBeschreibung());
        assertEquals("'a' um es zu kaufen\n'z' um es an die anderen Spieler zu versteigern\n'ü' um die Übersicht zu öffnen\n", presenter.getInputFrage());
    }
    @Test
    public void beschreibungBeiFreiemBahnhof(){
        when(grundstück.getArtikelKlein(Fälle.Dativ)).thenReturn("dem");
        when(grundstück.getName()).thenReturn("Testbahnhof");
        when(grundstück.getPronomenGroß()).thenReturn("Er");
        when(grundstück.getGrundstücksWert()).thenReturn(100);
        gleichesSetUpBeiFreiemGrundstück();

        presenter.spielLaufenLassen();
        assertEquals("Du bist auf dem Testbahnhof gelandet. Er ist zu verkaufen und kostet 100€.", presenter.getBeschreibung());
    }
    @Test
    public void beschreibungBeiFreiemWerk(){
        when(grundstück.getArtikelKlein(Fälle.Dativ)).thenReturn("dem");
        when(grundstück.getName()).thenReturn("Testwerk");
        when(grundstück.getPronomenGroß()).thenReturn("Es");
        when(grundstück.getGrundstücksWert()).thenReturn(100);
        gleichesSetUpBeiFreiemGrundstück();

        presenter.spielLaufenLassen();
        assertEquals("Du bist auf dem Testwerk gelandet. Es ist zu verkaufen und kostet 100€.", presenter.getBeschreibung());
    }
    private AusgabeModell modellBeiNeu(){
        LinkedHashMap<Eingaben, EingabeBeschreibungen> erlaubteEingaben = new LinkedHashMap<>();
        erlaubteEingaben.put(Eingaben.bestätigen, EingabeBeschreibungen.kaufen);
        erlaubteEingaben.put(Eingaben.zurück, EingabeBeschreibungen.versteigern);
        erlaubteEingaben.put(Eingaben.übersicht, EingabeBeschreibungen.übersicht);
        ArrayList<Ausgaben> ausgaben = new ArrayList<>();
        ausgaben.add(Ausgaben.aufFreiemGrundstück);
        return new AusgabeModell(feld, spieler1, brett, wurf, erlaubteEingaben, ausgaben);
    }
    private void gleichesSetUpBeiFreiemGrundstück(){
        AusgabeModell erwartetesModel = modellBeiNeu();
        when(kontextGrenze.erstelleModell(any())).thenReturn(erwartetesModel);
        when(feld.istGrundstück()).thenReturn(true);
        when(feld.getGrundstück()).thenReturn(grundstück);
    }

    // Auf besetztem Feld

    // Auf Kartenfeld

    // Auf freiem Feld mit Pasch (und dabei auch bestätigen)

    // Auf freiem Feld über Los gegangen
}