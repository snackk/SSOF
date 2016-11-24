package sink;

public abstract class Sink {
	private String _inputText;
	private String _patterns;
	private String _sinkPattern;
	private String _firstArgument;/*Could be a variable or a query*/
	private String _sinkType;

	protected Sink(String inputText, String patterns, String sinkType){
		_inputText = inputText;
		_patterns = patterns;
		_sinkType = sinkType;
		parseSink();
	}

	public final Boolean isSink(){
		if(getSinkPattern() == null)	
			return false;
		else return true;
	}
	
	public String getSinkType(){
		return _sinkType;
	}
	
	public String getFirstArgument(){
		return _firstArgument;
	}

	protected String getInputText(){
		return _inputText;
	}

	protected String getPatterns(){
		return _patterns;
	}

	protected String getSinkPattern(){
		return _sinkPattern;
	}


	protected void setPossibleEP(String firstArgument){
		_firstArgument = firstArgument;
	}

	protected void setSinkPattern(String sinkPattern){
		_sinkPattern = sinkPattern;
	}


	protected abstract void parseSink();

	public abstract String toString();
}
