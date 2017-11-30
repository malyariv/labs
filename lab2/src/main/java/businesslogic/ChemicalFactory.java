package businesslogic;

import dataclasses.Reaction;
import dataclasses.Substance;
import Interfaces.*;
import dbmysql.ReactionAdapterMySQL;
import dbmysql.SubstAdapterMySQL;

import java.util.*;

public class ChemicalFactory {
    private static Map<String, Substance> cacheSubst =new HashMap<>();

    public static IFChemicals getChemical(String name) {
        if (cacheSubst.containsKey(name)) return cacheSubst.get(name);
        IFSubstanceInfo substanceInfo=SubstAdapterMySQL.getInstance();
        Substance subst=substanceInfo.getSubstance(name);
        cacheSubst.put(name,subst);
        return subst;
    }

}
