
/**
 * ReliefGood simulates one type of relief good. 
 * An instance of ReliefGood will have its own name, unit of measurement, predetermined 
 * release rate and total number of units available in the supply. 
 * It allows increasing supply by any indicated number. 
 * It allows decreasing supply by the predetermined release rate only.
 * 
 * @author Carlo D. Mendoza
 * @version 2015.10.23
 */

public class ReliefGood
{   //instance variables
    private String name;
    private int releaseRate;
    private String unit;
    private int supply;
  
    /**
    * An instance of ReliefGood must have a name, a unit of measurement, and a release rate. 
    * The total available units of a new ReliefGood is 0. 
    * 
    * @param    n   the name of the ReliefGood
    * @param    r   the predetermined release rate of the ReliefGood
    * @param    u   the unit of measurement of the ReliefGood
    */
    public ReliefGood (String n, int r, String u)
    { 
        name = n;
        releaseRate = r;
        unit = u;
        supply = 0;
    }
    /**
    * addGoods increases the total available units (supply) of the ReliefGood
    * 
    * @param    num    the number of units to be added 
    */
    public void addGoods(int num)
    {
        supply = supply + num;
    }
    /**
    * releaseGoods decreases the total available units by the corresponding release rate
    */
    public void releaseGoods()
    {
        supply = supply - releaseRate;
    }
    /**
    * getName returns the name of the ReliefGood
    * 
    * @return   name of the ReliefGood
    */
    public String getName()
    {
        return name;
    }
    /**
    * getReleaseRate returns the predetermined number of units released at one time
    * 
    * @return   release rate
    */
    public int getReleaseRate()
    {
        return releaseRate;
    }
    /**
    * getUnit returns the unit of measurement of the ReliefGood
    * 
    * @return   unit of measurement
    */
    public String getUnit()
    {
        return unit;
    }
     /**
    * getUnitsLeft returns the total available units of the ReliefGood
    * 
    * @return   number of available units
    */
    public int getUnitsLeft()
    {
        return supply;
    }
}