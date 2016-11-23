package reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader {
	
	private List<String> entryPoints;   //entrypoints
	private List<String> validFuncs;	//validation functions
	private List<String> sinks;			//sensitive sinks
	
	public Reader() {
		this.entryPoints = new ArrayList<String>();
		this.validFuncs = new ArrayList<String>();
		this.sinks = new ArrayList<String>(); 
	}
	
	public List<String> getEntryPoints() {
		return this.entryPoints;
	}
	
	public List<String> getValidFuncs() {
		return this.validFuncs;
	}
	
	public List<String> getSinks() {
		return this.sinks;
	}
		
	public String getEntryPoint(String text) {
		
		String patternString = "(\\$_GET|\\$_POST|\\$_COOKIE|\\$_REQUEST|"
				+ "HTTP_GET_VARS|HTTP_POST_VARS|HTTP_COOKIE_VARS|HTTP_REQUEST_VARS)";
	    Pattern pattern = Pattern.compile(patternString);
	    Matcher matcher = pattern.matcher(text);
        boolean res = matcher.find();
        if(res)
        	return matcher.group();
        return null;
	}
	
	public String getSink(String text) {
		String patternString = "(mysql_query|mysql_unbuffered_query!"
				+ "mysqli_query|mysqli_real_query|mysqli_master_query|mysqli_multi_query|mysqli_stmt_execute)";
	    Pattern pattern = Pattern.compile(patternString);
	    Matcher matcher = pattern.matcher(text);
        boolean res = matcher.find();
        if(res)
        	return matcher.group();
        return null;
	}
	
	public String getValidFunc(String text) {
		String patternString = "(mysql_escape_string|mysql_real_escape_string!"
				+ "mysqli_escape_string|mysqli_real_escape_string|mysqli_stmt_bind_param)";
	    Pattern pattern = Pattern.compile(patternString);
	    Matcher matcher = pattern.matcher(text);
        boolean res = matcher.find();
        if(res)
        	return matcher.group();
        return null;
	}
	

	public static void main(String[] args) {
		
		Reader reader = new Reader();
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				
				//read entry points
				String entryPoint = reader.getEntryPoint(line);
				System.out.println("Entry Point: " + entryPoint);
				if(entryPoint != null)
					reader.getEntryPoints().add(entryPoint);
				
				//read sensitive sinks
				String sink = reader.getSink(line);
				System.out.println("Sink: " + sink);
				if(sink != null)
					reader.getSinks().add(sink);
				
				//read validation functions
				String validFunc = reader.getValidFunc(line);
				System.out.println("Validation: " + validFunc);
				if(validFunc != null)
					reader.getValidFuncs().add(validFunc);
			}
			
			System.out.println("Entry Points: " + reader.getEntryPoints().size());
			System.out.println("Sensitive Sinks: " + reader.getSinks().size());
			System.out.println("Validation Functions: " + reader.getValidFuncs().size());
			
			br.close();
		}catch(IOException e) {
			System.err.println("An input must be passed!");
		}

	}

}