package com.ufo.demo.obd;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;

import android.widget.LinearLayout;
import android.widget.TextView;


import com.openxc.measurements.EngineSpeed;
import com.openxc.measurements.FuelConsumed;
import com.openxc.measurements.FuelLevel;
import com.openxc.measurements.Latitude;
import com.openxc.measurements.Longitude;
import com.openxc.measurements.Measurement;
import com.openxc.measurements.UnrecognizedMeasurementTypeException;
import com.openxc.measurements.VehicleSpeed;
import com.openxc.remote.VehicleServiceException;
import com.openxc.units.Degree;
import com.openxc.units.KilometersPerHour;
import com.openxc.units.Liter;
import com.openxc.units.RotationsPerMinute;
import com.squareup.otto.Subscribe;
import com.ufo.demo.obd.connection.VehicleManagerConnector;
import com.ufo.demo.obd.connection.VehicleManagerConnector.VehicleManagerConnectorCallback;
import com.ufo.demo.obd.messages.EngineSpeedMessage;
import com.ufo.demo.obd.messages.FuelConsumedMessage;
import com.ufo.demo.obd.messages.FuelLevelMessage;
import com.ufo.demo.obd.messages.LatitudeMessage;
import com.ufo.demo.obd.messages.LongitudeMessage;
import com.ufo.demo.obd.messages.VehicleSpeedMessage;

@EActivity(R.layout.obd_details_screen)
public class ObdActivity extends Activity implements VehicleManagerConnectorCallback{
	
	@Bean
	VehicleManagerConnector mVMmConnector;
	
	@Bean
	BusProvider busProvider;
	
	@Bean
	EventAnalayzer mEventAnalyzer;
	
	
	
	private Latitude.Listener latitudeListener = new Latitude.Listener() {
		@Override
		public void receive(Measurement measurement) {
			final Latitude latitude = (Latitude) measurement;
			busProvider.getEventBus().post(new LatitudeMessage(latitude));
		}
	};
	
	private Longitude.Listener longitudeListener = new Longitude.Listener() {
		@Override
		public void receive(Measurement measurement) {
			final Longitude longitude = (Longitude) measurement;
			busProvider.getEventBus().post(new LongitudeMessage(longitude));
		}

	};

	private EngineSpeed.Listener engineSpeedListener = new EngineSpeed.Listener() {
		@Override
		public void receive(Measurement measurement) {
			final EngineSpeed engineSpeed = (EngineSpeed) measurement;
			busProvider.getEventBus().post(new EngineSpeedMessage(engineSpeed));
		}

	};

	
	private VehicleSpeed.Listener vehicleSpeedListener = new VehicleSpeed.Listener() {
		@Override
		public void receive(Measurement measurement) {
			final VehicleSpeed vehicleSpeed = (VehicleSpeed) measurement;
			busProvider.getEventBus().post(new VehicleSpeedMessage(vehicleSpeed));

		}
	};
	
	private FuelConsumed.Listener fuelConsumedListener = new FuelConsumed.Listener() {
		@Override
		public void receive(Measurement measurement) {
			final FuelConsumed fuelConsumed = (FuelConsumed) measurement;
			busProvider.getEventBus().post(new FuelConsumedMessage(fuelConsumed));
		}
	};
	
	private FuelLevel.Listener fuelLevelListener = new FuelLevel.Listener() {
		@Override
		public void receive(Measurement measurement) {
			final FuelLevel fuelLevel = (FuelLevel) measurement;
			busProvider.getEventBus().post(new FuelLevelMessage(fuelLevel));
			
		}
	};

	
	@Override
	protected void onPause() {
		busProvider.getEventBus().unregister(this);
		mEventAnalyzer.unregister();
		// Unbind from VM
		mVMmConnector.unbindFromVehicleManager();
		super.onPause();
		
		
	}

	@Override
	protected void onResume() {
		busProvider.getEventBus().register(this);
		mEventAnalyzer.register();
		// Bind to VM
		mVMmConnector.bindToVehicleManager(this);
		super.onResume();
		
		
	}

	@Override
	@UiThread
	public void onVMConnected() {
		// TODO Auto-generated method stub
		try {
			mVMmConnector.getVehicleManager().addListener(FuelLevel.class, fuelLevelListener);
			mVMmConnector.getVehicleManager().addListener(VehicleSpeed.class, vehicleSpeedListener);
			mVMmConnector.getVehicleManager().addListener(FuelConsumed.class, fuelConsumedListener);
			mVMmConnector.getVehicleManager().addListener(Latitude.class, latitudeListener);
			mVMmConnector.getVehicleManager().addListener(Longitude.class, longitudeListener);
			mVMmConnector.getVehicleManager().addListener(EngineSpeed.class, engineSpeedListener);
		} catch (VehicleServiceException e) {
			e.printStackTrace();
		} catch (UnrecognizedMeasurementTypeException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onVMDisconnected() {
		// TODO Auto-generated method stub
		
	}

	
	
}
