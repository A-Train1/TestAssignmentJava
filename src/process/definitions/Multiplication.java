package process.definitions;

import java.util.HashMap;
import process.core.*;

public class Multiplication extends AProcessStep {
	
	@Override
	public void perform() throws Exception {
		
		double x1 = 0;
		double x2 = 0;
		if(_stepInputContainer.has("x1"))x1 = Double.parseDouble(_stepInputContainer.getValue("x1"));
		if(_stepInputContainer.has("x2"))x2 = Double.parseDouble(_stepInputContainer.getValue("x2"));

		double result = 0;
		result = x1*x2;
		HashMap<String,String> outputValues = new HashMap<String,String>();
		outputValues.put("y", ""+result);
		_stepOputputContainer = new ProcessData(outputValues);
	}
}
