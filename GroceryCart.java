import java.util.*;
import javax.swing.*;
import java.io.File ;
import java.io.FileNotFoundException ;
import java.util.Scanner ;
/*
 * This class has methods that retrieves GroceryItems at specific indexes, add and remove objects from ArrayList, 
 * a fill ArrayList method, a clear method and a display method. 
 * @author Sarah Ferrolino (500768674) 
 */
public class GroceryCart 
{
	Stack<GroceryItem> list = new Stack<GroceryItem>();
	ListIterator<GroceryItem> iter = list.listIterator();
	
	private GroceryItem nextItem;
	public JTextArea area, area1, area2;
	private int index = 0;
	
	/**
	 * Method that adds GroceryItem to ArrayList list.
	 * @param item - GroceryItem object
	 */
	public void addItem(GroceryItem item)
	{
		list.add(item);
	}
	
	/**
	 * Method that fills ArrayList with several GroceryItem objects.
	 */
	public void fill() throws FileNotFoundException
	{
		File infile = new File("groceryItems.txt");
		Scanner scanner = new Scanner(infile);
		String label = "";
		int foodCode = 0;
		double price = 0;
		int weight = 0;
		int volume = 0;
		
		while(scanner.hasNextLine()) 
		{
			String type = "";
			foodCode = scanner.nextInt();
			Scanner scanner2 = new Scanner(scanner.nextLine());
			while(!scanner2.hasNextDouble()) 
			{
					if(!label.equals(""))
							label = label + " " + scanner2.next();
					else
						label += scanner2.next();		
			}
			price = scanner2.nextDouble();
			type = label;
			
			if(type.substring(0,4).equals("Meat")) {
				type = type.substring(5);
				weight = scanner2.nextInt();
				list.push(new Meat(type, price, foodCode,weight));
			}
			else if(type.substring(0,5).equals("Dairy")) {
				volume = scanner2.nextInt();
				type = type.substring(6);
				list.push(new Dairy(type, price, foodCode, volume));
			}
			else
				list.push(new GroceryItem(type, price, foodCode));
			label = "";
			scanner2.close();
		}	
		scanner.close();

		nextItem = list.get(0); //DONT TOUCH THIS. IDK WHY I NEED IT BUT ITS THERE.
	}

	/**
	 * Removes first item of ArrayList and returns a reference to it.
	 * @return itemRemoved - GroceryItem object
	 */
	public GroceryItem removeTopItem()
	{
		
		GroceryItem itemRemoved = list.get(0);
		list.remove(0);
		return itemRemoved;
		
		/* take from bottom (roast beef)
		if(list.size() > 0)
		{
			index--;
			if(index < 0)
				index = 0;
			return list.pop();
		}
		return null;
		*/
	}

	/**
	 * Clears textArea.
	 * @param area - Given TextArea
	 */
	public void clear(JTextArea area)
	{
		area.setText("");
	}

	/**
		Method goes through GroceryItem objects in array list and
		displays them in the given text area
	*/
	public void display(JTextArea area)
	{
		for(int i = 0; i < list.size(); i++)
		{
			area.append(list.get(i).getLabel() + "\n" + "\n");
		}
	}

	/**
	 * Gets next grocery item object.
	 * @return nextItem - GroceryItem object
	 */
	public GroceryItem getNextGroceryItem()
	{
		try {
			while(iter.hasNext())
			{
				nextItem = list.get(index);
				index++;
				return nextItem;
			}
		}
		catch(Exception e) { }
		return null;
	} 
 
	/**
	 * Gets first item in ArrayList.
	 */
	public void startNextGroceryItem()
	{
		nextItem = list.get(0);
	}
	
	/**
	 * Empties ArrayList list.
	 */
	public void empty()
	{
		list.removeAll(list);
	}


}