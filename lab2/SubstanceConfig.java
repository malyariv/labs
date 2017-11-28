package lab2;

public class SubstanceConfig {
	private String name;
	private String formula;
	private State state;
	private double molarMass;
	private Elements[] elements;
	private double density;
	
	
	public double getDensity() {
		return density;
	}
	public void setDensity(double density) {
		this.density = density;
	}
	public Elements[] getElements() {
		return elements;
	}
	public void setElements(Elements[] elements) {
		this.elements = elements.clone();
	}
	public String getName() {
		return name;
	}
	public String getFormula() {
		return formula;
	}
	public State getState() {
		return state;
	}
	public double getMolarMass() {
		return molarMass;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public void setState(State state) {
		this.state = state;
	}
	public void setMolarMass(double molarMass) {
		this.molarMass = molarMass;
	}
	
}
