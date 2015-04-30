package co.com.psl.elitemovie.controller.dto;

public class UserDto {
	
	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private boolean premiumUser;
	private boolean male;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isPremiumUser() {
		return premiumUser;
	}
	public void setPremiumUser(boolean premiumUser) {
		this.premiumUser = premiumUser;
	}
	public boolean isMale() {
		return male;
	}
	public void setMale(boolean male) {
		this.male = male;
	}

}
