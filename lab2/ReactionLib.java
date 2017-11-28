package lab2;

import java.util.*;

public class ReactionLib {
	
	private static Map<String, Reaction> lib=new HashMap<>();
	static {
		Reaction reaction=new Reaction();
		List<String> reagents=new ArrayList<>();
		reagents.add("Copper");
		reagents.add("AcidSulfuric");
		reaction.setReagents(reagents);
		List<String> products=new ArrayList<>();
		products.add("SulfateCopperII");
		products.add("OxideSulfurIV");
		products.add("Water");
		reaction.setProducts(products);
		
		Condition c=new Condition();
		c.setTemperature(70);
		c.setExhaust(true);
		reaction.setCondition(c);
		reaction.setYield(0.75);
		reaction.setReagentsCoeff(new int[] {1,2});
		reaction.setProductsCoeff(new int[] {1,1,2});
		lib.put("blue vitriol", reaction);
		lib.put("copper(II) sulfate", reaction);
		lib.put("cupric sulfate", reaction);
	}
	
	public static Reaction getReaction(String s) {
		return lib.get(s);
	}

}
