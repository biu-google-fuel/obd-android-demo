package com.ufo.demo.obd.messages;

import com.openxc.measurements.VehicleSpeed;

public class VehicleSpeedMessage {

	public String vehicleSpeed;
	
	public VehicleSpeedMessage(VehicleSpeed vehicleSpeed){
		this.vehicleSpeed = String.valueOf(vehicleSpeed.getValue().doubleValue());
	}
}
