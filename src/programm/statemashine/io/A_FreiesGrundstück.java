package programm.statemashine.io;

import programm.grundstücke.Grundstück;

public class A_FreiesGrundstück implements Ausgabe{

    Grundstück grundstück;

    public A_FreiesGrundstück(Grundstück grundstück) {
        this.grundstück = grundstück;
    }

    @Override
    public Object[] getAlles() {
        return new Object[]{grundstück};
    }
}
