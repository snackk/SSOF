package entrypoint;

public abstract class EntryPoint {
	private String _inputText;
	private String _patterns;
	private String _epVariable;
	private String _epPattern;
	private String _epElement;

	
	protected EntryPoint(){
	}
	
	protected EntryPoint(String inputText, String patterns){
		_inputText = inputText;
		_patterns = patterns;
		parseEntryPoint();
	}

	
	protected String getInputText(){
		return _inputText;
	}
	
	protected String getPatterns(){
		return _patterns;
	}
	
	public String getEpVariable(){
		return _epVariable;
	}
	
	protected String getEpPattern(){
		return _epPattern;
	}
	
	protected String getEpElement(){
		return _epElement;
	}
	
	public final Boolean isEntryPoint(){
		if(getEpPattern() == null)	
			return false;
		else return true;
	}
	protected void setEpElement(String epElement){
		_epElement = epElement;
	}
	
	protected void setEpVariable(String epVariable){
		_epVariable = epVariable;
	}
	
	protected void setEpPattern(String epPattern){
		_epPattern = epPattern;
	}
	
	
	protected abstract void parseEntryPoint();
	
	public final String toString(){
		return "Variable " + getEpVariable() + " & Pattern " + getEpPattern();
	}
}
