package programm.statemashine.io;

public class A_Gewürfelt implements Ausgabe {

    int[] wurf;

    public A_Gewürfelt(int[] wurf) {
        this.wurf = wurf;
    }

    @Override
    public Object[] getAlles() {
        return new Object[]{wurf};
    }
}
