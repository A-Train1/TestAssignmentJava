package process.core;

import java.util.HashMap;

public class ProcessData implements IProcessData {
	
	HashMap<String,String> _keyValuePairs;
	
	public ProcessData(HashMap<String,String> keyValuePairs){
		_keyValuePairs = keyValuePairs;
	}
	
	public boolean has(String key){
		
		if(_keyValuePairs.containsKey(key))return true;
		else return false;
	}  
	
	public String getValue(String key){
		
		return _keyValuePairs.get(key);
	}
}
