package Interfaces;

import dataclasses.Reaction;
import java.util.Map;

public interface IFReactionInfo {
    Map<String, Reaction> getReactions();

    Map<String,String> getNames();
}
