package dofcalculator;

import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {
	

	@FXML
	Label resultLabel;	
	
	@FXML
	TextField inputFocalLength;
	@FXML
	TextField inputAperature;
	@FXML
	TextField inputSensorType;
	
	@FXML
	TextField inputDistance;
	
	@FXML
	Button calculate;
	
	@FXML 
	public void onCalculate() {
		String rawFocallength = inputFocalLength.getText();
		double focallength = Double.parseDouble(rawFocallength);
		
		
		
		System.out.println(focallength + "mm");
		
		
		String rawAperature = inputAperature.getText();
		double aperature = Double.parseDouble(rawAperature);
		
		System.out.println("f" +  aperature);
		
		String SensorType = inputSensorType.getText();
		
		String rawfDistance = inputDistance.getText();
		double distance = Double.parseDouble(rawfDistance);
		
		System.out.println(distance + "m");
		
		
		double coc = selectCircleOfConfusion(SensorType);
		
		System.out.println(coc);
		
		
		double hyperfocal = calculateHyperfocal(focallength,aperature,coc);
		
		System.out.println("hyperfocal: " + hyperfocal);
		
		
		DOFresult result = calculateDOF(hyperfocal, distance, focallength);
		
		
		double NP = result.getNearPoint()/1000;
		double FP = result.getFarPoint()/1000;
		double DOF = result.getDOF()/1000;
		
		String NPStr = String.valueOf(NP);
		String FPStr = String.valueOf(FP);
		String DOFStr = String.valueOf(DOF);
	
		
		System.out.println(NPStr);
		System.out.println(FPStr);
		System.out.println(DOFStr);
		resultLabel.setText("Near Point: " + NPStr + "m \n" +
							"Far Point: " + FPStr + "m \n" +
							"Depth of Field: " + DOFStr + "m \n");
		
	}
	
	

	
	

	public static double calculateHyperfocal(double focallength, double aperature, double coc) {
		double hyperfocal = (focallength * focallength)/(aperature * coc);
		return hyperfocal;
	}
	
	
	public static double selectCircleOfConfusion(String sensorType) {
		double ff = 0.02501;
		double apsc = 0.019948;
		if (sensorType == "Fullframe") {
			return ff;
		}else if(sensorType == "APSC" ) {
			return apsc;
			}else {
				//default
				return ff;
			}
	}
	
	
	public static DOFresult calculateDOF(double hyperfocal, double distance, double focallength){
		double nearPoint = (hyperfocal * distance*1000)/(hyperfocal + (distance*1000 - focallength));
		double farPoint = (hyperfocal * distance*1000)/(hyperfocal - (distance*1000 - focallength));
		DOFresult result = new DOFresult(nearPoint,farPoint);
		return result;
	}
	
	
	
	
	
	

}
