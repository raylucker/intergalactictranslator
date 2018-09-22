package com.translator.intergalactic.entity;

import java.util.HashMap;
import java.util.Map;

public class Dictionary{
	Map<String, String> lib = new HashMap<String, String>();
	Map<String, Double> stuffLib = new HashMap<String, Double>();

	public String getLib(String key) {
		return lib.get(key);
	}

	public void setLib(String key, String value) {
		this.lib.put(key, value);
		//If the a value should not have more than 1 key, then application 
	}
	
	public Double getStuffLib(String key) {
		return stuffLib.get(key);
	}

	public void setStuffLib(String key, Double value) {
		this.stuffLib.put(key, value);
		//If the a value should not have more than 1 key, then application 
	}
}