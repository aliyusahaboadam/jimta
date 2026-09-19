package academy.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope = Admin.class)
@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstname;
	private String surname;
	private String lastname;
	private String email;
	private String phoneNumber;


	@OneToOne(mappedBy = "admin", fetch = FetchType.EAGER)
	private User user;

	// FIXED: was @ManyToOne on the Profile side only. Profile is now @OneToOne;
	// this is the inverse side so admin.getProfile() works and each admin can
	// only ever have one Profile row attached.
	@OneToOne(mappedBy = "admin", fetch = FetchType.EAGER)
	private Profile profile;


	@OneToMany(mappedBy = "admin",
	        cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
	        fetch = FetchType.LAZY)
	private Set<ForgotPasswordToken> forgotPasswordTokens = new HashSet<>();


	// Removal process
	public void removeAllForgotPasswordTokens() {
	    Set<ForgotPasswordToken> toRemove = new HashSet<>(this.forgotPasswordTokens);
	    toRemove.forEach(this::removeForgotPasswordToken);
	}

	public void removeForgotPasswordToken(ForgotPasswordToken token) {
	    this.forgotPasswordTokens.remove(token);
	    token.setAdminInternal(null);
	}

	void removeForgotPasswordTokenInternal(ForgotPasswordToken token) {
	    this.forgotPasswordTokens.remove(token);
	}

	void addForgotPasswordTokenInternal(ForgotPasswordToken token) {
	    this.forgotPasswordTokens.add(token);
	}


	// User ........................REMOVAL..............PROCESS

	public void removeUser() {
		if (this.user != null) {
			this.user.setAdminInternal(null);
			this.user = null;
		}
	}

	// Internal method to avoid recursion
	void setUserInternal(User user) {
		this.user = user;
	}


	// Profile ........................REMOVAL..............PROCESS

	public void removeProfile() {
		if (this.profile != null) {
			this.profile.setAdminInternal(null);
			this.profile = null;
		}
	}

	// Internal method to avoid recursion
	void setProfileInternal(Profile profile) {
		this.profile = profile;
	}


	private Admin(Builder builder) {
		this.id = builder.id;
		this.firstname = builder.firstname;
		this.surname = builder.surname;
		this.lastname = builder.lastname;
		this.email = builder.email;
		this.phoneNumber = builder.phoneNumber;
		this.user = builder.user;
		this.profile = builder.profile;
	}

	public Admin() {
		super();
	}

	public static class Builder {
		private long id;
		private String firstname;
		private String surname;
		private String lastname;
		private String email;
		private String phoneNumber;
		private User user;
		private Profile profile;

		public Builder setId(long id) {
			this.id = id;
			return this;
		}

		public Builder setFirstname(String firstname) {
			this.firstname = firstname;
			return this;
		}

		public Builder setSurname(String surname) {
			this.surname = surname;
			return this;
		}

		public Builder setLastname(String lastname) {
			this.lastname = lastname;
			return this;
		}

		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder setUser(User user) {
			this.user = user;
			return this;
		}

		public Builder setProfile(Profile profile) {
			this.profile = profile;
			return this;
		}

		public Builder setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		public Admin build() {
			return new Admin(this);
		}
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	// getter/setter
	public Set<ForgotPasswordToken> getForgotPasswordTokens() {
	    return forgotPasswordTokens;
	}

	public void setForgotPasswordTokens(Set<ForgotPasswordToken> forgotPasswordTokens) {
	    this.forgotPasswordTokens = forgotPasswordTokens;
	}



}
