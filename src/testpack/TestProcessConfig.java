package testpack;

import java.util.HashMap;

public class TestProcessConfig {
	
	public String GetTestProcessConfig(){
		return "<configuration> "
				+ "<process_input>input.properties</process_input> "
				+ "<process_output>output.properties</process_output> "
				+ "<output_keys>result1,result2</output_keys> "
				+ "<step>  <number>1</number>  <class>process.definitions.Multiplication</class>  <input>a:x1,c:x2</input>  <output>y:ac</output> </step> "
				+ "<step>  <number>2</number>  <class>process.definitions.Multiplication</class>  <input>constant4:x1,ac:x2</input>  <output>y:4ac</output> </step> "
				+ "<step>  <number>3</number>  <class>process.definitions.Square</class>  <input>b:x1</input>  <output>y:squareb</output> </step>"
				+ "<step>  <number>4</number>  <class>process.definitions.Difference</class>  <input>squareb:x1,4ac:x2</input>  <output>y:discriminant</output> </step> "
				+ "<step>  <number>5</number>  <class>process.definitions.SquareRoot</class>  <input>discriminant:x1</input>  <output>y:squarerootdiscriminant</output> </step> "
				+ "<step>  <number>6</number>  <class>process.definitions.Negative</class>  <input>b:x1</input>  <output>y:negb</output> </step> "
				+ "<step>  <number>7</number>  <class>process.definitions.Sum</class>  <input>negb:x1,squarerootdiscriminant:x2</input>  <output>y:result1</output> </step>"
				+ "<step>  <number>8</number>  <class>process.definitions.Difference</class>  <input>negb:x1,squarerootdiscriminant:x2</input>  <output>y:result2</output> </step> "
				+ "<step>  <number>9</number>  <class>process.definitions.Multiplication</class>  <input>constant2:x1,a:x2</input>  <output>y:2a</output> </step> "
				+ "<step>  <number>10</number>  <class>process.definitions.Division</class>  <input>result1:x1,2a:x2</input>  <output>y:result1</output> </step> "
				+ "<step>  <number>11</number>  <class>process.definitions.Division</class>  <input>result2:x1,2a:x2</input>  <output>y:result2</output> </step> "
				+ "</configuration>";
	}
	
	public HashMap<String,String> GetTestInputValues(){
		
		HashMap<String,String> testInputMap = new HashMap<String,String>();
		
		testInputMap.put("a", "2");
		testInputMap.put("b", "-7");
		testInputMap.put("c", "3");
		testInputMap.put("constant2", "2");
		testInputMap.put("constant4", "4");
		
		return testInputMap;
	}
}
