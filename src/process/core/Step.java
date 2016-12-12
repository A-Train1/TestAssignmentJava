package process.core;

import java.util.HashMap;

public class Step {
	public int number;
	public String class_name;
	public HashMap<String,String> input;
	public HashMap<String,String> output;
	
	public Step(){
		input = new HashMap<String,String>();
		output = new HashMap<String,String>();
	}
}
