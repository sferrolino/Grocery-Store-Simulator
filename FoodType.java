/**
 * This class has Get and Set methods for FoodType objects and a method that increases measure for item.
 * @author Sarah Ferrolino (500768674)
 */
public class FoodType implements Comparable<FoodType>
{
	private String label;
	private int foodCode;
	private int measure = 1;
	private int calories;
	private int sugar;
	private int fat;
	private int carbs;
	
	/**
	 * Constructor that assigns parameters to private class variables.
	 * @param label
	 * @param foodCode
	 * @param measure
	 * @param calories
	 * @param sugar
	 * @param fat
	 * @param carbs
	 */
	FoodType(String label, int foodCode, int measure, int calories, int sugar, int fat, int carbs)
	{
		this.label = label;
		this.foodCode = foodCode;
		this.measure = measure;
		this.calories = calories;
		this.sugar = sugar;
		this.fat = fat;
		this.carbs = carbs;
	}
	
	/**
	 * Comparator to compare two FoodType objects by their calories.
	 */
	public int compareTo(FoodType other)
	{
		if(this.calories*this.getMeasure() < other.calories*other.getMeasure()) { return 1; }
		if(this.calories*this.getMeasure() > other.calories*other.getMeasure()) { return -1; }
		return 0;
	}

	/**
	 * GET methods that get and return given class variables.
	 */
	public String getLabel()
	{
		return this.label;
	}
	public int getFoodCode()
	{
		return this.foodCode;
	}
	public int getCalories()
	{
		return this.calories;
	}
	public int getSugar()
	{
		return this.sugar;
	}
	public int getFat()
	{
		return this.fat;
	}
	public int getCarbs()
	{
		return this.carbs;
	}
	public int getMeasure()
	{
		return this.measure;
	}

	/**
	 * Increments measure variable.
	 */
	public void increaseMeasure()
	{
		this.measure++;
	}


}