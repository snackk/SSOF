package entrypoint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLIEntryPoint extends EntryPoint {

	public SQLIEntryPoint(String inputText) {
		super(inputText, "(\\$_GET|\\$_POST|\\$_COOKIE|\\$_REQUEST|"
				+ "HTTP_GET_VARS|HTTP_POST_VARS|HTTP_COOKIE_VARS|HTTP_REQUEST_VARS)");		
	}

	@Override
	protected void parseEntryPoint() {/*try catch here
	 	[0] -> variable side
	 	[1] -> Entry point
	 	*/
		String[] parsed = getInputText().split("=", 2);
		setEpVariable(parsed[0]);
	    Pattern pattern = Pattern.compile(getPatterns());
	    Matcher matcher = pattern.matcher(parsed[1]);

        if(matcher.find())
        	setEpPattern(matcher.group());
	}

}
