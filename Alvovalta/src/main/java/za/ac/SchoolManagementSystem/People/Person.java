package People;

import java.util.Objects;
import java.util.Scanner;

public abstract class Person {

	private String fullNames;
	private long idNumber;
	private int age;
	private String sex;
	private String address;
	private String password;
	
	

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFullNames() {
		return fullNames;
	}

	public void setFullNames(String fullNames) {
		this.fullNames = fullNames;
	}

	public long getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(long idNumber) {
		this.idNumber = idNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Person [fullNames=" + fullNames + ", idNumber=" + idNumber + ", age=" + age + ", sex=" + sex
				+ ", address=" + address + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, age, fullNames, idNumber, password, sex);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(address, other.address) && age == other.age && Objects.equals(fullNames, other.fullNames)
				&& idNumber == other.idNumber && Objects.equals(password, other.password)
				&& Objects.equals(sex, other.sex);
	}

	public abstract void register(Scanner sc) throws Exception;

	public abstract boolean logIn(String fullName, String password, Person p, Scanner sc);

}
