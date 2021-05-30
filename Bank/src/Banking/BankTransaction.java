package Banking;

/**
 * Name : Youssef Mohammed Fathi Abdelrassoul
 * ID : 20180352
 * Group : IS-S3
 */

public class BankTransaction {
	private String creationDate;
	private double amount;
	private String Operation;
	private int ID;
	private int fromAccount;
	private int toAccount;
	
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getOperation() {
		return Operation;
	}
	public void setOperation(String Operation) {
		this.Operation = Operation;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(int fromAccount) {
		this.fromAccount = fromAccount;
	}
	public int getToAccount() {
		return toAccount;
	}
	public void setToAccount(int toAccount) {
		this.toAccount = toAccount;
	}
	
	

}
