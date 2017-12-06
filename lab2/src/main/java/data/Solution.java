package data;

import interfaces.Chemicals;

public class Solution implements Chemicals {

    private Ions positiveIon;
    private Ions negativeIon;
    private Marker marker=Marker.SOLUTION;
    private String formula;

    public Solution(Ions... ions){
        for (Ions i:ions){
            if (i.getCharge()>0) positiveIon=i;
            else negativeIon=i;
            if (positiveIon==Ions.H){
                formula="H";
                if (Math.abs(negativeIon.getCharge())>1) formula+=Math.abs(i.getCharge());
                formula+=negativeIon;
            }
            else formula="H2O";
//            if (formula=="H2O") marker.setPriority(3);
//            else marker.setPriority(2);
        }
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
        return true;
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
        if (!(o instanceof Solution)) return false;
        Solution solution = (Solution) o;
        if (positiveIon != solution.positiveIon) return false;
        if (negativeIon != solution.negativeIon) return false;
        if (marker != solution.marker) return false;
        return formula.equals(solution.formula);
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
