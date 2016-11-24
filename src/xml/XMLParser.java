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
import java.util.Map;

public class XMLParser {
	private String _fileName = "Configs/SQLI.xml";
	private ArrayList<String> _entryPoints = new ArrayList<String>();
	private static Map<String, ArrayList<String>> _sinksAndValidators = new Hashtable<String, ArrayList<String>>();
	
	public XMLParser(){
		
	}
	
	public XMLParser(String fileName){
		_fileName = fileName;
	}
	
	public ArrayList<String> getEntryPoints(){
		return _entryPoints;
	}
	
	private String getFileName(){
		return _fileName;
	}
	
	public Map<String, ArrayList<String>> getSinksAndValidators(){
		return _sinksAndValidators;
	}
	
	public void parse(){
		  try {

				File fXmlFile = new File(getFileName());
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);

				doc.getDocumentElement().normalize();

				System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

				NodeList nList = doc.getElementsByTagName("ep");

				System.out.println("----------------------------");

				for (int temp = 0; temp < nList.getLength(); temp++) {

					Node nNode = nList.item(temp);

					System.out.println("\nCurrent Element :" + nNode.getNodeName());

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;
						getEntryPoints().add(eElement.getAttribute("type"));
						System.out.println("EP : " + eElement.getAttribute("type"));
					}
				}
				
				nList = doc.getElementsByTagName("sink");

				System.out.println("----------------------------");

				for (int temp = 0; temp < nList.getLength(); temp++) {

					Node nNode = nList.item(temp);

					System.out.println("\nCurrent Element :" + nNode.getNodeName());

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;
						System.out.println("EP : " + eElement.getAttribute("type"));
						NodeList b = eElement.getElementsByTagName("sanitization");
						ArrayList<String> san = new ArrayList<String>(); 
						for(int c=0; c<b.getLength();c++){
							san.add(eElement.getElementsByTagName("sanitization").item(c).getTextContent());
							System.out.println("sanitization : " + eElement.getElementsByTagName("sanitization").item(c).getTextContent());
						}
						getSinksAndValidators().put(eElement.getAttribute("type"), san);
					}
				}				
			    } catch (Exception e) {
				e.printStackTrace();
			    }
	}
}
