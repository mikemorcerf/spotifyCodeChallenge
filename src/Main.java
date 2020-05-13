//Author: Michael de Morcerf e Moura
//13 May 2020
//Spotify challenge

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		if(args.length!=1) {
			System.out.println("Please provide one input argument.");
			return;
		}
	    Scanner inFile = null;
		try {
			inFile = new Scanner(new FileReader(args[0]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		FileWriter outFile = null;
	    try {
			outFile = new FileWriter(new File("output.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    CharacterList myList = new CharacterList();
	    myList.processDocument(inFile, outFile);
	    
	    inFile.close();
	    try {
			outFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
