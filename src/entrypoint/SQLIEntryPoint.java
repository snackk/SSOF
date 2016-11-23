package entrypoint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLIEntryPoint extends EntryPoint {

	public SQLIEntryPoint(String inputText) {
		super(inputText, "(\\$_GET|\\$_POST|\\$_COOKIE|\\$_REQUEST|"
				+ "HTTP_GET_VARS|HTTP_POST_VARS|HTTP_COOKIE_VARS|HTTP_REQUEST_VARS)");		
	}

	@Override
	protected void parseEntryPoint() {
	    Pattern pattern = Pattern.compile(getPatterns());
	    Matcher matcher = pattern.matcher(getInputText());

        if(matcher.find())
        	setEpPattern(matcher.group());
	}

}
