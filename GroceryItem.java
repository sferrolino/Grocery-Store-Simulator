/**
 * This class contains Get and Set methods for GroceryItem objects, and two subclasses of GroceryItem (Meat and Dairy).
 * @author Sarah Ferrolino (500768674)
 */
public class GroceryItem
{
	private String label;
	private double price;
	private int foodCode;

	/**
	 * Constructor that assigns parameters to private class variables.
	 * @param label
	 * @param price
	 * @param foodCode
	 */
	GroceryItem(String label, double price, int foodCode)
	{
		this.label = label;
		this.foodCode = foodCode;
		this.price = price;
	}

	/**
	 * GET methods that get and return given class variables.
	 */
	public String getLabel()
	{
		return this.label;
	}
	public double getPrice()
	{
		return this.price;
	}
	public int getFoodCode()
	{
		return this.foodCode;
	}
	
	/**
	 * SET methods that set given class variables.
	 */
	public void setLabel(String foodLabel)
	{
		this.label = foodLabel;
	}
	public void setPrice(double amount)
	{
		this.price = amount;
	}
	public void setFoodCode(int code)
	{
		this.foodCode = code;
	}
}

/**
 * This class is subclass of GroceryItem for Dairy Items.
 */
class Dairy extends GroceryItem
{
	private double volume;

	Dairy(String label, double price, int foodCode, double volume)
	{
		super(label, price, foodCode);
		this.volume = volume;
	}
	public double getPrice()
	{
		return super.getPrice() * this.volume;
	}
	public void setVolume(int volume)
	{
		this.volume = volume;
	}
	public double getVolume()
	{
		return this.volume;
	}
}

/**
 * This class is subclass of GroceryItem for Meat Items.
 */
class Meat extends GroceryItem
{
	private double weight;
	
	Meat(String label, double price, int foodCode, double weight)
	{
		super(label, price, foodCode);
		this.weight = weight;
	}
	public double getPrice()
	{
		return super.getPrice() * this.weight;
	}
	public void setWeight(double weight)
	{
		this.weight = weight;
	}
	public double getWeight()
	{
		return this.weight;
	}
}
