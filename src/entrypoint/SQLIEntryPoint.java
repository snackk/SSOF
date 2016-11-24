package entrypoint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLIEntryPoint extends EntryPoint {

	public SQLIEntryPoint(String inputText, String entryPoints) {
		super(inputText, entryPoints);
	}

	@Override
	protected void parseEntryPoint() {
		
		String[] parsed = getInputText().split("=", 2);
		setEpVariable(parsed[0]);
		Pattern pattern = Pattern.compile(getPatterns());
		Matcher matcher = pattern.matcher(parsed[1]);

		if(matcher.find())
			setEpPattern(matcher.group());
	}

}
