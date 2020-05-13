import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class CharacterList {
	CharacterListNode head;
	int totalNumCharacters;
	LinkedList<String> uniqueCharacters; 
	
	public CharacterList(){
		head = null;
		totalNumCharacters = 0;
		uniqueCharacters = new LinkedList<String>();
	}
	
	void addCharacter(char key, int value) {
		CharacterListNode newNode = new CharacterListNode(key, value);
		totalNumCharacters += value;
		if(head == null) {
			head = newNode;
		}
		else if(newNode.numOfTimes > head.numOfTimes) {
			newNode.next = head;
			head = newNode;
		}
		else {
			CharacterListNode tempNode = head;
			while(tempNode.next != null) {
				if(newNode.numOfTimes > tempNode.next.numOfTimes) {
					newNode.next = tempNode.next;
					tempNode.next = newNode;
					return;
				}
				tempNode = tempNode.next;					
			}
			tempNode.next = newNode;
		}
	}
		
	void processDocument(Scanner inFile, FileWriter outFile) {
		HashMap<Character, Integer> charInText = new HashMap<>();
		String outputString = "";

		while(inFile.hasNextLine()) {
			String tempString = inFile.nextLine();
			outputString = outputString + tempString + "\n";
			for(int i=0; i<tempString.length(); i++) {
				if(charInText.containsKey(tempString.charAt(i))){
					charInText.put(tempString.charAt(i), charInText.get(tempString.charAt(i))+1);
				}
				else {
					charInText.put(tempString.charAt(i), 1);
				}
			}
		}
		charInText.forEach((k, v) -> addCharacter(k, v));
		
		if(head!=null) {
			CharacterListNode tempNode = head;
			while((tempNode!=null)&&(totalNumCharacters!=50)) {
				if((totalNumCharacters - tempNode.numOfTimes)>=50) {
					outputString = outputString.replace(tempNode.character, "");
					totalNumCharacters -= tempNode.numOfTimes;
					uniqueCharacters.add(tempNode.character);
				}
				tempNode = tempNode.next;
			}
		}
		try {
			outFile.write("The unique set of characters created is: " + uniqueCharacters + "\n");
			outFile.write("The initial text without the characters from the unique set has length of " + totalNumCharacters + "\n");
			outFile.write("and looks like the following:" + "\n");
			outFile.write(outputString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("The unique set of characters created is: " + uniqueCharacters);
		System.out.println("The initial text without the characters from the unique set has length of " + totalNumCharacters);
		System.out.println("and looks like the following:");
		System.out.println(outputString);
	}
	
}