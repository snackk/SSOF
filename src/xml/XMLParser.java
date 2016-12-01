package xml;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public abstract class XMLParser {
	private String _fileName;
	private ArrayList<String> _entryPoints = new ArrayList<String>();
	private Map<String, ArrayList<String>> _sinksAndValidators = new Hashtable<String, ArrayList<String>>();
	private ArrayList<String> _validations = new ArrayList<String>();

	protected XMLParser(String fileName){
		_fileName = fileName;
		parseXML();
	}

	public abstract String getEntryPoints();
	public abstract String getSinks();
	public abstract String getValidations();
	protected abstract void parseXML();
	public abstract String getValidator(String sink);
	
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
}
