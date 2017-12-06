package data;

import interfaces.Chemicals;

import java.util.ArrayList;
import java.util.List;

public class Acid implements Chemicals {

    private Ions positiveIon;
    private Ions negativeIon;
    private String formula;
    private boolean solubility=true;
    private String name;
    private Marker marker=Marker.ACID;

    public Acid(Ions i){
        negativeIon=i;
        positiveIon=Ions.H;
        formula="H";
        if (Math.abs(i.getCharge())>1) formula+=Math.abs(i.getCharge());
        formula+=i;
        //   name=Data.names.get(formula);
        //solubility=DataNew.getInstance().getSolubility(formula);
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
    public Ions getPositiveIon() {
        return positiveIon;
    }

    @Override
    public Ions getNegativeIon() {
        return negativeIon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Acid)) return false;

        Acid acid = (Acid) o;

        if (positiveIon != acid.positiveIon) return false;
        if (negativeIon != acid.negativeIon) return false;
        if (!formula.equals(acid.formula)) return false;
        return marker == acid.marker;
    }

    @Override
    public int hashCode() {
        int result = positiveIon.hashCode();
        result = 31 * result + negativeIon.hashCode();
        result = 31 * result + formula.hashCode();
        result = 31 * result + marker.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return formula;
    }
}
