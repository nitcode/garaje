package com.vehicle.garaje;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ManageParkingTest {

	@Test
	public void testVehicleEnter() {
		ManageParking manageParking=new ManageParking(2,1);
		boolean result = manageParking.vehicleEnter("KN32145", 1);
		assertSame( true, result);
	}
	
	@Test
	public void testVehicleExist() {
		ArrayList<String> licesneNumber=new ArrayList<String>();
		licesneNumber.add("KN32145");
		ManageParking manageParking=new ManageParking(2,1);
		ManageParking.saveVehicle.put(1, licesneNumber);
		boolean result = manageParking.vehicleEnter("KN32145", 1);
		assertSame( false, result);
	}
	
	@Test
	public void testVehicleRemove() {
		ArrayList<String> licesneNumber=new ArrayList<String>();
		licesneNumber.add("KN32145");
		ManageParking manageParking=new ManageParking(2,1);
		ManageParking.saveVehicle.put(1, licesneNumber);
		boolean result = manageParking.vehicleRemove("KN32145");
		assertSame( true, result);
	}
	
	@Test
	public void testVehicleLocate() {
		ArrayList<String> licesneNumber=new ArrayList<String>();
		licesneNumber.add("KN32145");
		ManageParking manageParking=new ManageParking(2,1);
		ManageParking.saveVehicle.put(1, licesneNumber);
		String result = manageParking.findVehicleLocation("KN32145");
		System.out.println(result.length());
		assertEquals( "Level- 1, Lot- 1", result);
	}
}
