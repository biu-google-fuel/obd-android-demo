package com.ufo.demo.obd.messages;

import com.openxc.measurements.Longitude;
import com.ufo.demo.obd.EventAnalayzer;

public class LongitudeMessage {
	public String longitude;
	
	public LongitudeMessage(Longitude longitude){
		this.longitude = EventAnalayzer.cordsformatter.format(longitude.getValue().doubleValue());
	}
}
