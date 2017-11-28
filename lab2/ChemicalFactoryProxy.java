package lab2;

import java.util.*;

public class ChemicalFactoryProxy {
	private static Map<String, Substance> cache=new HashMap<>();
	private static ChemicalFactory factory=new ChemicalFactory();
	
	public static IFChemicals getChemical(String s) {
		if (cache.containsKey(s)) return cache.get(s);
		Substance subst=factory.getSubstance(s);
		cache.put(s,subst);
		return subst;
	}

}
