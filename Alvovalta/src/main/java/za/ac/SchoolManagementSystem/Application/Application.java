package Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import Finance.Fees;
import People.Student;
import Result.Result;
import Subject.Subject;
import People.Staff;

public class Application {

	// local variables
	static boolean loggedIn;
	static int option;

	public static void main(String[] args) {

		//object created
		Scanner sc = new Scanner(System.in);
		Student student = new Student();
		Staff staff = new Staff();
		Result result = new Result();
		Fees fee = new Fees();
		Subject subject = new Subject();
		Menu menu = new Menu();

		for(int z=0; z<5; z++) {
		System.out.println();
		System.out.println("\tWELCOME TO ALVOVALTA SCHOOL MANAGEMENT SYSTEM\t \t \t");
		System.out.println();

		menu.menu();
		System.out.println();
		System.out.println("Select your option to continue");
		System.out.println();
		try {
			System.out.print("Enter option :");
			option = sc.nextInt();
		} catch (InputMismatchException ex) {
			System.out.println("Invalid Input, try again!!!!");
		}

		if (option == 1) {
			System.out.println();
			menu.menuA();
			System.out.println();
			System.out.println("Select your option to continue");
			System.out.println();
			try {
				System.out.print("Enter option :");
				option = sc.nextInt();
			} catch (InputMismatchException ex) {
				System.out.println("Invalid Input, try again!!!!");
			}
			if (option == 3) {
				try {
					//calling register in student class
					student.register(sc);
				} catch (Exception ex) {
					System.out.println(ex);
				}
			} else if (option == 4) {
				try {
					//calling register in staff class
					staff.register(sc);
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		}
		if (option == 2) {
			System.out.println();
			menu.menuB();
			System.out.println();
			System.out.println("Select your option to continue");
			System.out.println();
			try {
				System.out.print("Enter option :");
				option = sc.nextInt();
			} catch (InputMismatchException ex) {
				System.out.println("Invalid Input, try again!!!!");
			}
			if (option == 5) {
				System.out.println();
				for (int a = 0; a < 3; a++) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						// "com.mysql.jdbc.Driver" this is the path
						Connection c1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pvi", "root",
								"pvi@2020");
						// here Pvi is database name, root is username and password
						Statement st = c1.createStatement();
						System.out.println("*** LOG IN AS A STUDENT ***");
						// System.out.println();
						System.out.println("*** Enter your full names. ***");
						sc.nextLine();
						String fullName = sc.nextLine();
						// sc.nextLine();
						System.out.println("*** Enter your password. ***");

						String password = sc.nextLine();
						ResultSet res = st.executeQuery("SELECT * FROM Student WHERE full_names =" + "'" + fullName
								+ "'" + "AND password =" + "'" + password + "'");
						while (res.next()) {
							student.setFullNames(res.getString("full_names"));
							student.setPassword(res.getString("password"));
							//calling login method in student class
							loggedIn = student.logIn(fullName, password, student, sc);
						}
						c1.close();
					} catch (InputMismatchException e) {
						System.out.println("Invalid input");
					} catch (Exception ex) {
						System.out.println(ex);
					}
					if (loggedIn == false) {
						System.out.println("*** Wrong names and Password, Try again!!! ***");
						System.out.println();
					} else if (loggedIn == true) {
						a = 4;
					}
				}
				if (loggedIn == true) {
					System.out.println("*** Login Successfully!!! ***");
					System.out.println();
					menu.menu2();
					System.out.println();
					System.out.println("Select your option to continue");
					System.out.println();
					try {
						option = sc.nextInt();
					} catch (InputMismatchException ex) {
						System.out.println("Invalid Input, try again!!!!");
					}

					if (option == 7) {
						System.out.println();
						try {
							Class.forName("com.mysql.jdbc.Driver");
							// "com.mysql.jdbc.Driver" this is the path
							Connection c1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pvi", "root",
									"pvi@2020");
							// here Pvi is database name, root is username and password
							Statement st = c1.createStatement();
							System.out.println("*** Enter your student number. ***");
							int studentNo = sc.nextInt();

							// Sql statement for student
							ResultSet res = st.executeQuery("SELECT student_no, full_names, id_number, age, sex,"
									+ " grade, address, parent_info FROM Student WHERE student_no =" + studentNo);

							while (res.next()) {
								student.setStudentNo(res.getInt("student_no"));
								student.setFullNames(res.getString("full_names"));
								student.setIdNumber(res.getLong("id_number"));
								student.setAge(res.getInt("age"));
								student.setSex(res.getString("sex"));
								student.setGrade(res.getString("grade"));
								student.setAddress(res.getString("address"));
								student.setParentInfo(res.getString("parent_info"));
								System.out.println();
								System.out.println("STUDENT INFORMATION" + "\n___________________" + "\nStudent no: "
										+ student.getStudentNo() + "\nFull Name: " + student.getFullNames()
										+ "\nId number: " + student.getIdNumber() + "\nAge: " + student.getAge()
										+ "\nSex: " + student.getSex() + "\nGrade: " + student.getGrade()
										+ "\nAddress: " + student.getAddress() + "\nParent Information: "
										+ student.getParentInfo());
							}
							c1.close();
						} catch (InputMismatchException e) {
							System.out.println("Invalid input");
						} catch (Exception ex) {
							System.out.println(ex);
						}
					} else if (option == 8) {
						System.out.println();
						try {
							Class.forName("com.mysql.jdbc.Driver");
							// "com.mysql.jdbc.Driver" this is the path
							Connection c1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pvi", "root",
									"pvi@2020");
							// here Pvi is database name, root is username and password
							Statement st = c1.createStatement();
							System.out.println("*** Enter your student number. ***");
							int studentNo = sc.nextInt();

							// Sql statement for student
							ResultSet res = st
									.executeQuery("SELECT Student.student_no, Student.full_names, Student.id_number,"
											+ " Student.grade, Result.subject_code, Result.staff_no, Result.test, Result.assignment,"
											+ "Result.exam, Result.average FROM Student JOIN Result ON Student.student_no = Result.student_no WHERE Student.student_no ="
											+ studentNo);

							while (res.next()) {
								student.setStudentNo(res.getInt("student_no"));
								student.setFullNames(res.getString("full_names"));
								student.setIdNumber(res.getLong("id_number"));
								student.setGrade(res.getString("grade"));
								subject.setSubjectCode(res.getInt("subject_code"));
								staff.setStaffNo(res.getInt("staff_no"));
								result.setTestResult(res.getDouble("test"));
								result.setAssignmentResult(res.getDouble("assignment"));
								result.setExamResult(res.getDouble("exam"));
								result.setAverageMark(res.getDouble("average"));
								System.out.println();
								System.out.println("RESULT INFORMATION" + "\n__________________" + "\nStudent no: "
										+ student.getStudentNo() + "\nFull Names: " + student.getFullNames()
										+ "\nId number: " + student.getIdNumber() + "\nGrade: " + student.getGrade()
										+ "\nSubject code: " + subject.getSubjectCode() + "\nStaff no: "
										+ staff.getStaffNo() + "\nTest: " + result.getTestResult() + "\nAssignment: "
										+ result.getAssignmentResult() + "\nExam: " + result.getExamResult()
										+ "\nAverage: " + result.getAverageMark());
							}
							c1.close();
						} catch (InputMismatchException e) {
							System.out.println("Invalid input");
						} catch (Exception ex) {
							System.out.println(ex);
						}
					} else if (option == 9) {
						System.out.println();
						try {
							Class.forName("com.mysql.jdbc.Driver");
							// "com.mysql.jdbc.Driver" this is the path
							Connection c1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pvi", "root",
									"pvi@2020");
							// here Pvi is database name, root is username and password
							Statement st = c1.createStatement();
							System.out.println("*** Enter your student number. ***");
							int studentNo = sc.nextInt();

							// Sql statement for student
							ResultSet res = st
									.executeQuery("SELECT Student.student_no, Student.full_names, Student.id_number,"
											+ " Student.grade, Fees.amount, Fees.amount_paid, Fees.balance FROM Student JOIN Fees ON"
											+ " Student.student_no = Fees.student_no WHERE Student.student_no="
											+ studentNo);

							while (res.next()) {
								student.setStudentNo(res.getInt("student_no"));
								student.setFullNames(res.getString("full_names"));
								student.setIdNumber(res.getLong("id_number"));
								student.setGrade(res.getString("grade"));
								fee.setAmount(res.getDouble("amount"));
								fee.setAmountPaid(res.getDouble("amount_paid"));
								fee.setBalance(res.getDouble("balance"));
								System.out.println();
								System.out.println("ACCOUNT INFORMATION" + "\n___________________" + "\nStudent no: "
										+ student.getStudentNo() + "\nFull names: " + student.getFullNames()
										+ "\nId number: " + student.getIdNumber() + "\nGrade: " + student.getGrade()
										+ "\nAmount: " + fee.getAmount() + "\nAmount paid: " + fee.getAmountPaid()
										+ "\nBalance: " + fee.getBalance());
							}
							c1.close();
						} catch (InputMismatchException e) {
							System.out.println("Invalid input");
						} catch (Exception ex) {
							System.out.println(ex);
						}
					}
				} else {
					System.out.println("*** Invalid login credentials ***");
					System.exit(1);
				}
			}
		}
		if (option == 6) {
			System.out.println();
			for (int a = 0; a < 3; a++) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					// "com.mysql.jdbc.Driver" this is the path
					Connection c1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pvi", "root", "pvi@2020");
					// here Pvi is database name, root is username and password
					Statement st = c1.createStatement();
					System.out.println("*** LOG IN AS A STAFF MEMEBER ***");
					System.out.println();
					System.out.println("*** Enter your full names. ***");
					sc.nextLine();
					String fullName = sc.nextLine();
					System.out.println("*** Enter your password. ***");
					String password = sc.nextLine();

					// Sql statement for student
					ResultSet res = st.executeQuery("SELECT * FROM Staff WHERE full_names =" + "'" + fullName + "'"
							+ "AND password =" + "'" + password + "'");

					while (res.next()) {
						staff.setFullNames(res.getString("full_names"));
						staff.setPassword(res.getString("password"));
						loggedIn = staff.logIn(fullName, password, staff, sc);
					}
					c1.close();
				} catch (InputMismatchException e) {
					System.out.println("Invalid input");
				} catch (Exception ex) {
					System.out.println(ex);
				}
				if (loggedIn == false) {
					System.out.println("*** Wrong names and Password, Try again!!! ***");
					System.out.println();
				} else if (loggedIn == true) {
					a = 4;
				}
			}
			if (loggedIn == true) {
				System.out.println("*** Login Successfully!!! ***");
				System.out.println();
				menu.menu3();
				System.out.println();
				System.out.println("Select your option to continue");
				System.out.println();
				try {
					System.out.print("Enter option: ");
					option = sc.nextInt();
				} catch (InputMismatchException ex) {
					System.out.println("Invalid Input, try again!!!!");
				}
				if (option == 10) {
					System.out.println();
					try {
						Class.forName("com.mysql.jdbc.Driver");
						// "com.mysql.jdbc.Driver" this is the path
						Connection c1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pvi", "root",
								"pvi@2020");
						// here Pvi is database name, root is username and password
						Statement st = c1.createStatement();
						System.out.println("*** Enter your student number. ***");
						int studentNo = sc.nextInt();

						// Sql statement for student
						ResultSet res = st.executeQuery("SELECT student_no, full_names, id_number, age, sex,"
								+ " grade, address, parent_info FROM Student WHERE student_no =" + studentNo);

						while (res.next()) {
							student.setStudentNo(res.getInt("student_no"));
							student.setFullNames(res.getString("full_names"));
							student.setIdNumber(res.getLong("id_number"));
							student.setAge(res.getInt("age"));
							student.setSex(res.getString("sex"));
							student.setGrade(res.getString("grade"));
							student.setAddress(res.getString("address"));
							student.setParentInfo(res.getString("parent_info"));
							System.out.println();
							System.out.println("STUDENT INFORMATION" + "\n___________________" + "\nStudent no: "
									+ student.getStudentNo() + "\nFull Name: " + student.getFullNames()
									+ "\nId number: " + student.getIdNumber() + "\nAge: " + student.getAge() + "\nSex: "
									+ student.getSex() + "\nGrade: " + student.getGrade() + "\nAddress: "
									+ student.getAddress() + "\nParent Information: " + student.getParentInfo());
						}
						c1.close();
					} catch (InputMismatchException e) {
						System.out.println("Invalid input");
					} catch (Exception ex) {
						System.out.println(ex);
					}
				} else if (option == 11) {
					System.out.println();
					try {
						Class.forName("com.mysql.jdbc.Driver");
						// "com.mysql.jdbc.Driver" this is the path
						Connection c1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pvi", "root",
								"pvi@2020");
						// here Pvi is database name, root is username and password
						Statement st = c1.createStatement();
						System.out.println("*** Enter your student number. ***");
						int studentNo = sc.nextInt();

						// Sql statement for student
						ResultSet res = st
								.executeQuery("SELECT Student.student_no, Student.full_names, Student.id_number,"
										+ " Student.grade, Result.subject_code, Result.staff_no, Result.test, Result.assignment,"
										+ "Result.exam, Result.average FROM Student JOIN Result ON Student.student_no = Result.student_no WHERE Student.student_no ="
										+ studentNo);

						while (res.next()) {
							student.setStudentNo(res.getInt("student_no"));
							student.setFullNames(res.getString("full_names"));
							student.setIdNumber(res.getLong("id_number"));
							student.setGrade(res.getString("grade"));
							subject.setSubjectCode(res.getInt("subject_code"));
							staff.setStaffNo(res.getInt("staff_no"));
							result.setTestResult(res.getDouble("test"));
							result.setAssignmentResult(res.getDouble("assignment"));
							result.setExamResult(res.getDouble("exam"));
							result.setAverageMark(res.getDouble("average"));
							System.out.println();
							System.out.println("RESULT INFORMATION" + "\n__________________" + "\nStudent no: "
									+ student.getStudentNo() + "\nFull Names: " + student.getFullNames()
									+ "\nId number: " + student.getIdNumber() + "\nGrade: " + student.getGrade()
									+ "\nSubject code: " + subject.getSubjectCode() + "\nStaff no: "
									+ staff.getStaffNo() + "\nTest: " + result.getTestResult() + "\nAssignment: "
									+ result.getAssignmentResult() + "\nExam: " + result.getExamResult() + "\nAverage "
									+ result.getAverageMark());
						}
						c1.close();
					} catch (InputMismatchException e) {
						System.out.println("Invalid input");
					} catch (Exception ex) {
						System.out.println(ex);
					}
				} else if (option == 12) {
					System.out.println();
					try {
						Class.forName("com.mysql.jdbc.Driver");
						// "com.mysql.jdbc.Driver" this is the path
						Connection c1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pvi", "root",
								"pvi@2020");
						// here Pvi is database name, root is username and password
						Statement st = c1.createStatement();
						System.out.println("*** Enter your student number. ***");
						int studentNo = sc.nextInt();

						// Sql statement for student
						ResultSet res = st
								.executeQuery("SELECT Student.student_no, Student.full_names, Student.id_number,"
										+ " Student.grade, Fees.amount, Fees.amount_paid, Fees.balance FROM Student JOIN Fees ON"
										+ " Student.student_no = Fees.student_no WHERE Student.student_no="
										+ studentNo);

						while (res.next()) {
							student.setStudentNo(res.getInt("student_no"));
							student.setFullNames(res.getString("full_names"));
							student.setIdNumber(res.getLong("id_number"));
							student.setGrade(res.getString("grade"));
							fee.setAmount(res.getDouble("amount"));
							fee.setAmountPaid(res.getDouble("amount_paid"));
							fee.setBalance(res.getDouble("balance"));
							System.out.println();
							System.out.println("ACCOUNT INFORMATION" + "\n___________________" + "\nStudent no: "
									+ student.getStudentNo() + "\nFull names: " + student.getFullNames()
									+ "\nId number: " + student.getIdNumber() + "\nGrade: " + student.getGrade()
									+ "\nAmount: " + fee.getAmount() + "\nAmount paid: " + fee.getAmountPaid()
									+ "\nBalance: " + fee.getBalance());
						}
						c1.close();
					} catch (InputMismatchException e) {
						System.out.println("Invalid input");
					} catch (Exception ex) {
						System.out.println(ex);
					}
				} else if (option == 13) {
					System.out.println();
					try {
						Class.forName("com.mysql.jdbc.Driver");
						// "com.mysql.jdbc.Driver" this is the path
						Connection c1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pvi", "root",
								"pvi@2020");
						// here Pvi is database name, root is username and password
						Statement st = c1.createStatement();
						System.out.println("*** Enter your staff number. ***");
						int staffNo = sc.nextInt();

						// Sql statement for student
						ResultSet res = st.executeQuery("SELECT staff_no, full_names, id_number, age, sex,"
								+ "address, position FROM Staff WHERE staff_no =" + staffNo);

						while (res.next()) {
							staff.setStaffNo(res.getInt("staff_no"));
							staff.setFullNames(res.getString("full_names"));
							staff.setIdNumber(res.getLong("id_number"));
							staff.setAge(res.getInt("age"));
							staff.setSex(res.getString("sex"));
							staff.setAddress(res.getString("address"));
							staff.setPosition(res.getString("position"));
							System.out.println();
							System.out.println("STAFF INFORMATION" + "\n___________________" + "\nStudent no: "
									+ staff.getStaffNo() + "\nFull Name: " + staff.getFullNames() + "\nId number: "
									+ staff.getIdNumber() + "\nAge: " + staff.getAge() + "\nSex: " + staff.getSex()
									+ "\nAddress: " + staff.getAddress() + "\nPosition: " + staff.getPosition());
						}
						c1.close();
					} catch (InputMismatchException e) {
						System.out.println("Invalid input");
					} catch (Exception ex) {
						System.out.println(ex);
					}

				} else if (option == 14) {
					System.out.println();
					try {
						//calling the updateResult method in the result class
						result.updateResult(sc);
					} catch (InputMismatchException e) {
						System.out.println("Invalid input");
					} catch (Exception ex) {
						System.out.println(ex);
					}
				} else if (option == 15) {
					System.out.println();
					try {
						//calling the updateFees method in the fee class
						fee.updateFees(sc);
					} catch (InputMismatchException e) {
						System.out.println("Invalid input");
					} catch (Exception ex) {
						System.out.println(ex);
					}
				} else if (option == 16) {
					try {
						//calling the    method in the subject class
							subject.updateSubject(sc);
					} catch (InputMismatchException e) {
						System.out.println("Invalid input");
					} catch (Exception ex) {
						System.out.println(ex);
					}
				} else if (option == 17) {
						System.out.println();
						System.out.println("Do you want to delete a record");
						//System.out.println("\tYes or No");
						String response = sc.nextLine();
						if (response.trim().equalsIgnoreCase("yes")) {
							System.out.println("Are you sure you want to delete a record");
							//System.out.println("\tYes or No");
							String response1 = sc.nextLine();
							if (response1.trim().equalsIgnoreCase("yes")) {
								try {
									Class.forName("com.mysql.jdbc.Driver");
									// "com.mysql.jdbc.Driver" this is the path
									Connection c1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pvi",
											"root", "pvi@2020");
									// here Pvi is database name, root is username and password
									Statement st = c1.createStatement();
									System.out.println("Record deleted cannot be recovered!!!");
									System.out.println("*** Enter your student number. ***");
									int studentNo = sc.nextInt();

									// Sql statement for student
									ResultSet res = st.executeQuery(
											"DELETE Student.student_no, Student.full_names, Student.id_number,"
													+ "Student.age, Student.sex, Student.grade, Student.address, Student.parent_info,"
													+ "Student.password, Result.staff_no, Result.student_no, Result.subject_code,"
													+ "Result.assignment, Result.test, Result.exam, Result.average,"
													+ "Fees.student_no, Fees.amount, Fees.amount_paid, Fees.balance FROM Student JOIN"
													+ "Result ON Student.student_no = Result.student_no JOIN Fees ON"
													+ "Student.student_no = Fees.student_no WHERE Student.student_no="
													+ studentNo);
									System.out.println("Number of rows affected: " + res);
									System.out.println("Student record deleted");
								} catch (InputMismatchException e) {
									System.out.println("Invalid input");
								} catch (Exception ex) {
									System.out.println(ex);
								}
						} //else {System.out.println("Try again!!!");System.exit(0);}
					} //else System.out.println("Try again!!!"); System.exit(0);}
				
			} else if (option == 18) {
				System.out.println();
					System.out.println("Do you want to delete a record");
					System.out.println("\tYes or No");
					String response = sc.nextLine();
					if (response.trim().equalsIgnoreCase("yes")) {
						System.out.println("Are you sure you want to delete a record");
						//System.out.println("\tYes or No");
						String response1 = sc.nextLine();
						if (response1.trim().equalsIgnoreCase("yes")) {
							try {
								Class.forName("com.mysql.jdbc.Driver");
								// "com.mysql.jdbc.Driver" this is the path
								Connection c1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pvi", "root",
										"pvi@2020");
								// here Pvi is database name, root is username and password
								Statement st = c1.createStatement();
								System.out.println("Record deleted cannot be recovered!!!");
								System.out.println("*** Enter your staff number. ***");
								int staffNo = sc.nextInt();

								// Sql statement for student
								ResultSet res = st
										.executeQuery("DELETE Staff.staff_no, Staff.full_names, Staff.id_number,"
												+ "Staff.age, Staff.sex, Staff.address,"
												+ "Staff.password, Staff.position, Result.staff_no FROM Staff JOIN"
												+ "Result ON Staff.staff_no = Result.staff_no WHERE Staff.staff_no="
												+ staffNo);
								System.out.println("Number of rows affected: " + res);
								System.out.println("Student record deleted");
							} catch (InputMismatchException e) {
								System.out.println("Invalid input");
							} catch (Exception ex) {
								System.out.println(ex);
							}
						}// else {System.out.println("Try again!!!");System.exit(0);}
					} //else {System.out.println("Try again!!!");System.exit(0);}
				}
		} else {
			System.out.println("*** Invalid login credentials ***");
			System.exit(1);
		}

	}
		}
}
}



