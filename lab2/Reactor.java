package lab2;

import java.util.*;

public class Reactor implements IFReactor {
	private List<IFChemicals> reagents;
	private List<IFChemicals> products;
	
	
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
			double d=0.0;
			if (c.getState()==State.POWDER) {
				d=10.0/c.getMolarMass()/coef[i];
				if (d<minMol) {
					minMol=d;
				}
				mol.put(d,c.getName());
			}
			if (c.getState()==State.LIQUID) {
				d=10.0*c.getDensity()/c.getMolarMass()/coef[i];
				if (d<minMol) {
					minMol=d;
				}
				mol.put(d,c.getName());
			}
			if (c.getState()==State.GAS) {
				mol.put(Double.POSITIVE_INFINITY,c.getName());
			}
		}
		return minMol;
	}
	@Override
	public void execute(Reaction reaction, List<IFChemicals> chem) {
		reagents=new ArrayList<>(chem);
		getProducts(reaction);
		
		StringBuilder sb=new StringBuilder();
		int[] coef=reaction.getReagentsCoeff();
		int j=0;
		for (IFChemicals c:reagents) {
			if(coef[j]>1) {
				sb.append(coef[j]);
				sb.append("*");
				j++;
			}
			else j++;
			sb.append(c.getFormula());
			sb.append(" + ");
		}
		String str=sb.toString();
		str=str.substring(0, str.length()-3);
		sb=new StringBuilder(str);
		sb.append(" -> ");
		coef=reaction.getProductsCoeff();
		j=0;
		for (IFChemicals c:products) {
			if(coef[j]>1) {
				sb.append(coef[j]);
				sb.append("*");
				j++;
			}
			else j++;
			sb.append(c.getFormula());
			sb.append(" + ");
		}
		str=sb.toString();
		str=str.substring(0, str.length()-3);
		System.out.println(str);
		System.out.println("\tReagents");
		for (IFChemicals c:reagents) {
			if (c.getState()==State.POWDER) System.out.println("We take 10 mg of "+c.getName()+" powder.");
			if (c.getState()==State.LIQUID) System.out.println("We take 10 ml of "+c.getName()+" pure solution.");
		}
		System.out.println("\n\tOperations");
		new Operations().execute(reaction.getCondition());
		System.out.println("\n\tProducts");

		
		
		

		double mol=calculateMol(reaction);
		int[] coeff=reaction.getProductsCoeff();
		for (int i=0;i<products.size();i++) {
			IFChemicals c=products.get(i);
			sb=new StringBuilder("We get ");
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
