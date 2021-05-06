import java.util.ArrayList;
import java.util.Scanner;

public class User implements LoginPage{
	protected String name;
	protected int reward ;
	protected int noOfOrderTaken;
	protected float accountBalance;
	protected String address;
	protected int availDiscount;
	
	private float companyBalance = 0;
	private float totalDeliveryCharge = 0;
	
	
	public User() {
		reward = 0;
		noOfOrderTaken = 0;
		availDiscount = 0;
	}
	
	public int getDiscount() {
		return this.availDiscount;
	}
	public String getName() {
		return name;
	}
	public void setReward(int reward) {
		this.reward += reward;
	}
	public void setAccountBalance(float balance) {
		this.accountBalance += balance;
	}
	public void setNoOfOrderTaken(int num) {
		this.noOfOrderTaken += num;
	}
	protected void setCompanyBalance(float balance) {
		this.companyBalance += balance;
	}
	protected void setTotalDeliveryCharge(float deliveryCharge) {
		this.totalDeliveryCharge += deliveryCharge;
	}
	
	public void login_Page(LoginMenu lPage) {
		lPage.menu();
	}
	
	public int login(ArrayList<Restaurant> ret, ArrayList<Customer> cust) {
		System.out.println("Welcome to Zotato:");
		System.out.println("\t1) Enter as Restaurant Owner");
		System.out.println("\t2) Enter as Customer");
		System.out.println("\t3) Check user detail");
		System.out.println("\t4) Company account detail");
		System.out.println("\t5) Exit");
		
		
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		
		switch(input) {
		case 1:
			System.out.println("Choose Restaurant");
		
			for(int i=0;i<ret.size();i++) {
				System.out.print("\t" + (i+1) + ") " + ret.get(i).name + " ");
				if(ret.get(i).getType()!=null) {
					System.out.print("(" + ret.get(i).getType() + ")");
				}
				System.out.println();
			}
			
//			int input1 = in.nextInt();
//			while(ret.get(input1-1).login(ret, cust)!=-1) {
//				System.out.println();
//			}
			int input1 = in.nextInt();
			while(true) {
				this.login_Page(ret.get(input1-1));
				int k = ret.get(input1-1).login(ret, cust);
				if(k==-1)
					break;
				
			}
			break;
		
		case 2:
			for(int i=0;i<cust.size();i++) {
				System.out.print((i+1) + ") " + cust.get(i).name + " ");
				if(cust.get(i).getType()!=null) {
					System.out.print("(" + cust.get(i).getType() + ") ");
				}
				System.out.println();
			}
//			input = in.nextInt();
//			while(cust.get(input-1).login(ret, cust)!=-1) {
//				System.out.println();
//			}
			input = in.nextInt();
			while(true) {
				this.login_Page(cust.get(input-1));
				int k = cust.get(input-1).login(ret, cust);
				if(k==-1)
					break;
			}
			
			break;
			
		case 3:
			System.out.println("\t1) Customer List");
			System.out.println("\t2) Restaurant List");
			input = in.nextInt();
			
			if(input==1) {
				for(int i=0;i<cust.size();i++) {
					System.out.println((i+1) + ") " + cust.get(i).name + " ");
				}
				input = in.nextInt();
				cust.get(input-1).displayDetail();
			}
			else if(input==2) {
				for(int i=0;i<ret.size();i++) {
					System.out.println((i+1) + ") " + ret.get(i).name + " ");
				}
				input = in.nextInt();
				ret.get(input-1).displayDetail();
			}
			
			break;
			
		case 4:
			System.out.println("Total Company Balance - INR " + this.companyBalance + "/-");
			System.out.println("Total Delivery Charges Collected - INR " + this.totalDeliveryCharge + "/-");
			
			break;
			
		case 5:
			
			return -1;
		}
		
		return 0;
	}
}
