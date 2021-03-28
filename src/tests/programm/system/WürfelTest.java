package programm.system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WürfelTest {

    Random rnd = mock(Random.class);
    Würfel würfel;

    @BeforeEach
    public void init(){
        würfel = new Würfel(rnd);
    }

    @Test
    public void normalWerfen(){
        when(rnd.nextInt(6)).thenAnswer(new Answer<Integer>() {
            private int count = 0;
            public Integer answer(InvocationOnMock invocation) {
                if (count++ == 1)
                    return 3;
                return 2;
            }
        });
        int[] wurf = würfel.würfeln();
        assertEquals(3, wurf[0]);
        assertEquals(4, wurf[1]);
        assertEquals(7, wurf[2]);
        assertFalse(würfel.darfNochmalWerfen());
        assertFalse(würfel.mussInsGefängnis());
        assertEquals(7, würfel.getLetztenWurf());
    }

    @Test
    public void paschWerfen(){
        when(rnd.nextInt(6)).thenReturn(3);

        int[] wurf = würfel.würfeln();
        assertEquals(4, wurf[0]);
        assertEquals(4, wurf[1]);
        assertEquals(8, wurf[2]);
        assertTrue(würfel.darfNochmalWerfen());
        assertFalse(würfel.mussInsGefängnis());
        assertEquals(8, würfel.getLetztenWurf());
    }

    @Test
    public void dreiPaschaWerfen(){
        when(rnd.nextInt(6)).thenReturn(1);
        int[] wurf = würfel.würfeln();
        when(rnd.nextInt(6)).thenReturn(3);
        wurf = würfel.würfeln();
        wurf = würfel.würfeln();
        assertEquals(4, wurf[0]);
        assertEquals(4, wurf[1]);
        assertEquals(8, wurf[2]);
        assertFalse(würfel.darfNochmalWerfen());
        assertTrue(würfel.mussInsGefängnis());
        assertEquals(8, würfel.getLetztenWurf());
    }

    @Test
    public void zweiPascheDannNormal(){
        when(rnd.nextInt(6)).thenReturn(3);
        würfel.würfeln();
        würfel.würfeln();
        assertTrue(würfel.darfNochmalWerfen());
        assertFalse(würfel.mussInsGefängnis());
        assertEquals(8, würfel.getLetztenWurf());

        when(rnd.nextInt(6)).thenAnswer(new Answer<Integer>() {
            private int count = 0;
            public Integer answer(InvocationOnMock invocation) {
                if (count++ == 1)
                    return 3;
                return 2;
            }
        });
        int[] wurf = würfel.würfeln();
        assertEquals(3, wurf[0]);
        assertEquals(4, wurf[1]);
        assertEquals(7, wurf[2]);
        assertFalse(würfel.darfNochmalWerfen());
        assertFalse(würfel.mussInsGefängnis());
        assertEquals(7, würfel.getLetztenWurf());
    }

}