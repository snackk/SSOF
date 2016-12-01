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
		Matcher m = r.matcher(getInputText());

		if(m.find()){
			String[] aux = m.group().split(",", 2);
			aux[0] = aux[0].replace("(", "");
			setPossibleEP(aux[0]);
		}

		while(m.find()) {
			System.out.println(m.group(1));
		}

		Pattern pattern = Pattern.compile(getPatterns());
		Matcher matcher = pattern.matcher(getInputText());

		if(matcher.find())
			setSinkPattern(matcher.group());
	}

	@Override
	public String toString() {
		return "Query stored on '" + getFirstArgument() + "', this could lead to a SQL Injection.";
	}

}
