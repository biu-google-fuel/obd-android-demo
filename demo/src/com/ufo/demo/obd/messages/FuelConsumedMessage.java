package com.ufo.demo.obd.messages;

import com.openxc.measurements.FuelConsumed;
import com.ufo.demo.obd.EventAnalayzer;

public class FuelConsumedMessage {

	public String fuelConsumed;
	public String driveCost;
	
	public FuelConsumedMessage(FuelConsumed fuelConsumed){
		this.fuelConsumed = EventAnalayzer.formatter.format(fuelConsumed.getValue().doubleValue());
		this.driveCost = EventAnalayzer.formatter.format(fuelConsumed.getValue().doubleValue()*EventAnalayzer.GAS_LITER_RATE);
	}
}
