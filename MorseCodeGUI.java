import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

/*
This class is the GUI of the Morse Code translator and controls user imput. 
All of the GUI components, listeneners, and event handling are declared in this class and applied to the GUI.  
*/

public class MorseCodeGUI extends JFrame 
{
	// Menu components are declared and labels for each are set. 
	private JMenuBar menu = new JMenuBar(); 
	private JMenu mnuFile = new JMenu("File");
	
	// Menu sub-components are declared and labels are also set for each. 
	private JMenuItem mnuFileSave = new JMenuItem("Save");
	private JMenuItem mnuFileLoad = new JMenuItem("Load"); 
	private JMenuItem mnuFileExit = new JMenuItem("Exit");
	
	// Tabbed pane is initialized and two content panel tabs are declared. 
	private JTabbedPane pnlTabs = new JTabbedPane(); 
	private ContentPanel pnlTextToMorse = new ContentPanel();
	private ContentPanel pnlMorseToText = new ContentPanel();
	
	//A fileChoose is initialized to deal with the Save and Load sub-components of the menu. 
	private JFileChooser fileChooser = new JFileChooser();
	
	//The manager class declaration in order for the listeners and actions to work. 
	private Manager mngr;
	
	// Default constructor
	public MorseCodeGUI()
	{
		init();
	}	
	
	// Overloaded constructor which takes a string for the window title.
	public MorseCodeGUI(String name)
	{
		super(name);
		init();
	}	

	// The initializing method declares default operations, as well as adding all items to the frame itself. 
	// The menu bar and the tabbed panes are also initialized and added here with text labels.
	private void init()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(new Point(100, 100));
		
		// Setup menus
		menu.add(mnuFile);
		mnuFile.add(mnuFileLoad);
		mnuFile.add(mnuFileSave);		
		mnuFile.add(mnuFileExit);
		
		// Add menu to JFrame
		setJMenuBar(menu); 
		
		// Setup tab panels
		this.setContentPane(pnlTabs);
		pnlTabs.addTab("Text to Morse", null, pnlTextToMorse, "Convert Text to Morse");
		pnlTabs.addTab("Morse to Text", null, pnlMorseToText, "Convert Morse to Text");
		
		//The labels seen above the textAreas are set.
		pnlTextToMorse.setEditableLabelText("Enter Text Below:");
		pnlTextToMorse.setUneditableLabelText("Converted Text:");
		pnlMorseToText.setEditableLabelText("Enter Morse Code Below:");
		pnlMorseToText.setUneditableLabelText("Converted Morse Code:");
		
		// Windows size specified
		this.setSize(new Dimension(550, 540));	
		
		// Window resizing is restricted
		this.setResizable(false);
		
		//A try and catch will throw an exception if a file is not found.
		try
		{
			mngr = new Manager();
			
			// The Text To Morse panel will call the getMorse method on the content typed by the user
			// in the first textArea, the same is done by calling the getText, and vice-versa for the second textArea. 
			pnlTextToMorse.setEditableText(mngr.getMorse());
			pnlTextToMorse.setUneditableText(mngr.getText());

			
			pnlTextToMorse.setEditableText(mngr.getText());
			pnlTextToMorse.setUneditableText(mngr.getMorse());
		}
		catch ( FileNotFoundException ex )
		{
			System.out.println("FileNotFoundException: " + ex.getMessage());
		}
		
		//The Save and Load listeners are declared. 		
		mnuFileSave.addActionListener(new SaveListener());
		mnuFileLoad.addActionListener(new LoadListener());
		
		// This method listens to the event performed by the Exit button which just outputs an exit call, halts and closes the program.
		mnuFileExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);				
			}			
		});
		
		//Two listeners one for each conversion button are declared. 
		pnlMorseToText.addConvertListener(new ConvertToTextListener());
		pnlTextToMorse.addConvertListener(new ConvertToMorseListener());		
	}	
	
	// This inner class is an action listeneer which will go through the morse code entered by the user and handle the event 
	// to convert the morse code into text and display it in the second TextArea.
	private class ConvertToTextListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			String morse = pnlMorseToText.getEditableText();
			mngr.setMorse(morse);
			String text = mngr.convertMorseToText();		
			
			pnlMorseToText.setUneditableText(text);
			pnlTextToMorse.setEditableText(text);
			pnlTextToMorse.setUneditableText(morse);
		}		
	}	
	
	// This inner class is an action listeneer which will go through the text entered by the user and handle the event to 
	// convert the text into morse and display it in the second TextArea.
	private class ConvertToMorseListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			String text = pnlTextToMorse.getEditableText();
			mngr.setText(text);
			String morse = mngr.convertTextToMorse();		
			
			pnlTextToMorse.setUneditableText(morse);
			pnlMorseToText.setEditableText(morse);
			pnlMorseToText.setUneditableText(text);
		}		
	}
	
	// LoadListener listens for an event to occur, when load is pressed a dialog box is opened with the option to select 
	// a file to load into the the GUI, and program. The file must be a .txt. 
	private class LoadListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{			
			int returnVal = fileChooser.showOpenDialog(MorseCodeGUI.this);
			
			if ( returnVal == JFileChooser.APPROVE_OPTION )
			{			
				File file = fileChooser.getSelectedFile();			
			
				/*
				 If the Text to Morse tab is selected the manager loads the file and displays it in the textAreas. 
				 If the load is sucessful display a message that says it was loaded sucessfully, if the load was
				 unsuccesful then catch an exception and display that the file could not be read and it was unsuccessful. 
			    */
				if ( pnlMorseToText.isShowing() )
				{
					try
					{
						//The file is loaded then the appropriate message will be displayed, the file's content is placed in the textAreas.
						String morse = mngr.loadMorse(file);
						pnlMorseToText.setEditableText(morse);
						pnlTextToMorse.setUneditableText("");
						pnlMorseToText.setUneditableText("");
						pnlTextToMorse.setEditableText("");
						JOptionPane.showMessageDialog(MorseCodeGUI.this, "File successfully loaded.", "Load Confirmation", JOptionPane.PLAIN_MESSAGE);
					}
					catch ( Exception ex )
					{
						JOptionPane.showMessageDialog(MorseCodeGUI.this, "Unable to read from specified file", "Load Unsuccessful", JOptionPane.PLAIN_MESSAGE);
					}					
				}
				/*
				 If the Morse to text panel is selected then the load will be of a text file that will throw
				 a similar exception to the one above.
				 */
				else if ( pnlTextToMorse.isShowing() )
				{
					try
					{
						//The file is loaded then the appropriate message will be displayed, the file's content is placed in the textAreas.
						String text = mngr.loadText(file);
						pnlTextToMorse.setEditableText(text);
						pnlMorseToText.setUneditableText("");
						pnlTextToMorse.setUneditableText("");
						pnlMorseToText.setEditableText("");
						JOptionPane.showMessageDialog(MorseCodeGUI.this, "File successfully loaded.", "Load Confirmation", JOptionPane.PLAIN_MESSAGE);
					}
					//If the file cannot be read a message is displayed.
					catch ( Exception ex )
					{
						JOptionPane.showMessageDialog(MorseCodeGUI.this, "Unable to read from specified file", "Load Unsuccessful", JOptionPane.PLAIN_MESSAGE);
					}
				}
				// If the pane selected is none of the two above, then a message is displayed that the tab name cannot be recognized. 
				else
				{
					JOptionPane.showMessageDialog(MorseCodeGUI.this, "Unable to determine selected tab pane.", "Focus Error", JOptionPane.PLAIN_MESSAGE);
				}			
			}
		}		
	}
	
	// Another inner class for the event handling of the Save sub-menu component.
	private class SaveListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			// When the save component is pressed, a dialog box will be displayed with the option to save the file in a particular location. 
			int returnVal = fileChooser.showSaveDialog(MorseCodeGUI.this);
			
			if ( returnVal == JFileChooser.APPROVE_OPTION )
			{			
				File file = fileChooser.getSelectedFile();			
			
				// If the pane is the Text to Morse tab then save the Morse code, and display a message if it was saved or if it was not saved. 
				if ( pnlTextToMorse.isShowing() )
				{
					try
					{
						mngr.saveMorse(file);
						JOptionPane.showMessageDialog(MorseCodeGUI.this, "File successfully save.", "Save Confirmation", JOptionPane.PLAIN_MESSAGE);
					}
					catch ( Exception ex )
					{
						JOptionPane.showMessageDialog(MorseCodeGUI.this, "Unable to write to specified file", "Save Unsuccessful", JOptionPane.PLAIN_MESSAGE);
					}
				// If the pane is the Morse to Text tab then save the Text, and display a message if it was saved or if it was not saved. 
				}
				else if ( pnlMorseToText.isShowing() )
				{
					try
					{
						mngr.saveText(file);
						JOptionPane.showMessageDialog(MorseCodeGUI.this, "File successfully save.", "Save Confirmation", JOptionPane.PLAIN_MESSAGE);
					}
					catch ( Exception ex )
					{
						JOptionPane.showMessageDialog(MorseCodeGUI.this, "Unable to write to specified file.", "Save Unsuccessful", JOptionPane.PLAIN_MESSAGE);
					}
				}
				// If the tab pane cannot be recognized and is neither of the ones above, then display a message that the selected pane is undetermined. 
				else
				{
					JOptionPane.showMessageDialog(MorseCodeGUI.this, "Unable to determine selected tab pane.", "Focus Error", JOptionPane.PLAIN_MESSAGE);
				}			
			}
		}		
	}
}
