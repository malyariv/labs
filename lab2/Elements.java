package lab2;

public enum Elements {
	H(1.0),C(12.0), N(14.0),O(16.0), Na(23.0), Mg(24.3), Al(27.0),
	Si(28.1), P(31.0), S(32.1), Cl(35.4), K(39.1), Ca(40.1), Fe(55.8),
	Cu(63.5), Ag(107.9), Au(197), Pb(207.2);
	private double atomicWeight;
	Elements(double aw){
		atomicWeight=aw;
	}
	public double getAtomicWeight() {
		return atomicWeight;
	}
}
