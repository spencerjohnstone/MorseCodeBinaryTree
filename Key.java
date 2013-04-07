/*
 * Names: Visal Chea, Spencer Johnstone, Adam Waito
 * Description:
 * 	This class accepts a Character and String pair.
 */

public class Key
{
	private Character letter;
	private String morse;
	
	// Key Constructor
	public Key (char c, String s)
	{
		letter = c;
		morse = s;
	}
	
	/*
	 * Get and Set methods for the letter and morse variables
	 */
	
	public void setLetter(Character text)
	{
		letter = text;
	}
	
	public void setMorse(String text)
	{
		morse = text;
	}
	public String getMorse()
	{
		return morse;
	}
	public Character getLetter()
	{
		return letter;
	}
}
