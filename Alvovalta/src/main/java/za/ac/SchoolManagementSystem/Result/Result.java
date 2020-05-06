package Result;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Objects;
import java.util.Scanner;


public class Result {

	private double testResult;
	private double examResult;
	private double assignmentResult;
	private double averageMark;

	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}

	public double getTestResult() {
		return testResult;
	}

	public void setTestResult(double testResult) {
		this.testResult = testResult;
	}

	public double getExamResult() {
		return examResult;
	}

	public void setExamResult(double examResult) {
		this.examResult = examResult;
	}

	public double getAssignmentResult() {
		return assignmentResult;
	}

	public void setAssignmentResult(double assignmentResult) {
		this.assignmentResult = assignmentResult;
	}

	public double getAverageMark() {
		return averageMark;
	}

	public void setAverageMark(double averageMark) {
		this.averageMark = averageMark;
	}

	@Override
	public String toString() {
		return "Result [testResult=" + testResult + ", examResult=" + examResult + ", assignmentResult="
				+ assignmentResult + ", averageMark=" + averageMark + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(assignmentResult, averageMark, examResult, testResult);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Result other = (Result) obj;
		return Double.doubleToLongBits(assignmentResult) == Double.doubleToLongBits(other.assignmentResult)
				&& Double.doubleToLongBits(averageMark) == Double.doubleToLongBits(other.averageMark)
				&& Double.doubleToLongBits(examResult) == Double.doubleToLongBits(other.examResult)
				&& Double.doubleToLongBits(testResult) == Double.doubleToLongBits(other.testResult);
	}

		public void updateResult(Scanner sc) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		// "com.mysql.jdbc.Driver" this is the path
		Connection c1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pvi", "root", "pvi@2020");
		// here Pvi is database name, root is username and password
		Statement st = c1.createStatement();

		System.out.println("UPDATE RESULT");
		System.out.println();
		System.out.println("Enter staff number");
		int staffNo = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter student number");
		int studentNo = sc.nextInt();
		System.out.println("Enter subject code?");
		int subjectCode = sc.nextInt();
		System.out.println("Enter assignment score?");
		double assignment = sc.nextDouble();
		System.out.println("Enter test score");
		double test = sc.nextDouble();
		System.out.println("Enter exam score");
		double exam = sc.nextDouble();
		double average = (assignment + test + exam) / 3;
		average = Math.round(average * 100);
		average = average / 100;
		System.out.println("Your average score is: " + average);

		String sql = "INSERT INTO Result (staff_no, student_no, subject_code, assignment, test, exam, average)"
				+ " VALUES (" + staffNo + "," + studentNo + "," + subjectCode + "," + assignment + "," + test + ","
				+ exam + "," + average + ")";

		int res = st.executeUpdate(sql);
		System.out.println("Number of rows affected: " + res);

		c1.close();
	}
}
