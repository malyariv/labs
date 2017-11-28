package lab2;

public class Substance implements IFChemicals{
	
	private String name;
	private String formula;
	private State state;
	private double molarMass;
	private Elements[] elements;
	private double density;
	
	public void setDensity(double density) {
		this.density = density;
	}

	public void setElements(Elements[] elements) {
		this.elements=elements.clone();
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
	
	@Override
	public Elements[] dissociation() {
		Elements[] e=elements.clone();
		elements=null;
		return e;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getFormula() {
		return formula;
	}

	@Override
	public State getState() {
		return state;
	}

	@Override
	public double getMolarMass() {
		return molarMass;
	}
	
	@Override
	public Elements[] getElements() {
		return elements.clone();
	}
	@Override
	public double getDensity() {
		return density;
	}
}
