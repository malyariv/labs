package Interfaces;

import dataclasses.Reaction;
import java.util.List;

public interface IFReactor {
    void execute(Reaction reaction, List<IFChemicals> c);
}
