/*
 * Names: Visal Chea 100392137, Spencer Johnstone 100394114, Adam Waito 100401057
 * Description:
 * 	This class extends the Key class and implements Comparable based on the character provided.
 */

public class Letter extends Key implements Comparable<Key> 
{

	public Letter(char c, String s) 
	{
		super(c, s);
	}

	// Compares this and the provided key object based on the character contained
	public int compareTo(Key o) 
	{
		Letter let = (Letter)o;
		return this.getLetter().compareTo(let.getLetter());
	}
	
	public String toString()
	{
		return this.getLetter() + "";
	}

}
