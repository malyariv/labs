package Interfaces;

import businesslogic.TestPortion;
import dataclasses.Reaction;
import java.util.List;

public interface IFPreparation {
    List<TestPortion> getReagents(Reaction r);
}
