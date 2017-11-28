package lab2;

import java.util.*;

public class Reactor implements IFReactor {
	private List<IFChemicals> reagents;
	private List<IFChemicals> products=new ArrayList<>();
	
	
	private void getProducts(Reaction reaction) {
		List<String> list=reaction.getProducts();
		double mol=calculateMol(reaction);
		int[] coeff=reaction.getProductsCoeff();
		for (int i=0;i<list.size();i++) {
			IFChemicals c=ChemicalFactoryProxy.getChemical(list.get(i));
			products.add(c);
		}
	}
	private double calculateMol(Reaction reaction) {
		Map<Double,String> mol=new HashMap<>();
		int[] coef=reaction.getReagentsCoeff();
		double minMol=1000000000000.0;
		for (int i=0;i<reagents.size();i++) {
			IFChemicals c=reagents.get(i);
			double d;
			if (c.getState()==State.GAS) {
				d=1000;
			}
			else {
				d=10.0;
			}
			d=d/c.getMolarMass()/coef[i];
			if (c.getState()==State.LIQUID||c.getState()==State.GAS) d=d*c.getDensity();
			if (d<minMol) {
					minMol=d;
			}
			mol.put(d,c.getName());
		}
		return minMol;
	}
	@Override
	public void execute(Reaction reaction, List<IFChemicals> chem) {
		reagents=new ArrayList<>(chem);
		getProducts(reaction);
		System.out.println(reaction.getEquation());
		System.out.println("\tReagents");
		for (IFChemicals c:reagents) {
			switch (c.getState()) {
				case POWDER: System.out.println("We take 10 mg of "+c.getName()+" powder."); break;
				case LIQUID: System.out.println("We take 10 ml of "+c.getName()+" pure solution."); break;
				default: System.out.println("We perform in "+c.getName()); 
			}
		}
		System.out.println("\n\tOperations");
		new Operations().execute(reaction.getCondition());
		System.out.println("\n\tProducts");

		
		
		

		double mol=calculateMol(reaction);
		int[] coeff=reaction.getProductsCoeff();
		for (int i=0;i<products.size();i++) {
			IFChemicals c=products.get(i);
			StringBuilder sb=new StringBuilder("We get ");
			if (c.getState()==State.POWDER) {
				String s=String.format("%.2f", mol*coeff[i]*c.getMolarMass());
				sb.append(s);
				sb.append(" mg of ");
			}
			else {
				String s=String.format("%.2f", mol*coeff[i]*c.getMolarMass()/c.getDensity());
				sb.append(s);
				sb.append(" ml of ");
			}
			sb.append(c.getName());
			sb.append(" ");
			sb.append(c.getState());
			sb.append(".");
			System.out.println(sb);

		}
	}

}
