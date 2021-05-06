import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Restaurant extends User implements DetailPrinter, LoginPage, LoginMenu{
	retType type = null;
	static int noOfFoodItems = 0;
	HashMap<Integer, Food> foodMap = new HashMap<Integer, Food>();
	
	public Restaurant(String name, String address, retType t) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.address = address;
		this.type = t;
		this.accountBalance = 0;
		
	}
	
	public retType getType() {
		return this.type;
	}
	
	public void menu() {
		System.out.println("Welcome " + this.name);
		System.out.println("\t1) Add item");
		System.out.println("\t2) Edit item");
		System.out.println("\t3) Print Rewards");
		System.out.println("\t4) Discount on bill value");
		System.out.println("\t5) Exit");
	}
	
	public int login(ArrayList<Restaurant> ret, ArrayList<Customer> cust) {
//		System.out.println("Welcome " + this.name);
//		System.out.println("\t1) Add item");
//		System.out.println("\t2) Edit item");
//		System.out.println("\t3) Print Rewards");
//		System.out.println("\t4) Discount on bill value");
//		System.out.println("\t5) Exit");
		
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		switch(input) {
		case 1:
			System.out.println("Enter food item details");
			System.out.println("Food name");
			String name = in.next();
			System.out.println("Item price");
			float price = in.nextFloat();
			System.out.println("Item Quantity");
			int quantity = in.nextInt();
			System.out.println("Item Category");
			String category = in.next();
			System.out.println("Offer");
			int disc = in.nextInt();
			
			addFood(name, price, quantity, disc, Category.valueOf(Category.class, category));
			break;
			
		case 2:
			
			System.out.println("Choose item by code");
			Map<Integer, Food> map = foodMap;
			for(Map.Entry<Integer, Food> set : map.entrySet()) {
				set.getValue().displayDetail();
			}
			int inputId = in.nextInt();
			editFood(foodMap.get(inputId), in);
			break;
		
		case 3:
			System.out.println("Rewards Points : " + this.reward);
			break;
			
		case 4:
			System.out.print("Enter offer on total bill value : ");
			input = in.nextInt();
			this.availDiscount = input;
			break;
			
		case 5:
			
			return -1;
						
		}
		
		
		return 0;
	}
	
	public void displayDetail() {
		System.out.println(this.name + ", " + this.address + ", " + this.noOfOrderTaken);
		
	}
	
	
	public void addFood(String name, float price, int quantity, int discount, Category catg) {
		Restaurant.noOfFoodItems+=1;
		Food food = new Food(noOfFoodItems, name, price, quantity, discount, catg, this);
		foodMap.put(Restaurant.noOfFoodItems, food);
	}
	
	public void editFood(Food food, Scanner in) {
		System.out.println("Choose attribute to edit:");
		System.out.println("\t1) Name");
		System.out.println("\t2) Price");
		System.out.println("\t3) Quantity");
		System.out.println("\t4) Category");
		System.out.println("\t5) Offer");
		
		int input = in.nextInt();
		
		if (input==1) {
			System.out.print("Enter new name"+" - ");
			String name = in.next();
			food.setName(name);
		}
		else if(input==2) {
			System.out.print("Enter new price"+" - ");
			float price = in.nextFloat();
			food.setPrice(price);
		}
		else if (input==3) {
			System.out.print("Enter new quantity"+" - ");
			int quantity = in.nextInt();
			food.setQuantity(quantity);
		}
		else if(input==4) {
			System.out.print("Enter new category"+" - ");
			String s = in.next();
			Category catg = Category.valueOf(Category.class, s);
			food.setCategory(catg);
		}
		else if(input==5) {
			int offer = in.nextInt();
			food.setDiscount(offer);
		}
	}

}

enum retType{
	FastFood,Authentic
}

