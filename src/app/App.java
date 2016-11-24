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
	private static int lineNr = 0;

	private static ArrayList<EntryPoint> eps = new ArrayList<EntryPoint>();
	private static EntryPoint ep = null;

	private static ArrayList<Sink> sinks = new ArrayList<Sink>();
	private static Sink sink = null;

	private static Map<String, String> querys = new Hashtable<String, String>();

	private static Boolean isSafe = true;


	public static void main(String[] args) {
		
		if(args.length == 0){
			System.err.println("An argument is needed!\nExample: java App sqli_01.txt");
			return;
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(args[0]));

			System.out.println("/* DEBUG");/*DEBUG*/

			while ((inputLine = br.readLine()) != null) {
				if(inputLine.charAt(inputLine.length() - 1) == ";".charAt(0)){
					lineNr++;
					line = inputLine;

					System.out.println(line + " N " + lineNr);/*DEBUG*/
					
					ep = new SQLIEntryPoint(line, xmlParser.getEntryPoints());
					sink = new SQLISink(line, xmlParser.getSinksAndValidators());

					if(!sink.isSink())
						sink = null;
					else sinks.add(sink);

					if(!ep.isEntryPoint())
						ep = null;
					else eps.add(ep);

					if((ep == null) && (sink == null)){
						if(inputLine.contains("=")){
							String[] parsed = inputLine.split("=", 2);
							querys.put(parsed[0], parsed[1]);
						}
					}
				}else{
					line += inputLine;
					line = line.replace("\n", "").replace("\r", "");
				}	
			}

			System.out.println("*/ DEBUG");/*DEBUG*/

			if(!sinks.isEmpty()){	/*May have vunerabilities*/
				String query = "";
				for(Sink s : sinks){
					System.out.println("DEBUG SINK: First argument: " + s.getFirstArgument());
					if(querys.containsKey(s.getFirstArgument())){

						query = querys.get(s.getFirstArgument());
						System.out.println("CONTEM " + query);
						for(EntryPoint e : eps){
							if(query.contains(e.getEpVariable())){
								System.out.println("Funcao nao segura");/*Chamar validador!*/
								isSafe = false;
							}
						}
					}
				}
			}
			br.close();
			
			if(isSafe)
				System.out.println("Program slice doesn't contain a vunerability.");

			
		}catch(IOException e) {
			System.err.println("Couldn't read the file. Does it exist in the filesystem?");
		}
	}
}
