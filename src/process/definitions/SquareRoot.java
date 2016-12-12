package process.definitions;

import java.util.HashMap;
import process.core.AProcessStep;
import process.core.ProcessData;

public class SquareRoot extends AProcessStep{

	@Override
	public void perform() throws Exception {
		double x1 = 0;

		if(_stepInputContainer.has("x1"))x1 = Double.parseDouble(_stepInputContainer.getValue("x1"));

		double result = 0;

			result = Math.sqrt(x1);

		HashMap<String,String> outputValues = new HashMap<String,String>();
		outputValues.put("y", ""+result);
		_stepOputputContainer = new ProcessData(outputValues);
	}

}
