package Interfaces;

import businesslogic.TestPortion;
import dataclasses.Reaction;
import java.util.List;

public interface IFReactor {
    List<TestPortion> execute(Reaction reaction, List<TestPortion> c);
}
