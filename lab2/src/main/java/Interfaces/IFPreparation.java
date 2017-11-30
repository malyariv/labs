package Interfaces;

import dataclasses.Reaction;
import java.util.List;

public interface IFPreparation {
    List<IFChemicals> getReagents(Reaction r);
}
