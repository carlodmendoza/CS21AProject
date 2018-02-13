
/**
 * Class for a ReliefCenter that supports receiving and releasing donations, and inquiry of other details regarding the donations.
 * 
 * @author Carlo D. Mendoza
 * @version 2015.11.28
 */

import java.util.*;
public class ReliefCenter
{
    // instance variables
    private String name;
    private ArrayList<ReliefGood> goods;
    private int packsReleased;

    /**
     * The constructor creates an instance of a ReliefCenter with a name. 
     * A new ReliefCenter also has an ArrayList for ReliefGoods.
     * A new ReliefCenter begins with a total number of packs released of 0.
     * 
     * @param   n   the name of the ReliefCenter
     */
    public ReliefCenter (String n)
    {
        name = n;
        goods = new ArrayList<ReliefGood>();
        packsReleased = 0;
    }
    
    /**
     * findGood checks the names of the ReliefGoods on the list. 
     * It returns the ReliefGood object with the name that matches the supplied name.
     * It returns null when a match is not found. findGood is not case-sensitive.
     * 
     * @param   g   name of the good being searched
     * @return      ReliefGood object
     */
    public ReliefGood findGood (String g)
    {
        for (int i = 0; i < goods.size(); i++)
        {
            if (goods.get(i).getName().equalsIgnoreCase(g)) 
                return goods.get(i);
        }
        return null;
    }

    /**
     * addNewGood enables the ReliefCenter to identify their accepted relief goods.
     * It checks if the good is already in their list of accepted goods, by calling the findGood method.
     * If it is already on the list, the good is not added again,
     * and a message is printed saying the good is already on the list. 
     * If it is not yet on the list, a new instance of ReliefGood is created and added to the list.
     * 
     * @param   n   name of the relief good
     * @param   r   release rate of the good
     * @param   u   unit of measurement of the good
     * @return  a String that report success or failure of the operation
     */
    public String addNewGood (String n, int r, String u)
    {
        if ( findGood(n) == null )
        {
           	goods.add(new ReliefGood(n, r, u));
       	    return "Added new good: " + n;
       	}
        else
            return "Sorry, " + n + " already exists on the list.";
    }

    /**
     * receiveGoods enables the ReliefCenter to receive donations of a specific ReliefGood. 
     * It calls findGood to check if the ReliefCenter accepts the ReliefGood being donated. 
     * If it is accepted, the number of units donated will be added to the supply of that ReliefGood.
     * If it is not accepted, the units will be rejected, and a message is printed to inform 
     * the donor that the donation cannot be accepted.
     * 
     * @param   good    the name of the ReliefGood
     * @param   num     the number of units of the ReliefGood
     * @return  a String that report success or failure of the operation
     */
    public String receiveGoods (String good, int num)
    {
       if ( findGood(good) == null )
            return "Sorry, " + name + " cannot accept " + good + ".";
       else
            findGood(good).addGoods(num); 
        	return "Received " + good + ": " + num;
    }

    /**
     * releasePacks releases one allocation of each ReliefGood that the ReliefCenter carries.
     * It first checks if all of the ReliefGoods in the list have enough units to supply the needed number of packs. 
     * When a lack of one ReliefGood is detected, the method stops checking the rest of the goods in the list. 
     * If there are not enough units of the ReliefGoods to supply the needed packs, 
     * no packs are released, and a message is printed to report the lack of supply. 
     * If there are enough units of all the ReliefGoods for the needed number of packs, an allocation is released for each pack.
     * An allocation refers to the corresponding release rate of each ReliefGood.
     * A message is printed to report the successful release of all the requested packs. 
     * The method also updates how many packs of goods the ReliefCenter has released in total.
     * 
     * @param   num     the number of packs needed
     * @return  a String that report success or failure of the operation
     */
    public String releasePacks (int num)
    {   boolean hasEnoughSupply = true; //checks if all the ReliefGoods in the ArrayList have enough units
        for (int i = 0; i < goods.size(); i++)
        {  
            if (goods.get(i).getUnitsLeft() < goods.get(i).getReleaseRate()*num)
                hasEnoughSupply = false;
        }
        if (hasEnoughSupply == true)
        {   for (int i = 0; i < goods.size(); i++)
            {
                for (int j = 0; j < num; j++)
                {
                    goods.get(i).releaseGoods();
                }
            }
            packsReleased = packsReleased + num;
            return name + " released " + num + " requested packs.";
        }
        else
        {
            return "Sorry, " + name + " does not have enough to supply " + num + " packs.";
        }
    }
            
    /**
     * getPackCount returns the total number of packs the ReliefCenter has released
     * 
     * @return      total number of packs released
     */
    public int getPackCount()
    {
        return packsReleased;
    }

    /**
     * getName returns the name of the ReliefCenter
     * 
     * @return      name of the ReliefCenter
     */
    public String getName()
    {
        return name;
    }

    /**
     * printInventory prints the name of each ReliefGood on the list of the ReliefCenter, followed by each one's corresponding available units and unit of measurement
     * 
     * @return     a String that contains the complete inventory report
     */
    public String printInventory()
    {
        String item = "=================== INVENTORY ===================";
        for (int i = 0; i < goods.size(); i++)
        	{
        		item = item + "\n" + goods.get(i).getName() + ": " + goods.get(i).getUnitsLeft() + " " + goods.get(i).getUnit();
        	}
        return item + "\n" + "=================================================";
    }
}