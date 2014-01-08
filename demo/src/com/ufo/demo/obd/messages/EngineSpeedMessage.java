package com.ufo.demo.obd.messages;

import com.openxc.measurements.EngineSpeed;

public class EngineSpeedMessage {

	public String engineSpeed;
	
	public EngineSpeedMessage(EngineSpeed engineSpeed){
		this.engineSpeed = String.valueOf(engineSpeed.getValue().intValue());
	}
}
