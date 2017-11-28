package lab2;

import java.util.*;

public class ChemicalFactory {
	private Map<String, SubstanceConfig> map=new HashMap<>();
	{
		SubstanceConfig sc=new SubstanceConfig();
		
		sc.setName("Copper");
		sc.setFormula("Cu");
		sc.setState(State.POWDER);
		sc.setMolarMass(63.5);
		sc.setElements(new Elements[]{Elements.Cu});
		sc.setDensity(8.9);
		map.put("Copper", sc);
		
		sc=new SubstanceConfig();
		sc.setName("Sulfuric acid");
		sc.setFormula("H2SO4");
		sc.setState(State.LIQUID);
		sc.setMolarMass(98.1);
		sc.setElements(new Elements[]{Elements.H,Elements.H,Elements.S,
				Elements.O,Elements.O,Elements.O,Elements.O});
		sc.setDensity(1.84);
		map.put("AcidSulfuric", sc);

		sc=new SubstanceConfig();
		sc.setName("Copper(II) sulfate");
		sc.setFormula("CuSO4");
		sc.setState(State.POWDER);
		sc.setMolarMass(159.6);
		sc.setElements(new Elements[]{Elements.Cu,Elements.S,
				Elements.O,Elements.O,Elements.O,Elements.O});
		sc.setDensity(3.6); 
		map.put("SulfateCopperII", sc);	
		
		sc=new SubstanceConfig();
		sc.setName("Water");
		sc.setFormula("H2O");
		sc.setElements(new Elements[]{Elements.H,Elements.H,Elements.O});
		sc.setState(State.LIQUID);
		sc.setMolarMass(18.0);
		sc.setDensity(1.0);
		map.put("Water", sc);
	
		sc=new SubstanceConfig();
		sc.setName("Sulfur(IV) oxide");
		sc.setFormula("SO4");
		sc.setState(State.GAS);
		sc.setMolarMass(64.0);
		sc.setElements(new Elements[]{Elements.S,Elements.O,Elements.O});
		sc.setDensity(0.00263);
		map.put("OxideSulfurIV", sc);
	}
	
	public Substance getSubstance(String s) {
		Substance subst=new Substance();
		SubstanceConfig sc=map.get(s);
		subst.setName(sc.getName());
		subst.setFormula(sc.getFormula());
		subst.setMolarMass(sc.getMolarMass());
		subst.setState(sc.getState());
		subst.setElements(sc.getElements());
		subst.setDensity(sc.getDensity());
		return subst;
	}


}
