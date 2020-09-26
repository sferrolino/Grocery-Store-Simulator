import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.*;
import java.io.*;
import java.lang.reflect.Method;
/**
 * This class creates 4 main panels using GUI interfaces for the user to interact with.
 * @author Sarah Ferrolino (500768674)
 */
public class GroceryStoreFrame extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private static final int FRAME_WIDTH = 900;
	private static final int FRAME_HEIGHT = 700;
	public JTextArea area, area1, area2;
	GroceryCart myCart = new GroceryCart();
	CashRegister myRegister = new CashRegister();
	NutritionScanner myScanner = new NutritionScanner();
	ConveyerBelt myBelt = new ConveyerBelt();
	

	public static void main(String[] args) throws FileNotFoundException
	{
		JFrame frame = new GroceryStoreFrame();    
	    frame.setTitle("Grocery Store Simulator");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true); 
	    frame.pack();
	}

	/**
	 * Constructor that calls methods to create GUI interface.
	 * @throws FileNotFoundException 
	 */
	GroceryStoreFrame() throws FileNotFoundException
	{
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		createGroceryCartPanel();
		createScanItemsPanel();
		createCashRegisterPanel();
		createNutritionScannerPanel();
	}

	/**
	 * ActionListener button for: "REFILL" button
	 */
	class ClickListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			myCart.empty();
			try {
				myCart.fill();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("FileNotFoundException");
			}
			myCart.display(area);
		}
	}

	/**
	 * ActionListener button for: "SCAN ITEM" button
	 */
	class ClickListener1 implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{		
			if(myCart.list.size() != 0)
			{
				try 
				{
					myRegister.scanItem(myBelt.removeItem());
				}
				catch(Exception a) { }
				
				
				myRegister.clear(area1);
				myRegister.displayAll(area1);
				area1.append("----------------\n\n");
				area1.append("Total:     " + myRegister.getTotal() + "\n\n");
				DateFormat df = new SimpleDateFormat("EEE MMM HH:mm:ss yyyy");
	       		Date dateobj = new Date();
	       		area1.append(df.format(dateobj));
			}
			else
			{
				area.setText("");
			}
		}
	}

	/**
	 * ActionListener button for: "SCAN FOOD ITEM" button (Nutrition Panel)
	 */
	class ClickListener2 implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			GroceryItem nextItem = myCart.getNextGroceryItem();	
			if (nextItem == null)
				{ myScanner.displayAll(area2); }
			else
				{
					myScanner.scanFoodCode(nextItem.getFoodCode());
					myScanner.displayAll(area2); 	
				}
		}
	}
	
	/*
	 * PICKUP BUTTON:
	 * take out 
	 */
	class ClickListener3 implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{		
			if(myCart.list.size() != 0)
			{
				GroceryItem groceryItemRemoved = myCart.removeTopItem(); 
				myCart.clear(area);
				myCart.display(area);
				myBelt.setPickedUpItem(groceryItemRemoved);
			}
			else
			{
				area.setText("");
			}
		}
	}
	
	/*
	 * ADD ITEM
	 */
	class ClickListener4 implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{	
			myBelt.addItem();
			try 
			{
				myRegister.scanItem(myBelt.getPickedUpItem());
				myBelt.addItem();
			}
			catch(NullPointerException a) { }
		}
	}
	
	/**
	 * Creates Grocery Cart Panel with scrollable textArea and refill button.
	 * Refills cart when button "REFILL" is pressed.
	 */
	public void createGroceryCartPanel() 
	{
		JPanel cartPanel = new JPanel();
		cartPanel.setBackground(Color.LIGHT_GRAY);
		cartPanel.setPreferredSize(new Dimension(290, 400));
		cartPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,0, Color.red));
		JLabel vroom = new JLabel("Grocery Cart");
		vroom.setFont(new Font("Arial", Font.BOLD, 20));
		JButton fill = new JButton("REFILL");
		fill.setFont(new Font("Arial", Font.BOLD, 14));

		ActionListener refill = new ClickListener();
		fill.addActionListener(refill);

		cartPanel.add(vroom);
		cartPanel.add(fill);
		area = new JTextArea(21,22);
		JScrollPane areaScroll = new JScrollPane(area);
		areaScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		areaScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		area.setEditable(false);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		cartPanel.add(areaScroll);
		cartPanel.setVisible(true);
		try {
			myCart.fill();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		myCart.display(area);	
		getContentPane().add(cartPanel, BorderLayout.WEST);
	}

	/**
	 * Creates Scan Item panel with Scan Item button.
	 * When button is pressed, item is removed from Cart and added to Cash Register.
	 */
	public void createScanItemsPanel()
	{
		JPanel scanPanel = new JPanel();
		scanPanel.setBackground(Color.LIGHT_GRAY);
		scanPanel.setPreferredSize(new Dimension(325, 400));
		scanPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.red));
	    JButton button1 = new JButton("PICKUP");
	    JButton button = new JButton("SCAN");
	    JButton button2 = new JButton("ADD");
	    button.setFont(new Font("Arial", Font.BOLD, 14));
	    button1.setFont(new Font("Arial", Font.BOLD, 14));
	    button2.setFont(new Font("Arial", Font.BOLD, 14));
	    scanPanel.add(button1);
	    scanPanel.add(button);
	    scanPanel.add(button2);
	    
	    scanPanel.add(myBelt); //create belt from ConveyerBelt class (only change so far)
	    ActionListener pickUp = new ClickListener3();
	    button1.addActionListener(pickUp);
	    ActionListener addItem = new ClickListener4();
	    button2.addActionListener(addItem);
	    
	    ActionListener scan = new ClickListener1();
	    button.addActionListener(scan);

	    getContentPane().add(scanPanel, BorderLayout.CENTER);
	}

	/**
	 * Creates Cash Register panel with scrollable textArea with Checkout button.
	 * When button is pressed, textArea is cleared.
	 */
	public void createCashRegisterPanel()
	{
		JPanel register = new JPanel();
		add(register, BorderLayout.EAST);
		register.setBackground(Color.LIGHT_GRAY);
		register.setPreferredSize(new Dimension(290, 400));
		register.setBorder(BorderFactory.createMatteBorder(1,0,1,1, Color.red));
		JLabel vroom = new JLabel("Cash Register");
		JButton out = new JButton("CHECKOUT");
		vroom.setFont(new Font("Arial", Font.BOLD, 20));
		out.setFont(new Font("Arial", Font.BOLD, 14));
		register.add(vroom);
		register.add(out);	
		area1 = new JTextArea(21, 22);
		JScrollPane areaScroll1 = new JScrollPane (area1, 
   		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		area1.setEditable(false);
		area1.setLineWrap(true);
		area1.setWrapStyleWord(true);

		//CHECKOUT button
		out.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {			
				myRegister.clearList();
				myRegister.clear(area1);
			} 
		});
		
		register.add(areaScroll1);
		register.setVisible(true);	
		getContentPane().add(register, BorderLayout.EAST);

	}

	/**
	 * Creates Nutrition Scanner panel with scrollable textArea with Checkout, Carbs, Cals, Sugar, Fat button.
	 * When either of the 4 buttons are pressed, it is ordered by button name accordingly from greatest to least.
	 * When "Checkout" button is pressed, clears textArea. 
	 */
	public void createNutritionScannerPanel()
	{
		JPanel p1 = new JPanel();
		add(p1, BorderLayout.SOUTH);
		p1.setPreferredSize(new Dimension(700, 300));
		p1.setBackground(Color.LIGHT_GRAY);	
		area2 = new JTextArea(12, 73);
		JLabel eh = new JLabel("CART NUTRITION INFORMATION");
		JButton ok1 = new JButton("SCAN FOOD ITEM");
		JButton ok2 = new JButton("CLEAR");
		JButton button1 = new JButton("Cals");
		JButton button2 = new JButton("Carbs");
		JButton button3 = new JButton("Fat");
		JButton button4 = new JButton("Sugar");
		eh.setFont(new Font("Arial", Font.BOLD, 20));
		ok1.setFont(new Font("Arial", Font.BOLD, 14));
		ok2.setFont(new Font("Arial", Font.BOLD, 14));
		button1.setFont(new Font("Arial", Font.BOLD, 14));
		button2.setFont(new Font("Arial", Font.BOLD, 14));
		button3.setFont(new Font("Arial", Font.BOLD, 14));
		button4.setFont(new Font("Arial", Font.BOLD, 14));
		ActionListener nutrition = new ClickListener2();
	    ok1.addActionListener(nutrition);

	    //CLEAR BUTTON
	    ok2.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				if(myCart.list.size() != 0 && myScanner.items.size() != 0)
	    			{
					myScanner.clear(area2);
					myScanner.clearList();
					myCart.startNextGroceryItem();
	    			}
			    else
			    {
			    		myScanner.clear(area2);
			    }
			} 
		});
	    
	    //CALORIES BUTTON
	    button1.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				myScanner.clear(area2);	
				ArrayList<FoodType> cool = new ArrayList<FoodType>(myScanner.getList());
				Collections.sort(cool);
				myScanner.displaySorted(area2, cool);
				cool.removeAll(cool);
			}
			
	    });

		//CARBS BUTTON		
		button2.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				myScanner.clear(area2);
				ArrayList<FoodType> test = new ArrayList<FoodType>(myScanner.getList());
				Collections.sort(test, new Comparator<FoodType>() {
					@Override
					public int compare(FoodType one, FoodType two) 
					{
						int carbs1 = one.getCarbs()*one.getMeasure();
						int carbs2  = two.getCarbs()*two.getMeasure();
						if(carbs2 > carbs1)
						{ return 1; }
						else if(carbs2 < carbs1) { return -1; }
						return 0; 
					}
        		});
				myScanner.displaySorted(area2, test);
				test.removeAll(test);
			}	 
		});
		
		//FAT BUTTON
		button3.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				myScanner.clear(area2);	
				ArrayList<FoodType> ok = new ArrayList<FoodType>(myScanner.getList());
				Collections.sort(ok, new Comparator<FoodType>() {
					@Override
					public int compare(FoodType one, FoodType two) 
					{
						int fat1 = one.getFat()*one.getMeasure();
						int fat2 = two.getFat()*two.getMeasure();
						if(fat2 > fat1)
						{ return 1; }
						else if(fat2 < fat1)
						{ return -1; }
						return 0;
					}
        		});
				myScanner.displaySorted(area2, ok);	
				ok.removeAll(ok);	
			} 
		});

		//SUGAR BUTTON
		button4.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				myScanner.clear(area2);	
				ArrayList<FoodType> ok = new ArrayList<FoodType>(myScanner.getList());	
				Collections.sort(ok, new Comparator<FoodType>() {
					@Override
					public int compare(FoodType one, FoodType two) 
					{
						int sugar1 = one.getSugar()*one.getMeasure();
						int sugar2 = two.getSugar()*two.getMeasure();
						if(sugar2 > sugar1)
						{ return 1; }
						else if(sugar2 < sugar1)
						{ return -1; }
						return 0;
					}
        		});
				myScanner.displaySorted(area2, ok);	
				ok.removeAll(ok);	
			} 
		});

		JPanel top = new JPanel();
		top.setPreferredSize(new Dimension(900, 250));
		top.setBackground(Color.LIGHT_GRAY);	
		JScrollPane areaScroll = new JScrollPane (area2, 
   		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		area2.setEditable(false);
		area2.setLineWrap(true);
		area2.setWrapStyleWord(true);
		top.add(eh);
		top.add(ok1);
		top.add(ok2);
		top.add(areaScroll);
		top.setVisible(true);
		p1.add(button1);
	    p1.add(button2);
	    p1.add(button3);
	    p1.add(button4); 
	    p1.add(top, 0);
    	
	}
     
}

