package sink;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XSSSink extends Sink{

	public XSSSink(String inputText, String patterns) {
		super(inputText, patterns);
		
	}

	@Override
	protected void parseSink() {
		Pattern pattern = Pattern.compile(getPatterns());
		Matcher matcher = pattern.matcher(getInputText());

		if(matcher.find())
			setSinkPattern(matcher.group());
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
