package sink;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLISink extends Sink{
	
	public SQLISink(String inputText, String sinks) {
		super(inputText, sinks);
	}

	@Override
	protected void parseSink() {
		Pattern r = Pattern.compile("\\((.*?)\\)");
		Matcher m = r.matcher(getCodeSlice());

		if(m.find()){
			String[] aux = m.group(1).split(",", 2);
			setFirstArgument(aux[0]);
		}

		Pattern pattern = Pattern.compile(getPatterns());
		Matcher matcher = pattern.matcher(getCodeSlice());

		if(matcher.find())
			setSinkPattern(matcher.group());
	}

	@Override
	public String toString() {
		return "Query stored on '" + getFirstArgument() + "', this could lead to a SQL Injection.";
	}
}
