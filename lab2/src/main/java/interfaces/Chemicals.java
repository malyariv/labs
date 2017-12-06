package interfaces;

import data.*;
import java.util.List;

public interface Chemicals {
    String getFormula();
    Marker getMarker();
    boolean dissolve();
    Ions getPositiveIon();
    Ions getNegativeIon();

}
