package lab2;

public interface IFChemicals {
	String getName();
	String getFormula();
	State getState();
	double getDensity();
	double getMolarMass();
	Elements[] getElements();
	Elements[] dissociation();
}