package entrypoint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XSSEntryPoint extends EntryPoint {

	public XSSEntryPoint(String inputText, String entryPoints) {
		super(inputText, entryPoints);
	}

	@Override
	protected void parseEntryPoint() {
		
		try{
			Pattern pattern = Pattern.compile(getPatterns());

			Matcher matcher = pattern.matcher(getInputText());
	
			if(matcher.find())
				setEpPattern(matcher.group());
			
		}catch(ArrayIndexOutOfBoundsException e){
		
		}
	}
}
