package app;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import knownexploit.KnownExploit;
import knownexploit.SQLI;
import knownexploit.XSS;

public class App {	
	public static String inputLine = "";
	public static ArrayList<String> fileLines = new ArrayList<String>();
	
	public static Boolean ansiColors = false;
	public static Boolean isSqli = false;
	public static Boolean isXss = false;
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	private static KnownExploit _KnownExploit = null;
	
	public static void main(String[] args) {
		
		if(args.length == 0){
			System.err.println("An argument is needed!\nExample: java App -sqli tests/sqli_01.txt ");
			return;
		}
		
		for(int i=0; i<args.length; i++){
			if(args[i].toLowerCase().equals("-ansicolor=true")){
				ansiColors = true;
				continue;
			}if(args[i].toLowerCase().equals("-sqli")){
				isSqli = true;
				continue;
			}if(args[i].toLowerCase().equals("-xss")){
				isXss = true;	
				continue;
			}
			System.out.println("_________________________\n");
			System.err.println("Running program slice '" + args[i] + "'\n");
			try {
				File file = new File(args[i]);
				BufferedReader br = new BufferedReader(new FileReader(file));					
				
				while ((inputLine = br.readLine()) != null) {
					fileLines.add(inputLine);
				}
				br.close();
				
				handleFileText();
				if(isSqli)
					_KnownExploit = new SQLI(fileLines);
				if(isXss)
					_KnownExploit = new XSS(fileLines);
				
				Boolean isVunerable = false;
				
				if(_KnownExploit.isVunerable()){
					System.out.println(_KnownExploit.getVunerabilityIntel());
					isVunerable = true;
				}
				
				_KnownExploit = null;
				fileLines.clear();				
				
				if(!isVunerable)
					System.out.println("Program slice doesn't contain any type of known vunerability.");
	
			}catch(IOException e) {
				System.err.println("Couldn't read the file. Does it exist in the filesystem?");
			}
			
		}
	}		
	
	public static Boolean isComplete(String test){
		if((test.charAt(test.length() - 1) == ";".charAt(0)) || (test.charAt(test.length() - 1) == ">".charAt(0)))
			return true;
		else return false;
	}
	
	private static void handleFileText(){
		ArrayList<String> auxFileLines = new ArrayList<String>();
		String aux = "";
		for(String line : fileLines){
			if(isComplete(aux + line)){
				auxFileLines.add(aux + line);
				aux = "";
			}else{
				aux += line;
				aux = aux.replace("\n", "").replace("\r", "");
			}
		}
		fileLines = auxFileLines;
	}
}
