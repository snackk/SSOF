package validation;

public abstract class Validation {
	private String _inputText;
	private String _validations;
	
	protected Validation(String inputText, String validation){
		_inputText = inputText;
		_validations = validation;
	}
	
}
