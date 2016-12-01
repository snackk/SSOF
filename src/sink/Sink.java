package sink;

public abstract class Sink {
	private String _inputText;
	private String _patterns;
	private String _sinkPattern;
	private String _firstArgument;

	protected Sink(String inputText, String patterns){
		_inputText = inputText;
		_patterns = patterns;
		parseSink();
	}

	public final Boolean isSink(){
		if(getSinkPattern() == null)	
			return false;
		else return true;
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

	public String getSinkPattern(){
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
