package dofcalculator;

public class DOFresult {
	double nearPoint;
	double farPoint;
	double DOF;
	
	DOFresult(double NPoint, double FPoint){
		nearPoint = NPoint;
		farPoint = FPoint;
		DOF = farPoint - nearPoint;
	}
	
	
	public double getNearPoint() {
		return this.nearPoint;
	}
	
	
	public double getFarPoint() {
		return this.farPoint;
	}
	
	public double getDOF(){
		return this.DOF;
	}
	
}
