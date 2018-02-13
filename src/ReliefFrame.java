import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * ReliefFrame creates a GUI for the ReliefCenter. 
 * It is associated with a single ReliefCenter instance and facilitates transactions with the ReliefCenter by calling methods when events occur.
 * 
 * @author Carlo D. Mendoza
 * @version 2015.11.28
 */

public class ReliefFrame extends JFrame
{
	//instance variables
	private ReliefCenter center;
	private String nameCenter;
	private JPanel mainLayout, inputArea, buttonArea;
	private JScrollPane scrollbar;
	private JLabel welcomeMsg, rgLabel, qLabel, uLabel;
	private JTextField reliefGood, quantity, unit;
	private JButton addGood, receiveGoods, releasePacks, packsReleased, inventoryReport;
	private JTextArea statusReport;
	private PrintWriter log;

	/**
    * The constructor creates an instance of a ReliefCenter with a name.
    * It creates the components of the frame and handles the events. 
    * It also creates the text file log.txt.
    * 
    * @param    n   the name of the ReliefCenter
    */
	public ReliefFrame (String n) throws IOException
	{
		log = new PrintWriter("../log/log.txt");
		nameCenter = n;
		center = new ReliefCenter (nameCenter);
		setLayout( new BorderLayout() );
		welcomeMsg = new JLabel( "Welcome to the " + nameCenter + " Relief Center", SwingConstants.CENTER );
		add(welcomeMsg,"North");
		mainLayout = new JPanel();
		mainLayout.setLayout( new GridLayout(1,3) );
		add(mainLayout,"Center");
		createInputArea();
		createButtonArea();	
		createAddGoodButton();
		createReceiveGoodsButton();
		createReleasePacksButton();
		createPacksReleasedButton();
		createInventoryReportButton();
		createTextArea();
	}

	/**
    * Sets the layout of the panel for the textfields then adds the panel
    * Adds the textfields where the user will input
    */
	private void createInputArea()
	{
		inputArea = new JPanel();
		inputArea.setLayout( new GridLayout (9,1) );
		mainLayout.add(inputArea);
		rgLabel = new JLabel( "Relief Good" );
		inputArea.add(rgLabel);
		reliefGood = new JTextField();
		inputArea.add(reliefGood);
		inputArea.add(new JLabel()); //empty cell
		qLabel = new JLabel( "Quantity" );
		inputArea.add(qLabel);
		quantity = new JTextField();
		inputArea.add(quantity);
		inputArea.add(new JLabel()); //empty cell
		uLabel = new JLabel( "Unit" );
		inputArea.add(uLabel);
		unit = new JTextField();
		inputArea.add(unit);
		inputArea.add(new JLabel()); //empty cell
	}

	/**
    * Sets the layout of the panel for the buttons then adds the panel
    */
	private void createButtonArea()
	{
		buttonArea = new JPanel();
		buttonArea.setLayout( new GridLayout (9,1) );
		mainLayout.add(buttonArea);
	}

	/**
    * Adds action for the "Add Good" button then adds the button
    */
	private void createAddGoodButton()
	{
		addGood = new JButton( "Add Good" );
		/**
    	* Class for a ButtonListener that adds action for the "Add Good" button
    	*/
		class ButtonListener implements ActionListener
		{ 
		 	/**
		 	* Enables the ReliefCenter to identify their accepted relief goods.
		 	* Prints statements in the text area whether the good is added or is already on the list.
        	* When the "Add Good" button is clicked, it creates a new instance of ReliefGood then adds it to the list.
        	* If the good is already on the list, it is not added again.
        	*/
   			public void actionPerformed( ActionEvent ae )
   			{
      			String n = reliefGood.getText();
      			int r = 0;
      			try {
      				r = Integer.parseInt(quantity.getText());
      			} catch (NumberFormatException e) 
      				{
      					log.println("ERROR: Please enter an integer number for the release rate.");
      					log.flush();
      					if (statusReport.getText().equals(""))
      						statusReport.setText("ERROR: Please enter an integer number for the release rate.");
      					else
      						statusReport.setText(statusReport.getText() + "\n" + "ERROR: Please enter an integer number for the release rate.");
      					return;
      				}
      			String u = unit.getText();
      			String oldText = statusReport.getText();
      			String newText = center.addNewGood(n, r, u);
      			String logText = newText;
      			if (oldText.equals(""))
      				newText = newText;
      			else
      				newText = oldText + "\n" + newText;
      			statusReport.setText(newText); 
      			log.println(logText);
      			log.flush();          
   			}
		}
   		addGood.addActionListener(new ButtonListener());
		buttonArea.add(addGood);
		buttonArea.add(new JLabel()); //empty cell
	}

	/**
    * Adds action for the "Receive Goods" button then adds the button
    */
	private void createReceiveGoodsButton()
	{
		receiveGoods = new JButton( "Receive Goods" );
		/**
    	* Class for a ButtonListener that adds action for the "Receive Goods" button
    	*/
		class ButtonListener implements ActionListener
		{ 
			/**
		 	* Enables the ReliefCenter to receive donations of a specific ReliefGood. 
		 	* Prints statements in the text area whether the good is accepted or not.
        	* When the "Receive Goods" button is clicked, the number of units entered in the 'Quantity' text field 
        	* will be added to the supply of the ReliefGood entered in the 'Relief Good" text field.
        	* If the good is already on the list, it is not added again.
        	*/
   			public void actionPerformed( ActionEvent ae )
   			{
      			String good = reliefGood.getText();
      			int num = 0;
      			try {
      				num = Integer.parseInt(quantity.getText());
      			} catch (NumberFormatException e) 
      				{
      					log.println("ERROR: Please enter an integer number for the quantity.");
      					log.flush();
      					if (statusReport.getText().equals(""))
      						statusReport.setText("ERROR: Please enter an integer number for the quantity.");
      					else
      						statusReport.setText(statusReport.getText() + "\n" + "ERROR: Please enter an integer number for the quantity.");
      					return;
      				}
      			String oldText = statusReport.getText();
      			String newText = center.receiveGoods(good, num);
				String logText = newText;
      			if (oldText.equals(""))
      				newText = newText;
      			else
      				newText = oldText + "\n" + newText;
      			statusReport.setText(newText);     
				log.println(logText);
      			log.flush();           
   			}
		}
   		receiveGoods.addActionListener(new ButtonListener());
		buttonArea.add(receiveGoods);
		buttonArea.add(new JLabel()); //empty cell
	}

	/**
    * Adds action for the "Release Packs" button then adds the button
    */
	private void createReleasePacksButton()
	{
		releasePacks = new JButton( "Release Packs" );
		/**
    	* Class for a ButtonListener that adds action for the "Release Packs" button
    	*/
		class ButtonListener implements ActionListener
		{
			/**
		 	* Releases one allocation of each ReliefGood that the ReliefCenter carries.
		 	* Prints statements in the text area whether the ReliefCenter released all the requested packs or did not.
        	* When the "Release Packs" button is clicked, the number of requested packs 
        	* entered in the 'Quantity' text field will be released by the ReliefCenter.
        	* If there is a lack of supply, no packs will be released.
        	*/
   			public void actionPerformed( ActionEvent ae )
   			{
      			int num = 0;
      			try {
      				num = Integer.parseInt(quantity.getText());
      			} catch (NumberFormatException e) 
      				{
      					log.println("ERROR: Please enter an integer number for the quantity.");
      					log.flush();
      					if (statusReport.getText().equals(""))
      						statusReport.setText("ERROR: Please enter an integer number for the quantity.");
      					else
      						statusReport.setText(statusReport.getText() + "\n" + "ERROR: Please enter an integer number for the quantity.");
      					return;
      				}
      			String oldText = statusReport.getText();
      			String newText = center.releasePacks(num);
				String logText = newText;
      			if (oldText.equals(""))
      				newText = newText;
      			else
      				newText = oldText + "\n" + newText;
      			statusReport.setText(newText); 
				log.println(logText);
      			log.flush();               
   			}
		}
   		releasePacks.addActionListener(new ButtonListener());
		buttonArea.add(releasePacks);
		buttonArea.add(new JLabel()); //empty cell

	}

	/**
    * Adds action for the "Packs Released" button then adds the button
    */
	private void createPacksReleasedButton()
	{
		packsReleased = new JButton( "Packs Released" );
		/**
    	* Class for a ButtonListener that adds action for the "PacksReleased" button
    	*/
		class ButtonListener implements ActionListener
		{ 
			/**
        	* When the "Packs Released" button is clicked, the total number of packs
        	* the ReliefCenter has released will be printed in the text area.
        	*/
   			public void actionPerformed( ActionEvent ae )
   			{
      			String oldText = statusReport.getText();
      			String newText = nameCenter + " has released " + center.getPackCount() + " packs.";
				String logText = newText;
      			if (oldText.equals(""))
      				newText = newText;
      			else
      				newText = oldText + "\n" + newText;
      			statusReport.setText(newText);
				log.println(logText);
      			log.flush();               
   			}
		}
   		packsReleased.addActionListener(new ButtonListener());
		buttonArea.add(packsReleased);
		buttonArea.add(new JLabel()); //empty cell

	}

	/**
    * Adds action for the "Inventory Report" button then adds the button
    */
	private void createInventoryReportButton()
	{
		inventoryReport = new JButton( "Inventory Report");
		/**
    	* Class for a ButtonListener that adds action for the "Inventory Report" button
    	*/
		class ButtonListener implements ActionListener
		{ 
			/**
        	* When the "Inventory Report" button is clicked, an inventory of each ReliefGood on the list of the ReliefCenter, 
        	* followed by each one's corresponding available units and unit of measurement will be printed in the text area.
        	*/
   			public void actionPerformed( ActionEvent ae )
   			{
      			String oldText = statusReport.getText();
      			String newText = center.printInventory();
				String logText = newText;
      			if (oldText.equals(""))
      				newText = newText;
      			else
      				newText = oldText + "\n" + newText;
      			statusReport.setText(newText);            
				log.println(logText);
      			log.flush();    
   			}
		}
   		inventoryReport.addActionListener(new ButtonListener());
		buttonArea.add(inventoryReport);
	}

	/**
    * Creates a text area for the status report then adds the text area
    */
	private void createTextArea()
	{
		statusReport = new JTextArea();
		statusReport.setLineWrap(true); 
		statusReport.setEditable(false);
		scrollbar = new JScrollPane (statusReport);
		mainLayout.add(scrollbar);
	}
}