package academy.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import academy.model.Profile.Builder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope = Profile.class)
@Entity
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstname;
	private String surname;
	private String lastname;
	private String dateOfBirth;
	private String gender;
	private String phoneNumber;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "player_id")
	private Player player;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coach_id")
	private Coach coach;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id")
	private Admin admin;


	//............................PLAYER REMOVAL PROCESS..................

	public void removePlayer() {
		if (this.player != null) {
			this.player.setProfileInternal(null);
			this.player = null;
		}
	}

	// Internal method to avoid recursion
	void setPlayerInternal(Player player) {
		this.player = player;
	}


	//............................COACH REMOVAL PROCESS..................

	public void removeCoach() {
		if (this.coach != null) {
			this.coach.setProfileInternal(null);
			this.coach = null;
		}
	}

	// Internal method to avoid recursion
	void setCoachInternal(Coach coach) {
		this.coach = coach;
	}


	//............................ADMIN REMOVAL PROCESS..................

	public void removeAdmin() {
		if (this.admin != null) {
			this.admin.setProfileInternal(null);
			this.admin = null;
		}
	}

	// Internal method to avoid recursion
	void setAdminInternal(Admin admin) {
		this.admin = admin;
	}


	private Profile(Builder builder) {
		this.id = builder.id;
		this.firstname = builder.firstname;
		this.surname = builder.surname;
		this.lastname = builder.lastname;
		this.dateOfBirth = builder.dateOfBirth;
		this.gender = builder.gender;
		this.player = builder.player;
		this.admin = builder.admin;
		this.coach = builder.coach;
		this.phoneNumber = builder.phoneNumber;
	}

	public Profile() {
		super();
	}

	public static class Builder {
		private long id;
		private String firstname;
		private String surname;
		private String lastname;
		private String dateOfBirth;
		private String gender;
		private String phoneNumber;
		private Player player;
		private Coach coach;
		private Admin admin;


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

		public Builder setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		public Builder setLastname(String lastname) {
			this.lastname = lastname;
			return this;
		}

		public Builder setDateOfBirth(String dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
			return this;
		}

		public Builder setGender(String gender) {
			this.gender = gender;
			return this;
		}

		public Builder setPlayer(Player player) {
			this.player = player;
			return this;
		}

		public Builder setCoach(Coach coach) {
			this.coach = coach;
			return this;
		}

		public Builder setAdmin(Admin admin) {
			this.admin = admin;
			return this;
		}

		public Profile build() {
			return new Profile(this);
		}
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
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


	public String getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public Coach getCoach() {
		return coach;
	}


	public void setCoach(Coach coach) {
		this.coach = coach;
	}


	public Admin getAdmin() {
		return admin;
	}


	public void setAdmin(Admin admin) {
		this.admin = admin;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
