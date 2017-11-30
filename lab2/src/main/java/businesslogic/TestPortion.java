package businesslogic;

import Interfaces.IFChemicals;

public class TestPortion<T extends IFChemicals> {
    private T t;
    private double mass;
    public TestPortion(T t, double mass){
        this.t=t;
        this.mass=mass;
    }

}
