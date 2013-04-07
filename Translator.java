/*
 * This class is the main point of entry for the program and runs the user interface which provides 
 * the user the ability to manulipate the program.
 */
public class Translator 
{
	public static void main(String[] args)
	{
		MorseCodeGUI gui = new MorseCodeGUI("Morse Code Translator");
		gui.setVisible(true);	
	}
}
