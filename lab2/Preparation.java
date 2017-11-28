package lab2;

import java.util.ArrayList;
import java.util.List;

public class Preparation implements IFPreparation{
	private List<IFChemicals> chemicals=new ArrayList<>();
	
	private void getChemicals(Reaction r) {
		List<String> list=r.getReagents();
		for (String s:list) {
			IFChemicals c=ChemicalFactoryProxy.getChemical(s);
			chemicals.add(c);
		}
	}
	
	public List<IFChemicals> getReagents(Reaction r){
		getChemicals(r);
		return new ArrayList<>(chemicals);
	}
}
