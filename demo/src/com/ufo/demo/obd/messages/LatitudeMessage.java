package com.ufo.demo.obd.messages;

import com.openxc.measurements.Latitude;
import com.ufo.demo.obd.EventAnalayzer;

public class LatitudeMessage {

	public String latitude;
	
	public LatitudeMessage(Latitude latitude){
		this.latitude = EventAnalayzer.cordsformatter.format(latitude.getValue().doubleValue());
	}
}
