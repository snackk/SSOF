package entrypoint;

public abstract class EntryPoint {
	private String _inputText;
	private String _patterns;
	private String _varible;
	private String _entryPoint;	
	private String _epPattern;

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

	public String getVariable(){
		return _varible;
	}

	protected String getEpPattern(){
		return _epPattern;
	}

	public String getEntryPoint(){
		return _entryPoint;
	}

	public final Boolean isEntryPoint(){
		if(getEpPattern() == null)	
			return false;
		else return true;
	}
	
	protected void setEntryPoint(String ep){
		_entryPoint = ep;
	}

	protected void setVariable(String variable){
		_varible = variable;
	}

	protected void setEpPattern(String epPattern){
		_epPattern = epPattern;
	}


	protected abstract void parseEntryPoint();
}
