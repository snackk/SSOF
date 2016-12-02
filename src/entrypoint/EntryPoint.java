package entrypoint;

public abstract class EntryPoint {
	private String _codeSlice;
	private String _patterns;
	
	private String _epPattern;
	private String _entryPointValue;	
	
	private String _varible;
	


	protected EntryPoint(String codeSlice, String patterns){
		_codeSlice = codeSlice;
		_patterns = patterns;
		parseEntryPoint();
	}


	public String getCodeSlice(){
		return _codeSlice;
	}

	protected String getPatterns(){
		return _patterns;
	}

	public String getVariable(){
		return _varible;
	}

	public String getEpPattern(){
		return _epPattern;
	}

	public String getEntryPointValue(){
		return _entryPointValue;
	}

	
	public final Boolean isEntryPoint(){
		if(getEpPattern() == null)	
			return false;
		else return true;
	}
	
	
	protected void setEntryPointValue(String ep){
		_entryPointValue = ep;
	}

	public void setVariable(String variable){
		_varible = variable;
	}

	protected void setEpPattern(String epPattern){
		_epPattern = epPattern;
	}


	protected abstract void parseEntryPoint();
}
