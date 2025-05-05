package Finance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Objects;
import java.util.Scanner;

public class Fees {

	
	private double amount;
	private double amountPaid;
	private double balance;

	public Fees() {
		super();
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Fees [amount=" + amount + ", amountPaid=" + amountPaid + ", balance="
				+ balance + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, amountPaid, balance);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fees other = (Fees) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Double.doubleToLongBits(amountPaid) == Double.doubleToLongBits(other.amountPaid)
				&& Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance);
	}
	
	public void updateFees(Scanner sc) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		//"com.mysql.jdbc.Driver" this is the path
		Connection c1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pvi", "root", "pvi@2020");
		//here Pvi is database name, root is username and password
		Statement  st = c1.createStatement();
		
		System.out.println("\tWELCOME TO UPDATE FEES");
		System.out.println();
		System.out.println("Enter staff number");
		int staffNo = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter student number");
		int studentNo = sc.nextInt();
		//sc.nextLine();
		System.out.println("Enter subject code?");
		int subjectCode = sc.nextInt();
		System.out.println("Enter assignment score?");
		double assignment = sc.nextDouble();
		sc.nextLine();
		System.out.println("Enter test grade bracket");
		double test = sc.nextDouble();
		System.out.println("Enter exam grade bracket");
		double exam = sc.nextDouble();
		System.out.println("Enter average grade bracket");
		double average = sc.nextDouble();
		
		
		String sql = "INSERT INTO Staff (staff_no, student_no, subject_code, assignment, test, exam, average,"
				+ " password) VALUES (" + staffNo + studentNo +"," + subjectCode + "," + assignment + "," + test + "," + exam +
				"," + average + ")"; 
		
	    int res = st.executeUpdate(sql);
		System.out.println("Number of rows affected: " + res);
			
		c1.close();
	}
	}
	


