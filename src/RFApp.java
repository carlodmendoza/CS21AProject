import javax.swing.*;

/**
 * RFApp declares a JFrame variable and instantiates a ReliefFrame object. 
 * It accepts one argument from the command line when the application is executed. 
 * 
 * @author Carlo D. Mendoza
 * @version 2015.11.28
 */

public class RFApp
{
	/**
    * Main runs the ReliefCenter GUI (ReliefFrame)
    *
    * @param	args[]	the name of the ReliefCenter
    */
	public static void main (String args[])
	{
		try {
		ReliefFrame f = new ReliefFrame(args[0]);
		f.setTitle(args[0] + " Relief Center");
		f.setSize( 1100, 350 );
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		f.setVisible( true );
		} catch (Exception e){} //won't compile without try-catch
	}
}