package app;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import xml.*;

import entrypoint.*;
import sink.SQLISink;
import sink.Sink;

public class App {
	private static XMLParser xmlParser = new XMLParser();
	
	private static String inputLine = "";
	private static String line = "";

	private static ArrayList<EntryPoint> eps = new ArrayList<EntryPoint>();
	private static EntryPoint ep = null;

	private static ArrayList<Sink> sinks = new ArrayList<Sink>();
	private static Sink sink = null;

	private static Map<String, String> querys = new Hashtable<String, String>();

	private static Boolean isSafe = true;
	private static Boolean isTextOverflow = false;
	
	
	public static Boolean isComplete(String test){
		if(test.charAt(inputLine.length() - 1) == ";".charAt(0))
			return true;
		else return false;
	}
	
	public static void main(String[] args) {
		
		if(args.length == 0){
			System.err.println("An argument is needed!\nExample: java App sqli_01.txt");
			return;
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(args[0]));

			while ((inputLine = br.readLine()) != null) {
				if(isComplete(inputLine)){
					if(isTextOverflow){
						line += inputLine;
						line = line.replace("\n", "").replace("\r", "");
					}else line = inputLine;
					
					
					ep = new SQLIEntryPoint(line, xmlParser.getEntryPoints());
					sink = new SQLISink(line, xmlParser.getSinksAndValidators());

					if(!sink.isSink())
						sink = null;
					else sinks.add(sink);

					if(!ep.isEntryPoint())
						ep = null;
					else eps.add(ep);

					if((ep == null) && (sink == null)){
						if(line.contains("=")){
							String[] parsed = line.split("=", 2);
							querys.put(parsed[0], parsed[1]);
						}
					}
					line = "";
					isTextOverflow = false;
				}else{
					isTextOverflow = true;
					line += inputLine;
					line = line.replace("\n", "").replace("\r", "");
				}	
			}

			if(!sinks.isEmpty()){	/*May have vunerabilities*/
				String query = "";
				
				for(Sink s : sinks){
					
					if(querys.containsKey(s.getFirstArgument())){
						query = querys.get(s.getFirstArgument());
						
						for(EntryPoint e : eps){
							
							if(query.contains(e.getEpVariable())){
								isSafe = false;
								
								System.err.println("Program slice has a vunerability of type " + s.getSinkType() + ".");
								System.err.println(s.toString());
								System.err.println("Variable with user input: '" + e.getEpVariable() + "', and the query: " + query);
								System.err.println("Fix: ");
								
							}
						}
					}
				}
			}
			br.close();
			
			if(isSafe)
				System.out.println("Program slice doesn't contain any type of vunerability.");

			
		}catch(IOException e) {
			System.err.println("Couldn't read the file. Does it exist in the filesystem?");
		}
	}
}
