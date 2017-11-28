package lab2;

public class Condition {
	private Integer temperature=null;
	private boolean electrolysis=false, 
			exhaust=false;
	private String catalyst=null;
	
	
	public Integer getTemperature() {
		return temperature;
	}
	public boolean needElectrolysis() {
		return electrolysis;
	}
	public boolean needExhaust() {
		return exhaust;
	}
	public String getCatalyst() {
		return catalyst;
	}
	public void setCatalyst(String catalyst) {
		this.catalyst = catalyst;
	}
	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}
	public void setElectrolysis(boolean electrolysis) {
		this.electrolysis = electrolysis;
	}
	public void setExhaust(boolean exhaust) {
		this.exhaust = exhaust;
	}
	
	
	

}
