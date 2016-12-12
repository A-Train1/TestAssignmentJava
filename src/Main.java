import process.core.ProcessRunner;
import process.properties.ProcessDataContext;
import testpack.TestProcessConfig;

public class Main {

	static ProcessRunner process;
	
	public static void main(String[] args) throws Exception {
		ProcessDataContext.context = new TestProcessConfig().GetTestInputValues();
		process = new ProcessRunner();	
	}
}
