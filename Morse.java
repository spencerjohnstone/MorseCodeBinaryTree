/*
 * Names: Visal Chea, Spencer Johnstone, Adam Waito
 * Description:
 * 	This class extends the Key class and implements the comparable interface based on the morse code
 *  contained in each object.
 */

public class Morse extends Key implements Comparable<Key> 
{
	public Morse(char c, String s)
	{
		super(c, s);
	}
	
	public int compareTo(Key o) 
	{
		Morse more = (Morse) o;
		
		int i = 0;
		
		String s1 = this.getMorse();
		String s2 = more.getMorse();
		
		int l1 = s1.length();
		int l2 = s2.length();
		
		while ( i < l1 && i < l2)
		{
			if ( s1.charAt(i) != s2.charAt(i) )
			{
				if ( s1.charAt(i) == '-')
				{
					return -1;
				}
				else
				{
					return 1;
				}
			}
			
			i++;
		}
		
		if ( l1 == l2 )
		{
			return 0;
		}
		else
		{
			if ( l1 > l2 )
			{ 
				if ( s1.charAt(i) == '-')
				{
					return -1;
				}
				else
				{
					return 1;
				}
			}
			else
			{
				if ( s2.charAt(i) == '+')
				{
					return -1;
				}
				else
				{
					return 1;
				}
			}
		}		
	}
	
	public String toString()
	{
		return this.getMorse();
	}
}
