import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class project2 {

	public static ArrayStack inventory;
	public static Double highestPrice= 0.0;
	public static ArrayQueue backorder;
	public static int currentInventory=0;
	public static int numWidgets=0;
	public static int totalBackorder=0;
	private static BufferedReader br;


	public static void main(String[] args) {

		if(args == null || args.length == 0) System.out.println("You did not enter the name of the file.");
		else {
			try {
				readFile(args[0]);
			} catch (IOException e) {
				System.out.println("That File Does NOT Exist!!!");
			}
		}
	}


	public static void readFile(String fileName) throws IOException {
		inventory = new ArrayStack();
		backorder = new ArrayQueue();
		br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();


		while (line != null) {
			String[] input = line.split(" ", -1);
			String command = input[0];

			if (command.equals("R")){
				int orders = Integer.parseInt(input[1]);
				double price = Double.parseDouble(input[2]);
				currentInventory = currentInventory + orders;
				System.out.println("** New Shipment of " +orders +" stocks has arrived!!**");
				System.out.println("updating our current inventory to "+currentInventory);
				inventory.push(orders,price); //update stack

				if (price*1.40 >= highestPrice) {
					highestPrice = price * 1.4;
					System.out.println("The new value of the price is " + highestPrice);
				}
				else {
					System.out.println("The price is still " + highestPrice);
				}
				
				System.out.println();
				
				while(!backorder.isEmpty()&&currentInventory!=0){ 
					System.out.println("There is current backorder of " +totalBackorder);
					int topBackorder = backorder.dequeue();
					
					if (topBackorder > currentInventory) {
						try {
							double y = inventory.getPrice();
							int x = inventory.topAndPop();
							System.out.println("**A customer trys to get his backorder " + topBackorder + " widgets!!**");
							System.out.println("Incomplete order, customer pays " +currentInventory+ " @ $"+ (currentInventory * highestPrice));
							System.out.println("Actual Price: " + x + " @ " + y
									+ " = " + ((double) x * y) + "\n");
							System.out.println("Needs more inventory.");
							backorder.enqueue(topBackorder - currentInventory);
							totalBackorder = totalBackorder - currentInventory;
							currentInventory = 0;
						} catch (Exception e) {
							e.printStackTrace();
						}

					}

					else {
						currentInventory = currentInventory - topBackorder;
						try {
							double y = inventory.getPrice();
							int x = inventory.topAndPop();
							System.out.println("**A customer trys to get his backorder " + topBackorder + " widgets!!**");
							System.out.println("Successful order, customer pays " +topBackorder+ " @ $"+ (topBackorder * highestPrice));
							inventory.push(currentInventory, price);
							System.out.println("Actual Price: " + topBackorder + " @ " + y
									+ " = " + ((double) topBackorder * y) + "\n");
							System.out
									.println("We have more inventory than backorders, putting in inventory ");
							inventory.push(x-topBackorder, y);
							totalBackorder = totalBackorder - topBackorder;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					System.out.println();

				}

			}



			if (command.equals("S")){
				String actualPrice = null;
				numWidgets = Integer.parseInt(input[1]);
				double totalcost = numWidgets * highestPrice;
				double totalcost2 = currentInventory * highestPrice;
				System.out.println("**A customer is trying to order " +numWidgets+ " widgets!!** ");
				if(currentInventory>numWidgets) System.out.println("Successful order, customer pays "+numWidgets+ " @ $" +totalcost);
				else System.out.println("Incomplete order, customer pays " +currentInventory+ " @ $"+totalcost2);
				if (numWidgets > currentInventory) currentInventory = 0;
				else currentInventory = currentInventory - numWidgets;

				while (!inventory.isEmpty()){
					
					if (numWidgets < inventory.top()){ //if top stack is enough for order
						try {
							double y = inventory.getPrice();
							int x = inventory.topAndPop();
							if(actualPrice == null) actualPrice = "";
							actualPrice += (numWidgets + " @ " + y + " = " + ((double)numWidgets * y) + "\n");
							//System.out.println("subtracting to the current stack " +x+" - "+numWidgets);
							numWidgets =  x - numWidgets; //get difference, pop topstack the push again the difference.
							inventory.push(numWidgets,y);
							break;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					else {
						try {
							//System.out.println("Number of widgets to be purchased is more than the current stack so poping stack " +inventory.top());
							System.out.println();
							double y = inventory.getPrice();
							int x = inventory.topAndPop();
							if(actualPrice == null) actualPrice = "";
							actualPrice += (x + " @ " + y + " = " + ((double)x * y) + "\n");
							//System.out.print("Number of Widgets " +numWidgets+ " - top of the current stack " +x+ " = " );
							numWidgets = numWidgets-x;
							if (numWidgets == 0) break;
							//System.out.println(numWidgets);
							//System.out.println("Value left to check with the next stack: "+numWidgets);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				if(actualPrice != null) System.out.println("Actual Price:\n" + actualPrice);
				if(inventory.isEmpty()){
					totalBackorder=totalBackorder+numWidgets;
					System.out.println("current total backorders needed is " +totalBackorder);
					backorder.enqueue(numWidgets);
				}System.out.println();
			}line = br.readLine();
		}
	}
}


