package process.core;

import java.util.HashSet;
import java.util.Set;

public class Configuration {
	public String process_input;
	public String process_output;
	public String[] output_keys;
	public Set<Step> steps;
	
	public Configuration(){
		steps = new HashSet<Step>();
	}
}
