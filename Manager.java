import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.util.StringTokenizer;
import java.io.PrintWriter;

public class Manager {
	private BinaryTree<Morse> morseTree;
	private BinaryTree<Letter> letterTree;
	private String letterText;
	private String morseText;
	private final File F = new File("D:\\key.txt");
	private final String PUNCTUATIONS="?!\"#$%&\'()*+,-./\\[]:;=";
	
	
	public Manager()throws FileNotFoundException{
		morseTree = new BinaryTree<Morse>();
		letterTree = new BinaryTree<Letter>();
		Character letter = ' ';
		String morse="";
		Morse newKeyMorse=new Morse(letter, morse);
		morseTree.add(newKeyMorse);
		initiate();
	}
	private void initiate()throws FileNotFoundException{
		FileReader file=new FileReader(F);
		Scanner keyFile=new Scanner(file);
	    String line; 
	    while (keyFile.hasNextLine())
	    {
	    	 line=keyFile.nextLine();
	    	 StringTokenizer string = new StringTokenizer(line);
	    	 Character letter= string.nextToken().charAt(0);
	    	 String morse= string.nextToken();
	    	 Morse newKeyMorse=new Morse(letter, morse);
	    	 Letter newKeyLetter= new Letter(letter,morse);
	    	 morseTree.add(newKeyMorse);
	    	 letterTree.add(newKeyLetter);
	     }
	    
	    letterText = "";
	    morseText = "";
	}
	public BinaryTree<Letter> getLetterConversionTree(){
		return letterTree;
	}
	public BinaryTree<Morse> getMorseConversionTree(){
		return morseTree;
	}
	public String getText(){
		return letterText;
	}
	public String getMorse(){
		return morseText;
	}
	public String loadText(File f)throws FileNotFoundException{
		FileReader file=new FileReader(f);
		Scanner textFile=new Scanner(file);
		String loadingFile="";
		while (textFile.hasNextLine()){
			loadingFile=loadingFile.concat(textFile.nextLine() + ' ');
		}
		setMorse("");
		setText(loadingFile);
		
		return letterText;
	}
	public String loadMorse(File f)throws FileNotFoundException{
		FileReader file=new FileReader(f);
		Scanner morseFile=new Scanner(file);
		String loadingFile="";
		while (morseFile.hasNextLine()){
				loadingFile=loadingFile.concat(morseFile.nextLine() + ' ');
		}
		setText("");
		setMorse(loadingFile);
		
		return morseText;
	}
	public void saveText(File f)throws FileNotFoundException{
		PrintWriter save=new PrintWriter(f);
		Scanner stringSave= new Scanner(letterText);
		while(stringSave.hasNextLine()){
			save.println(stringSave.nextLine());
		}
		save.close();
	}
	public void saveMorse(File f)throws FileNotFoundException{
		PrintWriter save=new PrintWriter(f);
		Scanner stringSave= new Scanner(morseText);
		while(stringSave.hasNextLine()){
			save.println(stringSave.nextLine());
		}
		save.close();
	}
	public void setText(String str){
		letterText=punctWipe(str);
	}
	public void setMorse(String str){
		morseText=str;
	}
	
	private String punctWipe(String str){
		String temp="";
		for(int i=0; i<str.length(); i++){
			char t=str.charAt(i);
			if(!isPunct(t)){
				temp=temp.concat(t+"");
			}
		}
		return temp;
	}
	private boolean isPunct(char c){
		return PUNCTUATIONS.contains(c+"");
	}
	
	/**Miguel Can you implement these two methods
	 * 
	 **/
	public String convertTextToMorse()
	{
		String[] words = getText().split("\\s");
		String temp = "";
		
		for ( String w : words )
		{
			for ( int i = 0; i < w.length(); i++ )
			{				
				Letter look = new Letter(Character.toLowerCase(w.charAt(i)),"");
				Letter result = letterTree.find(look);
				String morse;
				
				if(result!=null)
				{
					morse=result.getMorse();
				}
				else
				{
					morse="";
				}
				
				temp=temp.concat(morse+" ");
			}
			
			temp += "    ";
		}
	
		setMorse(temp);
		
		return morseText;
	}
	
	public String convertMorseToText()
	{
		String[] words = getMorse().split("\\s\\s\\s\\s\\s");
		String temp = "";
		
		for ( String w : words )
		{
			String[] characters = w.split("\\s");
			
			for ( String c : characters )
			{
				Morse look = new Morse(' ',c);
				Morse result = morseTree.find(look);
				Character letter;
				
				if(result!=null)
				{
					letter=result.getLetter();
				}
				else
				{
					letter=' ';
				}
				
				temp=temp.concat(Character.toUpperCase(letter)+"");
			}
			
			temp += " ";
		}
		
		setText(temp);
		
		return letterText;
	}
}