
public class Food implements DetailPrinter {
	private final int ID;
	private String name;
	private float price;
	private int discount;
	private Category category;
	private int quantity;
	private Restaurant restaurant;
	
	public Food(int Id, String name, float price, int quantity, int discount, Category catg, Restaurant rest) {
		this.ID = Id;
		this.name = name;
		this.category = catg;
		this.price = price;
		this.quantity = quantity;
		this.discount = discount;
		this.restaurant = rest;
	}
	

	public int getID() {
		return this.ID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void reduceQuantity(int q) {
		this.quantity -= q;
	}


	public void displayDetail() {
		System.out.println(this.ID + " " + this.restaurant.getName() + " - "+ this.name + " "+ this.price + " "+ this.quantity + " "+ this.discount + "% "+ "off "+ this.category);
	}
	
}

enum Category{
	Starter, MainCourse, Dessert, Beverage
}
