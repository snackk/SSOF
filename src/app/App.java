package app;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import entrypoint.*;
import sink.SQLISink;
import sink.Sink;

public class App {
	private static String inputLine = "";
	private static String line = "";
	private static int lineNr = 0;
	
	private static ArrayList<EntryPoint> eps = new ArrayList<EntryPoint>();
	private static EntryPoint ep = null;
	
	private static ArrayList<Sink> sinks = new ArrayList<Sink>();
	private static Sink sink = null;
	
	public static void main(String[] args) {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
			
			System.out.println("/* DEBUG");/*DEBUG*/
			
			while ((inputLine = br.readLine()) != null) {
				if(inputLine.charAt(inputLine.length() - 1) == ";".charAt(0)){
					lineNr++;
					line = inputLine;
					
					System.out.println(line + " N " + lineNr);/*DEBUG*/
					
					sink = new SQLISink(line);
					if(!sink.isSink())
						sink = null;
					else sinks.add(sink);
					/*
					ep = new SQLIEntryPoint(line);
					
					if(!ep.isEntryPoint())
						ep = null;
					else eps.add(ep);*/
				}else{
					line += inputLine;
					line = line.replace("\n", "").replace("\r", "");
				}
				
			}
			
			System.out.println("*/ DEBUG");/*DEBUG*/
			/*
			for(EntryPoint e : eps){
				System.out.println(e.toString());
			}*/
			
			for(Sink e : sinks){
				System.out.println(e.toString());
			}
			br.close();
		}catch(IOException e) {
			System.err.println("An argument is needed!\nExample: java App sqli_01.txt");
		}

	}

}
