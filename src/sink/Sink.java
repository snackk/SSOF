package sink;

public abstract class Sink {
	private String _inputText;
	private String _patterns;
	private String _sinkPattern;
	private String _possibleEP;

	
	protected Sink(){
	}
	
	protected Sink(String inputText, String patterns){
		_inputText = inputText;
		_patterns = patterns;
		parseSink();
	}

	
	protected String getPossibleEP(){
		return _possibleEP;
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
	
	public final Boolean isSink(){
		if(getSinkPattern() == null)	
			return false;
		else return true;
	}

	
	protected void setPossibleEP(String possibleEP){
		_possibleEP = possibleEP;
	}
	
	protected void setSinkPattern(String sinkPattern){
		_sinkPattern = sinkPattern;
	}
	
	
	protected abstract void parseSink();
	
	public final String toString(){
		return "Sink " + getSinkPattern() + " & Possible Entry Point " + getPossibleEP();
	}
}
