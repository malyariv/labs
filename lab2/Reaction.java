package lab2;

import java.util.*;

public class Reaction {
	private List<String> reagents=new ArrayList<>();
	private List<String> products=new ArrayList<>();
	private Condition condition;
	private double yield;
	private int[] reagentsCoeff, productsCoeff;
	
	public int[] getReagentsCoeff() {
		return reagentsCoeff.clone();
	}
	public int[] getProductsCoeff() {
		return productsCoeff.clone();
	}
	public void setReagentsCoeff(int[] reagentsCoeff) {
		this.reagentsCoeff = reagentsCoeff.clone();
	}
	public void setProductsCoeff(int[] productsCoeff) {
		this.productsCoeff = productsCoeff.clone();
	}
	public List<String> getProducts() {
		return new ArrayList<>(products);
	}
	public void setProducts(List<String> products) {
		this.products = new ArrayList<>(products);
	}
	public List<String> getReagents() {
		List<String> r=new ArrayList<>(reagents);
		return r;
	}
	public void setReagents(List<String> reagents) {
		this.reagents = new ArrayList<>(reagents);
	}
	public Condition getCondition() {
		return condition; // инкапсуляция!
	}
	public void setCondition(Condition condition) {
		this.condition = condition; // инкапсуляция!
	}

	public double getYield() {
		return yield;
	}
	public void setYield(double yield) {
		this.yield = yield;
	}
	public String toString() {
		return reagents.toString();
	}

}
