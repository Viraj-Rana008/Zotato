
public class Order implements DetailPrinter{
	private String itemName;
	private int quantity;
	private float price;
	private Restaurant restaurantName;
	private float deliveryCharge;
	private Food food;
	private float totalCost;

	
	public Order( int quantity, float price, Restaurant rest, Food food) {
		this.itemName = food.getName();
		this.price = price;
		this.quantity = quantity;
		this.restaurantName = rest;
		this.food = food;
	}
	
	
	public void displayDetail() {
		System.out.println(this.food.getID() + " " +  this.restaurantName.getName() + 
							" - " + this.itemName + " " + this.food.getPrice() + " " + this.quantity + 
							" " + this.food.getDiscount() + "% " + "off " + this.food.getCategory());
	}
	public void setDeliveryCharge(int d) {
		this.deliveryCharge = d;
		this.totalCost = this.deliveryCharge + this.price;
	}
	
	public float getDeliveryCharge() {
		return deliveryCharge;
	}
	
	public float getTotalCost() {
		return this.totalCost;
	}
	
	public float getPrice() {
		return price;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Restaurant getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(Restaurant restaurantName) {
		this.restaurantName = restaurantName;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}
}
