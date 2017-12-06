package data;

import interfaces.Chemicals;

public class Alkali implements Chemicals {

    private Ions negativeIon;
    private Ions positiveIon;
    private String formula;
    private boolean solubility;
    private String name;
    private Marker marker=Marker.ALKALI;

    public Alkali(Ions i){
        positiveIon=i;
        negativeIon=Ions.OH;
        formula=i+"";
        if (i.getCharge()==1) formula+="OH";
        else formula=formula+"(OH)"+i.getCharge();
        //   name=Data.names.get(formula);
        solubility=DataNew.getInstance().getSolubility(formula);
    }

    public String getFormula() {
        return formula;
    }
    public Marker getMarker() {
        return marker;
    }

    public boolean dissolve() {
        return solubility;
    }

    @Override
    public Ions getNegativeIon() {
        return negativeIon;
    }

    @Override
    public Ions getPositiveIon() {
        return positiveIon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alkali)) return false;
        Alkali alkali = (Alkali) o;
        if (negativeIon != alkali.negativeIon) return false;
        if (positiveIon != alkali.positiveIon) return false;
        if (!formula.equals(alkali.formula)) return false;
        return marker == alkali.marker;
    }

    @Override
    public int hashCode() {
        int result = negativeIon.hashCode();
        result = 31 * result + positiveIon.hashCode();
        result = 31 * result + formula.hashCode();
        result = 31 * result + marker.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return formula;
    }
}
