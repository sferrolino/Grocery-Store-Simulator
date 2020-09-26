import javax.swing.*;
import java.text.*;
import java.util.*;

/**
 * This class contains a display method, a scan item method, clear methods and a getTotal method.
 */
public class CashRegister
{
	ArrayList<GroceryItem> list = new ArrayList<GroceryItem>();
	public JTextArea area, area1, area2;
	
	/**
	 * Displays ArrayList in textArea.
	 * @param area - textArea
	 */
	public void displayAll(JTextArea area)
	{
		for(int i = 0; i < list.size(); i++)
		{
			area.append(list.get(i).getLabel() + "    " + list.get(i).getPrice() + "\n\n");
		}
	}

	/**
	 * Calculates Register total.
	 * @return total
	 */
	public String getTotal()
	{
		double total = 0;
		DecimalFormat dc = new DecimalFormat("0.00");
		for(int i = 0; i < list.size(); i++)
		{
			total = total + list.get(i).getPrice();
		}
		return dc.format(total);
	}

	/**
	 * Clears ArrayList list.
	 */
	public void clearList()
	{
		list.removeAll(list);
	}

	/**
	 * Clears textArea.
	 * @param area
	 */
	public void clear(JTextArea area)
	{
		area.setText("");
	}

	/**
	 * Takes GroceryItem and adds to Cash Register list.
	 * @param item - GroceryItem object
	 */
	public void scanItem(GroceryItem item)
	{
		DecimalFormat dc = new DecimalFormat("0.00");
		dc.format(item.getPrice());
		list.add(item);
 	}



}