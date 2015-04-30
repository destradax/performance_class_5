package co.com.psl.elitemovie.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "application_user")
public class User implements Serializable {
	
	private static final long serialVersionUID = -541475594006936153L;
	
	@Id
	@SequenceGenerator(initialValue = 1, sequenceName = "db_user_sequence", name = "userSequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequence")
	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private boolean premiumUser;
	private boolean male;
	
	public User(){};
	
	public User(int id, String firstName, String lastName, int age,
			boolean premiumUser, boolean male) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.premiumUser = premiumUser;
		this.male = male;
	}

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