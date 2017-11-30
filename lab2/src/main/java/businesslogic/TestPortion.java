package businesslogic;

import Interfaces.IFChemicals;

public class TestPortion<T extends IFChemicals> {
    private T t;
    private double amount;
    public TestPortion(T t, double amount){
        this.t=t;
        this.amount=amount;
    }
    public String getName(){
        return t.getName();
    }
    public String getFormula(){
        return t.getFormula();
    }
    public double getAmount(){
        return amount;
    }
    public IFChemicals getChemical(){
        return t;
    }


}
