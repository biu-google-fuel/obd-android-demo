package com.ufo.demo.obd;

import java.text.DecimalFormat;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import com.openxc.measurements.EngineSpeed;
import com.openxc.units.KilometersPerHour;
import com.openxc.units.Liter;
import com.openxc.units.RotationsPerMinute;
import com.squareup.otto.Subscribe;
import com.ufo.demo.obd.messages.EngineSpeedMessage;
import com.ufo.demo.obd.messages.FuelConsumedMessage;
import com.ufo.demo.obd.messages.FuelLevelMessage;
import com.ufo.demo.obd.messages.LatitudeMessage;
import com.ufo.demo.obd.messages.LongitudeMessage;
import com.ufo.demo.obd.messages.VehicleSpeedMessage;

import android.widget.LinearLayout;
import android.widget.TextView;

@EBean
public class EventAnalayzer {
	public static double GAS_LITER_RATE = 3.21;
	public static DecimalFormat formatter = new DecimalFormat("0.0##");	
	public static DecimalFormat cordsformatter = new DecimalFormat("0.0#####");
	
	@ViewById(R.id.main_message_table)
	LinearLayout fuelLevelLayout;
	
	@ViewById(R.id.main_msg)
	TextView fuelLevelMainMessage;
	
	@ViewById(R.id.sub_msg1)
	TextView fuelLevelsubMessage1;
	
	@ViewById(R.id.sub_msg2)
	TextView fuelLevelsubMessage2;
	
	@ViewById(R.id.speed)
	TextView speedCaption;
	
	@ViewById(R.id.fuel_consumed)
	TextView fuelConsumedCaption;
	
	@ViewById(R.id.drive_cost)
	TextView driveCostCaption;
	
	@ViewById(R.id.latitude)
	TextView latitudeCaption;
	
	@ViewById(R.id.longitude)
	TextView longtidueCaption;
	
	@ViewById(R.id.engine_speed)
	TextView engineSpeedCaption;
	
	@Bean
	BusProvider busProvider;
	
	public void register(){
		this.busProvider.getEventBus().register(this);
	}
	
	public void unregister(){
		this.busProvider.getEventBus().unregister(this);
	}
	
	@UiThread
	@Subscribe
	public void onFuelLevelUpdate(FuelLevelMessage message){
		fuelLevelLayout.setBackgroundResource(message.background);
		fuelLevelMainMessage.setText(message.mainMessage);
		fuelLevelsubMessage1.setText(message.subMessage_1);
		fuelLevelsubMessage2.setText(message.subMessage_2);
	}
	
	@UiThread
	@Subscribe
	public void onLongitudeUpdate(LongitudeMessage message){
		longtidueCaption.setText(message.longitude);
	}
	
	@UiThread
	@Subscribe
	public void onLatitudeUpdate(LatitudeMessage message){
		latitudeCaption.setText(message.latitude);
	}
	
	@UiThread
	@Subscribe
	public void onEngineSpeedUpdate(EngineSpeedMessage message) {
		engineSpeedCaption.setText(message.engineSpeed);
		
	}
	
	
	@UiThread
	@Subscribe
	public void onFuelConsumptionUpdate(FuelConsumedMessage message) {
		   
		fuelConsumedCaption.setText(message.fuelConsumed);
		driveCostCaption.setText(message.driveCost);
		   
	}
	
	@UiThread
	@Subscribe
	public void onVehicleSpeedUpdate(VehicleSpeedMessage message) {
		   speedCaption.setText(message.vehicleSpeed);
	}

	
	
}
