import java.util.*;
import java.io.File ;
import java.io.FileNotFoundException ;
/**
 * This class contains a FoodType list of unique Grocery Items with nutrition facts and a method that retrieves
 * the FoodType.
 * @author Sarah Ferrolino (500768674)
 */
public class NutritionChart
{
	Map<Integer, FoodType> list = new HashMap<Integer, FoodType>();
	private static int foodCode = 0;
	private int measure = 0;
	int calories = 0;
	int sugar = 0;
	int fat = 0;
	int carbs = 0;
	String label = "";
	FoodType item;
	/**
	 * Constructor that adds unique GroceryItems into an ArrayList with given nutrition facts
	 */
	public NutritionChart() throws FileNotFoundException
	{
		File infile = new File("nutrition.txt");
		Scanner scanner = new Scanner(infile);
		
		while(scanner.hasNextLine()) 
		{
			foodCode = scanner.nextInt();
			Scanner scanner2 = new Scanner(scanner.nextLine());
			while(!scanner2.hasNextInt()) 
			{
				if(!label.equals(""))
					label = label + " " + scanner2.next();
				else
					label += scanner2.next();
			}
				measure = scanner2.nextInt();
				calories = scanner2.nextInt();
				sugar = scanner2.nextInt();
				fat = scanner2.nextInt();
				carbs = scanner2.nextInt();
				
				item = new FoodType(label, foodCode, measure, calories, sugar, fat, carbs);
				
				list.put(foodCode, item);
				label = "";
				scanner2.close();
		}
		scanner.close();
	}

	/**
	 * Takes the foodCode and compares it to the cataloged list of FoodType items until it finds a match.
	 * @param foodCode 
	 * @return itemFound - FoodType object
	 */
	public FoodType getFoodType(int foodCode)
	{
		FoodType itemFound = list.get(foodCode);
		return itemFound;
	}
}
