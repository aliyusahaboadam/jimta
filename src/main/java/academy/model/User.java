package academy.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import academy.model.User.Builder;

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope = User.class)
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private String password;
	private String email;
	private String role;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "player_id")
	private Player player;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id")
	private Admin admin;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coach_id")
	private Coach coach;


	//............................PLAYER REMOVAL PROCESS..................

	public void removePlayer() {
		if (this.player != null) {
			this.player.setUserInternal(null);
			this.player = null;
		}
	}

	// Internal method to avoid recursion
	public void setPlayerInternal(Player player) {
		this.player = player;
	}


	//............................ADMIN REMOVAL PROCESS..................

	public void removeAdmin() {
		if (this.admin != null) {
			this.admin.setUserInternal(null);
			this.admin = null;
		}
	}

	// Internal method to avoid recursion
	public void setAdminInternal(Admin admin) {
		this.admin = admin;
	}


	//............................COACH REMOVAL PROCESS..................

	public void removeCoach() {
		if (this.coach != null) {
			this.coach.setUserInternal(null);
			this.coach = null;
		}
	}

	// Internal method to avoid recursion
	public void setCoachInternal(Coach coach) {
		this.coach = coach;
	}


	private User(Builder builder) {
		this.id = builder.id;
		this.username = builder.username;
		this.password = builder.password;
		this.email = builder.email;
		this.role = builder.role;
		this.player = builder.player;
		this.admin = builder.admin;
		this.coach = builder.coach;
	}

	public User() {
		super();
	}

	public static class Builder {
		private long id;
		private String username;
		private String password;
		private String email;
		private String role;
		private Player player;
		private Admin admin;
		private Coach coach;

		public Builder setId(long id) {
			this.id = id;
			return this;
		}

		public Builder setUsername(String username) {
			this.username = username;
			return this;
		}

		public Builder setPassword(String password) {
			this.password = password;
			return this;
		}

		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder setRole(String role) {
			this.role = role;
			return this;
		}

		public Builder setPlayer(Player player) {
			this.player = player;
			return this;
		}

		public Builder setAdmin(Admin admin) {
			this.admin = admin;
			return this;
		}

		public Builder setCoach(Coach coach) {
			this.coach = coach;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

}
