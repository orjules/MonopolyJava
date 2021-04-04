package programm.consoleUI;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import programm.statemashine.enums.EingabeBeschreibungen;
import programm.statemashine.enums.Eingaben;
import programm.statemashine.io.NeuesAusgabeModell;
import programm.statemashine.io.NeuesEingabeModell;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControllerTest {

    KontextGrenze kontext = mock(KontextGrenze.class);
    ConsoleHandler consoleHandler = mock(ConsoleHandler.class);

    Controller controller = new Controller(kontext, consoleHandler);

    @Test
    public void eingabeIstW(){
        HashMap<Eingaben, EingabeBeschreibungen> ersteEingabe = new HashMap<>();
        ersteEingabe.put(Eingaben.werfen, EingabeBeschreibungen.ersterWurf);
        when(consoleHandler.getEingabe(ersteEingabe)).thenReturn(Eingaben.werfen);
        ArgumentCaptor<NeuesEingabeModell> eingabe = ArgumentCaptor.forClass(NeuesEingabeModell.class);

        NeuesAusgabeModell ausgabe = controller.eingabeErfragen();

        verify(kontext, times(1)).erstelleModell(eingabe.capture());
        assertEquals(Eingaben.werfen, eingabe.getValue().getAntwort());
    }
}