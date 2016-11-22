package reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

	public static void main(String[] args) {
		SQLManual sqlm = new SQLManual();
		String line;
		try {
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
			while ((line = br.readLine()) != null) {
				String[] parts = line.split("=");
				System.out.println(parts[1]);
				
			}
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

}