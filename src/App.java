import java.util.ArrayList;

public class App {
	ArrayList<Restaurant> rList = new ArrayList<Restaurant>();
	ArrayList<Customer> cList = new ArrayList<Customer>();
	
	
	User user = new User();
	
	public void startApp() {
		
		rList.add(new Restaurant("Barbeque-Bones", "Wall Street, Lucknow", retType.Authentic));
		rList.add(new Restaurant("Desi Rasoi", "Lal Kila", null));
		rList.add(new Restaurant("Chacha-Da-Dhaba", "Jilla Gaziabad", retType.FastFood));
		rList.add(new Restaurant("The Modern Shop", "New Yorkva", retType.Authentic));
		rList.add(new Restaurant("I_I_D", "Harkesh Nagar", null));
		
		cList.add(new Customer("Manauv", "Dumbivalli", customerType.Elite));
		cList.add(new Customer("Sarfraz", "Islamabad", null));
		cList.add(new Customer("Mansoor", "Kedarnath", null));
		cList.add(new Customer("Immannuel Rajkumar Jr", "Jamshedpur", customerType.Special));
		cList.add(new Customer("Nayra", "Noida", null));
		
		
		while(user.login(rList, cList)!=-1) {
			System.out.println();
		}
	}
	
}
	

