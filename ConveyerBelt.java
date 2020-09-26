import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Method;

import javax.swing.*;
import java.text.*;
import java.util.*;
public class ConveyerBelt extends JComponent
{	
	/**
	 * create belt object to be displayed in scanItemPanel
	 */
	private Rectangle box;
	private int width = 320;
	private int height = 200; //these r weird (always make this one bigger than startY)
	private int startX = 15;
	private int startY = 185;
	private LinkGroceryItem first;
	private LinkGroceryItem last;
	private LinkGroceryItem test;
	//instance variables
	LinkGroceryItem pickedUpItem = null;
	//setLocation of each item in belt
	private int x1 = 15;
	private int x2 = 75;
	private int x3 = 135;
	private int x4 = 195;
	private int x5 = 255;
	private int y = 133;
	
	/**
	 * Constructor method: creates Rectangle object
	 */
	public ConveyerBelt() 
	{
		box = new Rectangle(startX,startY,width,height);
	}
	
	/**
	 * This method draws pickedUpItem, draws the linked list of LinkGroceryItems/
	 * @param Graphics g
	 */
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.BLACK);
		g.fillRect(startX, startY, width, height);
		if(pickedUpItem != null) //for PICKUP button
			pickedUpItem.draw(g2);
		if(first != null) //for ADD button
		{
			first.setLocation(x1, y);
			first.draw(g2);
		}
		
	}
	
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(box.getSize());
	}
	
	/**
	 * This method goes through linked list of LinkGroceryItems and counts total
	 * @return number of items (int)
	 */
	private int numItems()
	{
		int count = 0;
		LinkGroceryItem position = first;
		LinkGroceryItem previous = last;
		if (first == null) { throw new NoSuchElementException(); }
		  while (position.next != null)
		  {
			   previous = position;
			   position = position.next;
			   count++;
		   }	
		return count;
	}
	
	/**
	 * This method adds pickedUpItem object to front of linked list
	 */
	public void addItem()
	{
		try {
		int ok = numItems();
		}
		catch(Exception e) {};
		
			// original
			if(pickedUpItem != null)
			{
				test = new LinkGroceryItem(pickedUpItem.gitem, first);
				test.gitem = pickedUpItem.gitem;
				test.next = first;
				first = test;
				pickedUpItem = null;
				if(last == null)
					last = test;
			}	
			repaint();
			// original
	}
	
	/**
	 * This method goes through linked list and finds last one.
	 * @return GroceryItem object that is removed
	 */
	public GroceryItem removeItem()
	{
	   LinkGroceryItem position = first;
	   LinkGroceryItem previous = first;
	   
	   if (first == null) {throw new NoSuchElementException(); }
	   if (first.next == null)
	   {
		   GroceryItem data = first.gitem;
		   first = null;
		   repaint();
		   return data;
	   }
	   while (position.next != null)
	   {
		   previous = position;
		   position = position.next;
		   
	   }
	   previous.next = null;
	   repaint();
	   return position.gitem;
	}
	
	/**
	 * This method creates new LinkGroceryObject passing in the reference to the GroceryItem object and sets
	 * pickedUpItem to point to the new LinkGroceryItem object.
	 * @param item (GroceryItem object)
	 */
	public void setPickedUpItem(GroceryItem item)
	{
		if(pickedUpItem != null) {  }
		else 
		{
			pickedUpItem = new LinkGroceryItem(item, null); 
			repaint();
		}
		
	}
	
	/**
	 * This private class is used to keep GroceryItem objects in a "linked list" 
	 * @author sarahferrolino
	 */
	private class LinkGroceryItem
	{
		GroceryItem gitem;
		Rectangle box;
		LinkGroceryItem next;
		private int width = 56;
		private int height = 45; 
		private int startX = 50; 
		private int startY = 20; 
		public LinkGroceryItem()
		{ }
		
		/**
		 * Constructor method that sets variables.
		 * @param item
		 * @param item2
		 */
		public LinkGroceryItem(GroceryItem item, LinkGroceryItem item2)
		{
			this.gitem = item;
			this.next = item2;
			Rectangle test = new Rectangle(startX,startY,width,height);
			box = test;
		}
		
		/**
		 * Sets location of box rectangle object of LinkGroceryItem.
		 * @param x
		 * @param y
		 */
		public void setLocation(int x, int y)
		{
			this.startX = x;
			this.startY = y;
			box.setLocation(startX,startY);
		}
		
		/**
		 * Checks if box rectangle object intersects with this.box.
		 * @param item
		 * @return boolean
		 */
		public boolean intersects(LinkGroceryItem item)
		{
			if(this.box.intersects(item.box))
				return true;
			else
				return false;
		}
		
		/**
		 * Draws box and label string stored in gitem.
		 * @param g2
		 */
		public void draw(Graphics2D g2)
		{
			String lab = gitem.getLabel();
			g2.draw(box);
			g2.setFont(new Font("Arial", Font.PLAIN, 10)); 
			g2.drawString(lab, startX+2, startY+10);
			if(next != null)
			{
				next.setLocation(15, 133);
			}
		}
		
		
	}

	public GroceryItem getPickedUpItem() 
	{
			return pickedUpItem.gitem;
	}
		
}