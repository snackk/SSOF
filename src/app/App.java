package app;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import knownexploit.KnownExploit;
import knownexploit.SQLI;
import knownexploit.XSS;

public class App {	
	public static String inputLine = "";
	public static ArrayList<String> fileLines = new ArrayList<String>();;
	
	private static ArrayList<KnownExploit> _KnownExploits = new ArrayList<KnownExploit>();	
	
	public static void main(String[] args) {
		
		if(args.length == 0){
			System.err.println("An argument is needed!\nExample: java App sqli_01.txt sqli_02.txt");
			return;
		}
		
		for(int i=0; i<args.length; i++){
			System.out.println("_________________________\n");
			System.err.println("Running test '" + args[i] + "'");
			try {
				BufferedReader br = new BufferedReader(new FileReader(args[i]));
				
				while ((inputLine = br.readLine()) != null) {
					fileLines.add(inputLine);
				}
				br.close();
				
				handleFileText();
				
				_KnownExploits.add(new SQLI(fileLines));
				_KnownExploits.add(new XSS(fileLines));
				
				Boolean isVunerable = false;
				
				for(KnownExploit ke : _KnownExploits){
					if(ke.testVunerability()){
						System.out.println(ke.getVunerabilityIntel());
						isVunerable = true;
						break;
					}
				}
				
				_KnownExploits.clear();
				fileLines.clear();				
				
				if(!isVunerable)
					System.out.println("Program slice doesn't contain any type of known vunerability.");
	
			}catch(IOException e) {
				System.err.println("Couldn't read the file. Does it exist in the filesystem?");
			}
		}
	}
	
	
	public static ArrayList<KnownExploit> getKnownExploits(){
		return App._KnownExploits;
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
