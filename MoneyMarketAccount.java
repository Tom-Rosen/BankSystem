
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Money Market Account
// Course:   CS 300 Fall 2020
//
// Author:   Tom Rosen
// Email:    trrosen@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         
// Online Sources:  
//					
//
///////////////////////////////////////////////////////////////////////////////

/**
 * Class extends Bank Account, and creates the basic model for a money market account.  Money market
 * account has an interest rate, as well as a fine for withdrawing funds.  Money market accounts
 * have all the behaviors of an object of BankAccount, but also has the ability to add intrest and 
 * add a penalty for withdrawals.  Also has it's own toString() to differentiate between a bank 
 * account and a money market account.
 * 
 * @author Tom Rosen
 *
 */
public class MoneyMarketAccount extends BankAccount {
	
	private static final double PENALTY_RATE = 0.1; //Rate of penalty on withdrawals.
	private static final double INTEREST_RATE = 0.05; //Interest rate on account.
	
	/**
	 * Constructor for a MoneyMarketAccount; sets inital balance to param and calls the super 
	 * constructor for the rest of info set
	 * 
	 * @see BankAccount constructor for other inializations.
	 * @param initialBalance - Inital amount to put into account.
	 * @throws IllegalArgumentException if amount is < $10.0.
	 */
	public MoneyMarketAccount(double initialBalance) throws IllegalArgumentException
	{
		super(initialBalance);
	}
	
	/**
	 * Adds intrest amount to MoneyMarketAccount based on current balance of account.
	 */
	public void addInterest()
	{
		double add = super.balance() * INTEREST_RATE;;
		super.deposit(add);
	}
	
	/**
	 * Withdraws amount from account as well as the penalty amount for making a withdraw.  Only 
	 * withdraws if there is enough money in the account to withdraw amount + penalty amount.
	 * 
	 * @param amount - Amount to be withdrawn from account.
	 * @Override withdraw method in BankAccount
	 * @return True if result is withdrawn, false otherwise.
	 */
	@Override
	public boolean withdraw(double amount)
	{
		double withdrawlPen = amount * PENALTY_RATE;
		double withdrawlAmount = amount + withdrawlPen;
		boolean result = super.withdraw(withdrawlAmount);
		return result;
	}
	
	/**
	 * Creates and returns a String of the information stored in the account, namely the NUMBER(ID)
	 * and the balance of the account.
	 * 
	 * @return String of information in account.
	 */
	public String toString()
	{
		return "Money Market Account #" + NUMBER + ": $" + super.balance();
	}
	
}
