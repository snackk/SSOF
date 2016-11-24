package xml;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class XMLParser {
	private String _fileName = "Configs/SQLI.xml";
	private ArrayList<String> _entryPoints = new ArrayList<String>();
	private static Map<String, ArrayList<String>> _sinksAndValidators = new Hashtable<String, ArrayList<String>>();


	public XMLParser(){
		parse();
	}

	public XMLParser(String fileName){
		_fileName = fileName;
		parse();
	}


	public String getEntryPoints(){
		String built = "";
		for(String s : _entryPoints){
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

	private String getFileName(){
		return _fileName;
	}

	public String getSinksAndValidators(){
		String built = "";
		Iterator<Entry<String, ArrayList<String>>> it = _sinksAndValidators.entrySet().iterator();

		while (it.hasNext()) {
			Entry<String, ArrayList<String>> entry = it.next();
			if(built.equals(""))
				built = entry.getKey();
			else built += "|" + entry.getKey();
		}
		return built;
	}


	private void parse(){
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
					_entryPoints.add(eElement.getAttribute("type"));
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
					_sinksAndValidators.put(eElement.getAttribute("type"), san);
				}
			}				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
