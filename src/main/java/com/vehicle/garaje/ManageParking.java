package com.vehicle.garaje;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ManageParking {
	
	static Map<Integer,Integer> parkSpace = new HashMap<Integer,Integer>();
    static Map<Integer,ArrayList<String>> saveVehicle = new HashMap<Integer,ArrayList<String>>();

	public ManageParking(int maxSize, int maxLevel) {
    	parkSpace.put(maxLevel, maxSize);
    }
	
	public Map<Integer,Integer> getParking() {
        return parkSpace;
	}

	public boolean vehicleEnter(String licenseNum, int level)
    {
		boolean vehicleExist = false;
        for (Map.Entry<Integer,ArrayList<String>> entry : saveVehicle.entrySet()) {
        	for(int i=0;i<entry.getValue().size();i++) {
        		if(licenseNum.equals(entry.getValue().get(i))) {
        			vehicleExist = true;
            	}
            }
        }
        
        if(!vehicleExist) {
        	ArrayList<String> values=new ArrayList<String>();
    		if(saveVehicle.containsKey(level)) {
    			values.addAll(saveVehicle.get(level));
    			if(values.contains(licenseNum)) {
    				return false;
    			}
    			else {
    				values.add(licenseNum);
    				saveVehicle.put(level, values);
    				return true;
    			}
    		}
    		else {
    			values.add(licenseNum);
    			saveVehicle.put(level, values);
    			return true;
    		}
        }
        else {
        	return false;
        }
		
    }
	
	public boolean vehicleRemove(String licenseNum)
    {
		boolean returnVal = false;
        for (Map.Entry<Integer,ArrayList<String>> entry : saveVehicle.entrySet()) {
        	
        	for(int i=0;i<entry.getValue().size();i++) {
        		if(licenseNum.equals(entry.getValue().get(i))) {
        			entry.getValue().remove(i);
        			returnVal = true;
        			break;
        		}
        	}
        }
        return returnVal;
    }
	
	public String findVehicleLocation(String licenseNum) {
		String level = "";
        String parkLot = "";
        for (Map.Entry<Integer,ArrayList<String>> entry : saveVehicle.entrySet()) {
        	for(int i=0;i<entry.getValue().size();i++) {
        		if(licenseNum.equals(entry.getValue().get(i))) {
        			level = Integer.toString(entry.getKey());
            		parkLot = Integer.toString(i+1);
            	}
            }
        }
        return "Level- " + level +", Lot- " + parkLot;
    }
	
	public boolean insertLevel(int level, int parkingSpace) {
		if(parkSpace.containsKey(level)) {
        	return false;
        }
        else {
        	parkSpace.put(level, parkingSpace);
        }
        return true;
    }
}