package com.translator.intergalactic.entity;

import java.util.HashMap;
import java.util.Map;

public class Dictionary{
	Map<String, String> lib = new HashMap<String, String>();
	Map<String, Double> stuffLib = new HashMap<String, Double>();
	
	public String getLib(String key) {
		if(this.lib.containsKey(key))
			return lib.get(key);
		else
			return "404";
	}

	public void setLib(String key, String value) {
		if(this.lib.containsKey(key))
			this.lib.remove(key);
		
		if(this.lib.containsValue(value))
			this.lib.values().remove(value);
		
		this.lib.put(key, value);
	}
	
	public Double getStuffLib(String key) {
		if(this.stuffLib.containsKey(key))
			return stuffLib.get(key);
		else
			return 0.0;
	}

	public void setStuffLib(String key, Double value) {
		if(this.stuffLib.containsKey(key))
			this.stuffLib.remove(key);
		
		this.stuffLib.put(key, value);
		//If the a value should not have more than 1 key, then application 
	}
}