public class CharacterListNode {
	String character;
	int numOfTimes;
	CharacterListNode next;
		
	public CharacterListNode(char newChar, int value){
		character = "" + newChar;
		numOfTimes = value;
		next = null;
	}
}