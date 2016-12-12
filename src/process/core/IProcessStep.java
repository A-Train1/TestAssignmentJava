package process.core;

public interface IProcessStep  {
	
    void perform() throws Exception;  
    void setInput(IProcessData inputData);  
    IProcessData getOutput();
}