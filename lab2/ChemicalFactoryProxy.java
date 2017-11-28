package lab2;

import java.util.*;

public class ChemicalFactoryProxy {
	private static Map<String, Substance> cache=new HashMap<>();
	private static ChemicalFactory factory=new ChemicalFactory();
	
	public static IFChemicals getChemical(String classname) {
		if (cache.containsKey(classname)) return cache.get(classname);
		Substance subst=factory.getSubstance(classname);
		cache.put(classname,subst);
		return subst;
	}

}
