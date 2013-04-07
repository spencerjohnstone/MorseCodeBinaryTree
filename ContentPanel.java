import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

/*
 The actual content of the GUI, is declared and set in this class
 this includes the sizes, constraints and the type of layout used for the GUI. 
 */

public class ContentPanel extends JPanel {

	// Spring layout is used for the layout manager of the GUI. 
	
	private static SpringLayout layContent = new SpringLayout();
	
	// The Text Areas are declared.
	
	private JTextArea txtEditable = new JTextArea();
	private JTextArea txtUneditable = new JTextArea();
	
	// The labels required for the GUI are declared, one for each box. 
	
	private JLabel lblEditable = new JLabel(); 
	private JLabel lblUneditable = new JLabel();
	
	//The convert button is declared and is given the String parameter as the name of the button displayed in the GUI. 
	
	private JButton btnConvert = new JButton("Convert");
	
	// The Scrollbars are added to each panel, this is to ensure the user can type as much text as desired.
	
	private JScrollPane pnlEditable;
	private JScrollPane pnlUneditable;

	/* 
	The default constructor has two method calls, one declares the layout manager on every object created
	and the second method is the init() method which will also be called on every object. 
	*/
	
	public ContentPanel()
	{		
		super(layContent);
		init();
	}
	
	/*
	 The init method will set the dimensions of many of components alongside label and the type
	 of font being used in the Text Fields. It also will add the scroll bars to the appropriate components. 
	 */
	
	private void init()
	{
		
		// The columns (width) of the first Text Area is set, and the text will automatically wrap to the box, the rows are also initialized.
		
		txtEditable.setColumns(56);
		txtEditable.setLineWrap(true);
		txtEditable.setRows(10);
		
		// The font used in the first Text Area is set to Courier and the font size is also initialized. 
				
		txtEditable.setFont(new Font("Courier New", Font.PLAIN, 15));
		
		// The second text Area's dimensions and criteria are set, and it is made uneditable. 
		
		txtUneditable.setColumns(56);
		txtUneditable.setLineWrap(true);
		txtUneditable.setRows(10);
		txtUneditable.setEditable(false);
		
		// The same font used in the top text area will be used in the lower one, once again intializing the text size. 
		
		txtUneditable.setFont(new Font("Courier New", Font.PLAIN, 15));
		
		// The label for the first text area is added and is constrained using parameters set by the Spring Layout, this will set the position of the Label.  
		
		this.add(lblEditable);
		layContent.putConstraint(SpringLayout.WEST, lblEditable, 15, SpringLayout.WEST, this);
		layContent.putConstraint(SpringLayout.NORTH, lblEditable, 5, SpringLayout.NORTH, this);
		
		// The scroll bar for the first text Area is always going to be a vertical bar, and will always be a preferred size. 
		
		pnlEditable = new JScrollPane(txtEditable);
		pnlEditable.setVerticalScrollBarPolicy(pnlEditable.VERTICAL_SCROLLBAR_ALWAYS);		
		pnlEditable.setPreferredSize(txtEditable.getPreferredScrollableViewportSize());
		
		// The actual text area is added to the GUI and set to conform to the constraints set by the putConstraint method. 
		
		this.add(pnlEditable);
		layContent.putConstraint(SpringLayout.WEST, pnlEditable, 15, SpringLayout.WEST, this);
		layContent.putConstraint(SpringLayout.NORTH, pnlEditable, 5, SpringLayout.SOUTH, lblEditable);
		
		// The label for the second text box is added and is also placed accordingly using the method call. 
		
		this.add(lblUneditable);
		layContent.putConstraint(SpringLayout.WEST, lblUneditable, 15, SpringLayout.WEST, this);
		layContent.putConstraint(SpringLayout.NORTH, lblUneditable, 5, SpringLayout.SOUTH, pnlEditable);
		
		// The scroll bar for the second text area is also set, and will always be vertical. 
		
		pnlUneditable = new JScrollPane(txtUneditable);
		pnlUneditable.setVerticalScrollBarPolicy(pnlEditable.VERTICAL_SCROLLBAR_ALWAYS);		
		pnlUneditable.setPreferredSize(txtUneditable.getPreferredScrollableViewportSize());
		
		// The second text box is added and constrained to certain criteria to fit in place in the GUI. 
		
		this.add(pnlUneditable);
		layContent.putConstraint(SpringLayout.WEST, pnlUneditable, 15, SpringLayout.WEST, this);
		layContent.putConstraint(SpringLayout.NORTH, pnlUneditable, 5, SpringLayout.SOUTH, lblUneditable);
		
		// The most southern component, the button is added and placed in its appropriate position in the layout. 
				
		this.add(btnConvert);
		layContent.putConstraint(SpringLayout.WEST, btnConvert, 15, SpringLayout.WEST, this);
		layContent.putConstraint(SpringLayout.NORTH, btnConvert, 5, SpringLayout.SOUTH, pnlUneditable);
	}
	
	// Methods invoked to set the text of the labels for both the text boxes are set, and will take a String parameter. 
	
	public void setEditableLabelText(String s)
	{
		lblEditable.setText(s);
	}
	
	public void setUneditableLabelText(String s)
	{
		lblUneditable.setText(s);
	}
	
	// Methods invoked to set the text areas will also follow the method calls above, with a String parameter. 
	
	public void setEditableText(String s)
	{
		txtEditable.setText(s);
	}
	
	public void setUneditableText(String s)
	{
		txtUneditable.setText(s);
	}
	
	// An Action Listener method is invoked and is added to the button which converts the text or morse code. 
	
	public void addConvertListener(ActionListener al)
	{
		btnConvert.addActionListener(al);
	}
	
	// The text entered by the user is retrieved by this method call. This method returns the text in the first text box. 
	
	public String getEditableText()
	{
		return txtEditable.getText();
	}
}
