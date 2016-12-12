package process.core;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import process.properties.ProcessDataContext;
import testpack.TestProcessConfig;

public class ProcessRunner {
	
	List<AProcessStep> processSteps;
	String[] outputKeys;
	
	public ProcessRunner() throws Exception{
		Start();
	}
	
	List<AProcessStep> GenerateSteps() throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		List<AProcessStep> steps = new ArrayList<AProcessStep>();
		
		Configuration config = ReadConfig(new TestProcessConfig().GetTestProcessConfig());
		outputKeys = config.output_keys;
		
		int size = config.steps.size();
		for(int i = 0; i<size; i++){
			Step step = null;
			for(Step step0 : config.steps){
				if(step0.number == i+1)step = step0;
			}
			Class<?> c = Class.forName(step.class_name);
			Constructor<?> constructor = c.getConstructor();
			Object instance = constructor.newInstance();
			AProcessStep s = (AProcessStep) instance;
			s.SetStepConf(step);
			steps.add(s);
		}
		return steps;
	}
	
	public void Start() throws Exception{
		
		processSteps = GenerateSteps();
		for(AProcessStep s : processSteps){
			HashMap<String,String> map = new HashMap<String,String>();
			for(String str : s._stepConf.input.keySet()){
				if(ProcessDataContext.context.containsKey(str))map.put(s._stepConf.input.get(str), ProcessDataContext.context.get(str));
			}
			s.setInput(new ProcessData(map));
			s.perform();
			IProcessData output = s.getOutput();
			for(String e : s._stepConf.output.keySet()){
				if(output.has(e))ProcessDataContext.context.put(s._stepConf.output.get(e),output.getValue(e));
			}
		}
		Finish();
	}
	
	public void Finish(){
		for(String key : outputKeys){
			if(ProcessDataContext.context.containsKey(key))System.out.println(key+":"+ProcessDataContext.context.get(key));
		}
	}
	
	Configuration ReadConfig(String config) throws ParserConfigurationException, SAXException, IOException {
		
		Configuration conf = new Configuration();
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(config));
		Document dom = documentBuilder.parse(is);
		Element docEle = dom.getDocumentElement();
		NodeList nl =  docEle.getChildNodes();
	    if (nl != null) {
	        int length = nl.getLength();
	        for (int i = 0; i < length; i++) {
	            if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
	            	Element el = (Element) nl.item(i);
	            	if(el.getNodeName().contains("output_keys")){
	            		String superString = el.getTextContent();
	            		conf.output_keys = superString.split(",");
	            	}
	                if (el.getNodeName().contains("step")) {
	                	Step s = new Step();
	                	NodeList elnl =  ((Node) el).getChildNodes();
	            	    if (elnl != null) {
	            	        int length2 = elnl.getLength();
	            	        for (int k = 0; k < length2; k++) {
	            	            if (elnl.item(k).getNodeType() == Node.ELEMENT_NODE) {
	            	            	Element el2 = (Element) elnl.item(k);
	            	            	if(el2.getNodeName().contains("number"))s.number = Integer.parseInt(el2.getTextContent());
	            	            	if(el2.getNodeName().contains("class"))s.class_name = el2.getTextContent();
	            	            	if(el2.getNodeName().contains("input")){
	            	            		String superString = el2.getTextContent();
	            	            		String[] keypairs = superString.split(",");
	            	            		for(String keypair : keypairs){
	            	            			String[] keys = keypair.split(":");
	            	            			s.input.put(keys[0], keys[1]);
	            	            		}
	            	            	}
	            	            	if(el2.getNodeName().contains("output")){
	            	            		String superString = el2.getTextContent();
	            	            		String[] keypairs = superString.split(",");
	            	            		for(String keypair : keypairs){
	            	            			String[] keys = keypair.split(":");
	            	            			s.output.put(keys[0], keys[1]);
	            	            		}
	            	            	}
	            	            }
	            	        }
	            	    }
	            	    conf.steps.add(s);
	                }
	            }
	        }
	    }
		return conf;
	}
}

