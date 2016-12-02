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
			String[] parser = null;
			
			if(getCodeSlice().contains("=")){
				parser = getCodeSlice().split("=", 2);
				setVariable(parser[0]);

			}else{
				if(getCodeSlice().contains(" ")){
					parser = getCodeSlice().split(" ", 2);
				}
			}
			setEntryPointValue(parser[1]);

			Pattern pattern = Pattern.compile(getPatterns());

			Matcher matcher = pattern.matcher(parser[1]);
	
			if(matcher.find())
				setEpPattern(matcher.group());
			
		}catch(Exception e){
		
		}
	}
}
