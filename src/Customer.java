import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Customer extends User implements DetailPrinter, LoginPage , LoginMenu{
	private customerType type = null;
	private ArrayList<Order> orderList = new ArrayList<Order>();
	private ArrayList<Order> cartList = new ArrayList<Order>();
	private ArrayList<Order> orderHistory = new ArrayList<Order>();
	
	public Customer(String name, String address, customerType type) {
		this.name = name;
		this.address = address;
		this.type = type;
		this.accountBalance = 1000;
		
	}
	
	public customerType getType() {
		return type;
	}
	
	public void menu() {
		System.out.println("Welcome "+ this.name);
		System.out.println("Customer Menu");
		System.out.println("\t1) Select Restaurant");
		System.out.println("\t2) Checkout Cart");
		System.out.println("\t3) Reward won");
		System.out.println("\t4) Print the recent orders");
		System.out.println("\t5) Exit");
	}
	
	public int login(ArrayList<Restaurant> ret, ArrayList<Customer> cust) {
//		System.out.println("Welcome "+ this.name);
//		System.out.println("Customer Menu");
//		System.out.println("\t1) Select Restaurant");
//		System.out.println("\t2) Checkout Cart");
//		System.out.println("\t3) Reward won");
//		System.out.println("\t4) Print the recent orders");
//		System.out.println("\t5) Exit");
		
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		
		switch(input) {
		case 1:
			System.out.println("Choose Restaurant");
			
			for(int i=0;i<ret.size();i++) {
				System.out.print((i+1) + ") " + ret.get(i).name + " ");
				if(ret.get(i).type!=null) {
					System.out.print("(" + ret.get(i).type + ")");
				}
				System.out.println();
			}
			
			int input1 = in.nextInt();
			System.out.println("Choose item by code :");
			Map<Integer, Food> map = ret.get(input1-1).foodMap;
			for(Map.Entry<Integer, Food> set : map.entrySet()) {
				set.getValue().displayDetail();
			}
			
			int input2 = in.nextInt();
			System.out.println("Enter item quantity - ");
			int quant = in.nextInt();
			
			addToCart(ret.get(input1-1).foodMap.get(input2), quant, ret.get(input1-1) );
			
			ret.get(input1-1).foodMap.get(input2).reduceQuantity(quant);
			
			System.out.println("Items added to cart");
			break;
			
		case 2:
			System.out.println("Items in cart - ");
			this.displayCartList();
			
			this.checkOutOrder();
			
			float totalCost = 0;												//final cost after adding delivery too
			float totalDeliveryCharge = 0;										//total delivery of each item added
			
			float[] charges = this.totalCostCalculator(totalCost, totalDeliveryCharge);
			
			totalCost = charges[0];
			totalDeliveryCharge = charges[1];
			
			System.out.println("Total order value - INR " + totalCost + " /-");
			
			while(totalCost > this.accountBalance) {
				System.out.println("Order Value exceeded");
				System.out.println("Enter item code to remove ");
				this.displayCartList();
				input = in.nextInt();
				
				float[] ans = this.removeItemFromCart(input);
				totalCost -= ans[0];
				totalDeliveryCharge -= ans[1];
			}
			
			System.out.println("\t1) Proceed to checkout");
			int n = in.nextInt();												//useless input just because they asked
			input = n;															//useless line just to remove warning
			
			
			System.out.println(this.countItemsOrdered() + " items successfully bought for INR "+ totalCost + " /-");
			
			this.orderList.get(0).getRestaurantName().setAccountBalance(totalCost - totalCost/100);			//setting restaurant's account balance
			this.deductCost(totalCost); 																	//setting customer's account balance
			
			this.rewardCalulator(totalCost - totalDeliveryCharge);
			
			super.setCompanyBalance(totalCost/100);							//setting company's balance
			super.setTotalDeliveryCharge(totalDeliveryCharge);				//setting delivery charges to company
			
			
			this.orderList.clear();
			break;
			
		case 3:
			System.out.println("Reward Points - " + this.reward);
			break;
			
		case 4:
			for(int i=this.orderHistory.size()-1;i>-1;i--) {
				System.out.println( " Bought item: " + this.orderHistory.get(i).getItemName()+ ", Quantity: " + 
						            this.orderHistory.get(i).getQuantity() + " for Rs. " + this.orderHistory.get(i).getPrice() + " from Restaurant- '" +
						            this.orderHistory.get(i).getRestaurantName().getName() + "' and Delivery Charges: " + this.orderHistory.get(i).getDeliveryCharge());
				
			}
			break;
			
		case 5:
			
			return -1;
		}
		
		
		return 0;
	}
	
	public void displayDetail() {
		System.out.print(this.name);
		if(this.type!=null) {
			System.out.print(" (" + this.type + ")");
		}
		System.out.println(", " + this.address+", " +  this.accountBalance);
	}
	
	public void displayCartList() {
		for(int i=0;i<this.cartList.size();i++) {
			this.cartList.get(i).displayDetail();;
		}
	}
	
	public void deductCost(float totalCost) {
		if(this.reward > totalCost) {
			this.reward -= totalCost;
		}
		else {
			totalCost -= this.reward;
			this.reward = 0;
			this.accountBalance -= totalCost;
		}
	}

	public int countItemsOrdered() {
		int sum = 0;
		for(int i=0;i<this.orderList.size();i++) {
			sum += this.orderList.get(i).getQuantity();
		}
		return sum;
	}
	
	public float[] totalCostCalculator(float totalCost, float totalDeliveryCharge) {
	
		
		for(int i=0;i<this.orderList.size();i++) {						//setting  totalCost
			
			totalCost += this.orderList.get(i).getTotalCost();
			totalDeliveryCharge += this.orderList.get(i).getDeliveryCharge();
			
			this.orderList.get(i).getRestaurantName().setNoOfOrderTaken(1);					//setting number of order taken by restaurant
			this.noOfOrderTaken += 1;														//setting orders taken by customer
			
		}																			//loop ends
		
		float[] charges = {totalCost, totalDeliveryCharge};
		return charges;
		
	}
	
	
	public void rewardCalulator(float totalCost) {
			
		int points = 0,i=0;
		if(this.orderList.get(i).getRestaurantName().getType()==retType.FastFood) {
			points = (int)(totalCost/150);
			this.reward += 10*points;
		}
		
		else if(this.orderList.get(i).getRestaurantName().getType()==retType.Authentic) {
			points = (int)(totalCost/200);
			this.reward += 25*points;
		}
		else {
			points = (int)(totalCost/100);
			this.reward += 5*points;
		}
		this.orderList.get(i).getRestaurantName().setReward(this.reward);
		
	}
	
	
	public float[] removeItemFromCart(int itemId) {
		float[] ans = new float[2];
		for(int i=0;i<this.cartList.size();i++) {
			if(this.cartList.get(i).getFood().getID()==itemId) {
				float temp1 = this.cartList.get(i).getTotalCost();
				float temp2 = this.cartList.get(i).getDeliveryCharge();
				ans[0] = temp1;
				ans[1] = temp2;
			}
		}
		return ans;
	}
	
	
	public void addToCart(Food food, int quantity, Restaurant restaurantName) {
		
		float cost = food.getPrice() * quantity;
		cost = cost - cost*food.getDiscount()/100;
		cost = cost - cost*restaurantName.getDiscount()/100;
		if(restaurantName.getType()==retType.Authentic && cost>100) {
			cost -= 50;
		}
		
		if(this.type==customerType.Elite && cost > 200) {
			cost -= 50;
		}
		else if(this.type==customerType.Special && cost > 200) {
			cost -= 25;
		}
		
		this.cartList.add(new Order( quantity, cost, restaurantName, food));
	}
	
	
	public void checkOutOrder() {
		int totalDeliveryCost = 0;
		
		for(int i =0; i<this.cartList.size();i++) {						//"delivery charges added here" & "cart to order transfer"
			
			int deliveryCharge = 40;
			if(this.type==customerType.Elite ) {
				deliveryCharge = 0;
			}
			
			else if(this.type==customerType.Special ) {
				deliveryCharge = 20;
			}
			
			this.cartList.get(i).setDeliveryCharge(deliveryCharge);
			
			this.orderList.add( this.cartList.get(i) );
			
			totalDeliveryCost += deliveryCharge;
		}
		
		this.cartList.clear();
		
		System.out.println("Delivery charge - " + totalDeliveryCost + "/-");
		
		updateOrderHistory();
	}
	
	
	public void updateOrderHistory() {
		for(int i=0;i<this.orderList.size();i++) {
			if(this.orderHistory.size() == 10) {
				this.orderHistory.remove(0);
			}
			this.orderHistory.add(this.orderList.get(i));
		}
	}
}

enum customerType{
	Elite, Special
}
