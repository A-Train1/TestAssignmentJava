package process.core;

public abstract class AProcessStep  implements IProcessStep {
	
	public Step _stepConf;
	protected IProcessData _stepInputContainer;
	protected IProcessData _stepOputputContainer;
	
	public void setInput(IProcessData input){
		_stepInputContainer = input;
	}  
	
    public IProcessData getOutput(){

		return _stepOputputContainer;
    }
    
    public void SetStepConf(Step stepConf){
    	_stepConf = stepConf;
    }
    
    public static IProcessData CastOuterToInner(IProcessData outer){
    	
		return outer;
    	
    }
} 
