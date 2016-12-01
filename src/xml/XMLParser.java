package xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {
	private String _fileName;
	private ArrayList<String> _entryPoints = new ArrayList<String>();
	private Map<String, ArrayList<String>> _sinksAndValidators = new Hashtable<String, ArrayList<String>>();
	private ArrayList<String> _validations = new ArrayList<String>();

	public XMLParser(String fileName){
		_fileName = fileName;
		parseXML();
	}

	public String getValidator(String sink){
		return getSinksAndValidatorsAsMap().get(sink).get(0);
	}
	
	protected String getFileName(){
		return _fileName;
	}
	
	protected ArrayList<String> getEntryPointsAsArray(){
		return _entryPoints;
	}
	
	protected Map<String, ArrayList<String>> getSinksAndValidatorsAsMap(){
		return _sinksAndValidators;
	}
	
	protected ArrayList<String> getValidationsAsArray(){
		return _validations;
	}
	
	public String getEntryPoints(){
		String built = "";
		for(String s : getEntryPointsAsArray()){
			if(built.equals("")){
				if(s.contains("$"))
					built = "\\" + s;
			}
			else{
				if(s.contains("$"))
					built += "|\\" + s;
			}
		}
		return built;
	}

	public String getSinks(){
		String built = "";
		Iterator<Entry<String, ArrayList<String>>> it = getSinksAndValidatorsAsMap().entrySet().iterator();

		while (it.hasNext()) {
			Entry<String, ArrayList<String>> entry = it.next();
			if(built.equals(""))
				built = entry.getKey();
			else built += "|" + entry.getKey();
		}
		return built;
	}
	
	public String getValidations(){
		String built = "";
		for(String s : getValidationsAsArray()){
			if(built.equals(""))
				built = s;
			else built += "|" + s;
		}
		return built;
	}
	
	protected void parseXML(){
		try {

			File fXmlFile = new File(getFileName());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("ep");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					getEntryPointsAsArray().add(eElement.getAttribute("type"));
				}
			}

			nList = doc.getElementsByTagName("sink");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					NodeList b = eElement.getElementsByTagName("sanitization");
					ArrayList<String> san = new ArrayList<String>(); 
					for(int c=0; c<b.getLength();c++){
						san.add(eElement.getElementsByTagName("sanitization").item(c).getTextContent());
					}
					getSinksAndValidatorsAsMap().put(eElement.getAttribute("type"), san);
				}
			}

			nList = doc.getElementsByTagName("val");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					getValidationsAsArray().add(eElement.getAttribute("type"));
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
