package data;

import interfaces.Chemicals;

public class Salt implements Chemicals{

    private Ions positiveIon;
    private Ions negativeIon;
    private Marker marker=Marker.SALT;
    private String formula;
    private boolean solubility;
    private String name;

    public Salt(Ions... ions){
        int product=1;
        for (Ions i:ions){
            if (i.getCharge()>0) {
                positiveIon=i;
                if (product%i.getCharge()!=0) product*=i.getCharge();
            }
            else {
                if (product%i.getCharge()!=0) product*=-i.getCharge();
                negativeIon=i;
            }
        }
        formula=deriveFormula(product);
        solubility=DataNew.getInstance().getSolubility(formula);
    }

    @Override
    public String getFormula() {
        return formula;
    }

    @Override
    public Marker getMarker() {
        return marker;
    }

    @Override
    public boolean dissolve() {
        return solubility;
    }

    @Override
    public Ions getPositiveIon() {
        return positiveIon;
    }

    @Override
    public Ions getNegativeIon() {
        return negativeIon;
    }

    private String deriveFormula(int product){
        StringBuilder sb=new StringBuilder(""+positiveIon);
        if (product/positiveIon.getCharge()!=1) {
            sb.append(product/positiveIon.getCharge());
        }
        if (product/Math.abs(negativeIon.getCharge())==1){
            sb.append(""+negativeIon);
        }
        else {
            if (negativeIon.nameLength()==1){
                sb.append(negativeIon);
            }
            else {
                sb.append("("+negativeIon+")");
            }
            sb.append(product/Math.abs(negativeIon.getCharge()));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return formula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Salt)) return false;

        Salt salt = (Salt) o;

        if (positiveIon != salt.positiveIon) return false;
        if (negativeIon != salt.negativeIon) return false;
        if (marker != salt.marker) return false;
        return formula.equals(salt.formula);
    }

    @Override
    public int hashCode() {
        int result = positiveIon.hashCode();
        result = 31 * result + negativeIon.hashCode();
        result = 31 * result + marker.hashCode();
        result = 31 * result + formula.hashCode();
        return result;
    }
}
