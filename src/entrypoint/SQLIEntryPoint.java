package entrypoint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLIEntryPoint extends EntryPoint {

	public SQLIEntryPoint(String inputText, String entryPoints) {
		super(inputText, entryPoints);
	}

	@Override
	protected void parseEntryPoint() {
		
		try{
			String[] parsed = getCodeSlice().split("=", 2);
			setVariable(parsed[0]);
			setEntryPointValue(parsed[1].replace(";", ""));
			Pattern pattern = Pattern.compile(getPatterns());

			Matcher matcher = pattern.matcher(parsed[1]);
	
			if(matcher.find())
				setEpPattern(matcher.group());
			
		}catch(ArrayIndexOutOfBoundsException e){
		
		}
	}
}
