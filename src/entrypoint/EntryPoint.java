package entrypoint;

public abstract class EntryPoint {
	private String _inputText;
	private String _patterns;
	private String _epVariable;
	private String _epPattern;

	
	protected EntryPoint(){
	}
	
	protected EntryPoint(String inputText, String patterns){
		_inputText = inputText;
		_patterns = patterns;
		parseEntryPoint();
	}

	
	public String getInputText(){
		return _inputText;
	}
	
	public String getPatterns(){
		return _patterns;
	}
	
	public String getEpVariable(){
		return _epVariable;
	}
	
	protected void setEpPattern(String epPattern){
		_epPattern = epPattern;
	}
	
	public String getEpPattern(){
		return _epPattern;
	}
	
	protected abstract void parseEntryPoint();
}
