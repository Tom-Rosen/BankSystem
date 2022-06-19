// Name: Tom Rosen
// Email: trrosen @wisc.edu 

import java.util.ArrayList;

/**
 * This class creates the basic model and behaviors for a Bank Account.  A Bank Account knows its
 * name and is given a unique ID.  It is also added into an array of accounts.  It is given an 
 * inital amount over $10 and has the ability to deposit and withdraw, get the current balance,
 * see if one account equals another(same ID), and has the ability to get all elements and values 
 * in accounts array.
 * 
 * @author Tom Rosen
 *
 */
public class BankAccount {
	
	private static final String BANK_NAME = "Fantastic Bank"; //Bank name, same for all accounts
	private static ArrayList<BankAccount> accounts = new ArrayList<BankAccount>(); //store accounts
	private static int numberGenerator = 1001; //number generator for account ID
	protected final int NUMBER; //account ID
	private double balance; //balance inside of account
	
	/**
	 * Constructor for BankAccount.  Sets inital values to parameter, NUMBER to numberGenerator, and
	 * increments numberGenerator so the next BankAccount has a unique ID.
	 * 
	 * @param initalBalance - Initial balance to set account to.
	 * @throws IllegalArgumentException if initalBalance < 10.0.
	 */
	public BankAccount(double initalBalance) throws IllegalArgumentException
	{
		if(initalBalance < 10.0)
		{
			throw new IllegalArgumentException("Inital balance less than $10.0");
		}
		this.NUMBER = numberGenerator;
		numberGenerator++;
		this.balance = initalBalance;
		accounts.add(this);
	}
	
	/**
	 * Enables user to deposit an amount into the account if amount is above $0.
	 * 
	 * @param amount - Amount to be deposited into account.
	 * @throws IllegalArgumentException if amount is <= 0.0.
	 */
	public void deposit(double amount) throws IllegalArgumentException
	{
		if(amount <= 0.0)
		{
			throw new IllegalArgumentException("Amount deposited was <= $0.0");
		}
		this.balance = this.balance + amount;
	}
	
	/**
	 * Withdraws the parameter amount from the account if amount is greater than 0.0 and the account
	 * has enough money that it doesn't go into the negative after a withdraw.  If either of these
	 * isn't satisfied it throws and IllegalArgumentException.
	 * 
	 * @param amount - Amount to be withdrawn from account.
	 * @return True if withdrawal was successful, false otherwise.
	 */
	public boolean withdraw(double amount)
	{
		if(amount <= 0.0 || this.balance - amount < 0.0)
		{
			return false;
		}
		this.balance = this.balance - amount;
		return true;
	}
	
	/**
	 * Returns the balance of the account.
	 * 
	 * @return the current balance of account.
	 */
	public double balance()
	{
		return this.balance;
	}
	
	/**
	 * Checks to see if an object o is the same as BankAccount passed in, by checking to see if the 
	 * if the account NUMBER(ID) is the same, and object o is an instance of BankAccount.
	 * 
	 * @return True if object 0 is the same as passed BankAccount, false otherwise.
	 */
	public boolean equals(Object o)
	{
		if(o instanceof BankAccount && ((BankAccount) o).NUMBER == this.NUMBER)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Creates a string representation of this BankAccount's information; namely the bank number and
	 * balance.
	 * 
	 * @return String of information for this BankAccount.
	 */
	public String toString()
	{
		return "Bank Account #" + this.NUMBER + ": $" + this.balance;
	}
	
	/**
	 * Creates a long String of all accounts and their information in the accounts ArrayList.  In my
	 * code I had it print the Bank Name: ---- followed by a new line.  After this it traversed the 
	 * ArrayList, adding the toString of each element and a new line.
	 * Example:
	 * "Bank Name: Cool Bank
	 *  Bank Account #1001: $15.0
	 *  Money Market Account #1002 $20.0
	 *  "
	 * 
	 * @return the toString of all elements in the accounts ArrayList.
	 */
	public static String getAllAccounts()
	{
		String allAccounts = "";
		allAccounts = "Bank Name: " + BANK_NAME + "\n";
		for(int i = 0; i < accounts.size(); i++)
		{
			allAccounts += accounts.get(i).toString() + "\n";
		}
		return allAccounts;
	}
	
	/**
	 * Creates a demo of the BankAccount and MoneyMarketAccount code to demonstrate proper 
	 * functionality of the code.  Creates 3 BankAccounts, 2 MoneyMarketAccounts, and then adds 
	 * funds, withdraws funds, and adds interest to MoneyMarketAccounts only.  Also tests to make 
	 * sure the errors are properly caught and handled.
	 */
	public static void demo()
	{
		System.out.println("Error caught in constructor demo");
		try
		{
			BankAccount b1 = new BankAccount(5.0);
		}
		catch(IllegalArgumentException e)
		{
			System.out.println(e.getMessage());
			
		}
		BankAccount b2 = null;
		BankAccount b3 = null;
		BankAccount b4 = null;
		try
		{
			b2 = new BankAccount(10.0); 
			b3 = new BankAccount(20.0);
			b4 = new BankAccount(30.0);
		}
		catch(IllegalArgumentException e)
		{
			System.out.println(e.getMessage());
			System.out.println("ERROR");
			
		}
		
		try
		{
			MoneyMarketAccount b1 = new MoneyMarketAccount(5.0);
		}
		catch(IllegalArgumentException e)
		{
			System.out.println(e.getMessage());	
		}
		MoneyMarketAccount m2 = null;
		MoneyMarketAccount m3 = null;
		try
		{
			m2 = new MoneyMarketAccount(10.0);
			m3 = new MoneyMarketAccount(20.0);
		}
		catch(IllegalArgumentException e)
		{
			System.out.println(e.getMessage());	
			System.out.println("ERROR");
		}
		System.out.println();
		System.out.println("Initalization of accounts demo");
		System.out.println(getAllAccounts());
		
		try
		{
			b2.deposit(10.0);
			m2.deposit(10.0);
			b3.deposit(5.0);
			m3.deposit(5.0);
			b4.deposit(1.0);
		}
		catch(IllegalArgumentException e)
		{
			System.out.println(e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("ERROR: " + e.getMessage());
		}
		
		for(int i = 0; i < accounts.size(); i++)
		{
			if(accounts.get(i) instanceof MoneyMarketAccount)
			{
				((MoneyMarketAccount)accounts.get(i)).addInterest();
			}
		}

		
		System.out.println("Deposit and add interest demo");
		System.out.println(getAllAccounts());
		
		b2.withdraw(5.0);
		m2.withdraw(5.0);
		b3.withdraw(5.0);
		m3.withdraw(5.0);
		
		System.out.println("Withdraw and add penalty demo");
		System.out.println(getAllAccounts());
		
		System.out.println("equals() method demo, first should be equal rest shouldn't be");
		BankAccount b5 = b2;
		for(int i = 0; i < accounts.size(); i++)
		{
			if(accounts.get(i).equals(b5))
			{
				System.out.println("The two accounts are equal");
			}
			else
			{
				System.out.println("The two accounts are NOT equal");
			}
		}
	}
	
	/**
	 * Runs demo() method only.
	 * 
	 * @param args - Unused.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		demo();
	}

}
