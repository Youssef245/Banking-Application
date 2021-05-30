package Banking;

/**
 * Name : Youssef Mohammed Fathi Abdelrassoul
 * ID : 20180352
 * Group : IS-S3
 */

import java.util.ArrayList;

public class BankAccount {
	private int bankAccountNumber;
	private String creationDate;
	private double balance;
	ArrayList <BankTransaction> transactions = new ArrayList <BankTransaction> () ;

	
	public BankAccount() {
		creationDate="0000";
		balance=0;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public BankTransaction getTransaction(int index) {
		return transactions.get(index);
	}
	public void addTransaction(BankTransaction transaction) {
		transactions.add(transaction);
	}
	
	public int getBankAccountNumber() {
		return bankAccountNumber;
	}
	public void setBankAccountNumber(int bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	
	public ArrayList<BankTransaction> getTransactions() {
		return transactions;
	}
	public boolean updateBalance (double amount,String operation )
	{
		if(operation.equalsIgnoreCase("Transfer")) {
			if(balance>=amount) {
				balance=balance-amount;
				return true;}
			else 
				return false;}
		else if (operation.equalsIgnoreCase("Recieve")) {
			balance=balance+amount;
			return true;
		}
		else
			return false;
	}
	

}
