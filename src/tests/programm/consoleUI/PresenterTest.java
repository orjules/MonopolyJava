package programm.consoleUI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programm.statemashine.enums.Ausgaben;
import programm.statemashine.enums.EingabeBeschreibungen;
import programm.statemashine.enums.Eingaben;
import programm.statemashine.io.AusgabeModell;
import programm.system.brett.Brett;
import programm.system.spieler.Spieler;

import java.util.ArrayList;
import java.util.HashMap;

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
        assertEquals("'w' um zu würfeln", presenter.getInputFrage());
    }
    private AusgabeModell modellBeiInital(){
        HashMap<Eingaben, EingabeBeschreibungen> erlaubteEingaben = new HashMap<>();
        erlaubteEingaben.put(Eingaben.werfen, EingabeBeschreibungen.ersterWurf);
        ArrayList<Ausgaben> ausgaben = new ArrayList<>();
        ausgaben.add(Ausgaben.mussErstWürfeln);
        return new AusgabeModell(null, spieler1, brett, null, erlaubteEingaben, ausgaben);
    }
}