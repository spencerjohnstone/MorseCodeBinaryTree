/*
 * Names: Visal Chea, Spencer Johnstone, Adam Waito
 * Description:
 * 
 */

public class BinaryTree<T extends Comparable>
{
	int size = 0;
	Node start;
	
	public T find(T t)
	{
		Node pos = start;
		
		while ( pos != null )
		{
			if ( t.compareTo(pos.value) == 0 )
			{
				return pos.value;
			}
			else if ( t.compareTo(pos.value) > 0 )
			{
				pos = pos.left;
			}
			else
			{
				pos = pos.right;
			}
		}
		
		return null;
	}
	
	public void balance()
	{
		Node root = new Node();
		root.right = start;
		
		treeToVine(root);
		vineToTree(root, size);
		
		start = root.right;
	}
	
	private void treeToVine( Node r )
	{
		Node vineTail = r;
		Node remainder = vineTail.right;
		
		while ( remainder != null )
		{
			if ( remainder.left == null )
			{
				vineTail = remainder;
				remainder = remainder.right;
			}
			else
			{
				Node temp = remainder.left;
				remainder.left = temp.right;
				temp.right=remainder;
				remainder=temp;
				vineTail.right=temp;
			}
		}
	}
	
	private void vineToTree( Node r, int s )
	{
		int leafCount = (int)(size + 1 - Math.pow(2, Math.log10(size+1)/Math.log10(2)));
		compression(r, leafCount);		
		s -= leafCount;
		
		while ( s > 1 )
		{
			compression(r, s/2);
			s /= 2;
		}		
	}
	
	private void compression( Node r, int s )
	{
		Node scanner = r;
		Node child;
		
		for ( int i = 1; i <= s; i++ )
		{
			child = scanner.right;
			scanner.right = child.right;
			scanner = scanner.right;
			child.right = scanner.left;
			scanner.left = child;
		}
	}
	
	public void add(T c)
	{
		size++;
		
		if (start==null)
		{
			start=new Node();
			start.value=c;	
		}
		else
		{
			Node position = start;
			while (position != null)
			{
				int a = c.compareTo(position.value);
				
				if (a>0)
				{
					if (position.left==null)
					{
						position.left=new Node();
						position.left.value=c;
						position=null;
					}
					else
					{
					position = position.left;
					}
				}
				else if (a<0)
				{
					if (position.right==null)
					{
						position.right=new Node();
						position.right.value=c;
						position=null;
					}
					else
					{
					position = position.right;
					}
				}	
			}
		}			
	}
	
	public void print()
	{
		if (start != null)
		start.printNodes(0);
		System.out.println();
	}
		
	private class Node
	{
		public Node left = null;
		public Node right = null;
		public T value;
		
		/**
        Prints this node and all of its descendants
        in sorted order.
		*/
		public void printNodes(int depth)
		{  
		    if (left != null)
		       left.printNodes(depth +1);
		    System.out.println("Depth: " + depth + " Value: " + value + " ");
		    if (right != null)
		       right.printNodes(depth +1);
		    
		}
	}
}
