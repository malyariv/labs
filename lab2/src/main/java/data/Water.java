package data;

import interfaces.Chemicals;

public class Water implements Chemicals{

    @Override
    public String getFormula() {
        return "H2O";
    }

    @Override
    public Marker getMarker() {
        return Marker.WATER;
    }

    @Override
    public boolean dissolve() {
        return false;
    }

    @Override
    public Ions getPositiveIon() {
        return null;
    }

    @Override
    public Ions getNegativeIon() {
        return null;
    }

    @Override
    public String toString() {
        return "H2O";
    }
}
