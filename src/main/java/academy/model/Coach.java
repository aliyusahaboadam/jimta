package academy.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import academy.model.Coach.Builder;
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
		scope = Coach.class)
@Entity
public class Coach {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String licenseNo;
	private String specialization;

	@OneToOne(mappedBy = "coach",
	          fetch = FetchType.EAGER,
	          cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
	          orphanRemoval = true)
	private User user;

	@OneToOne(mappedBy = "coach",
	          fetch = FetchType.EAGER,
	          cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
	          orphanRemoval = true)
	private Profile profile;

	@OneToMany(mappedBy = "coach", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	private Set<Team> teams = new HashSet<>();


	// TEAM ........................REMOVAL..............PROCESS

	public void removeTeam(Team team) {
		this.teams.remove(team);
		team.setCoachInternal(null);
	}

	public void removeAllTeams() {
		Set<Team> teamsToRemove = new HashSet<>(this.teams);
		teamsToRemove.forEach(this::removeTeam);
	}

	public void addTeamInternal(Team team) {
		this.teams.add(team);
	}

	void removeTeamInternal(Team team) {
		this.teams.remove(team);
	}


	// User ........................REMOVAL..............PROCESS

	public void removeUser() {
		if (this.user != null) {
			this.user.setCoachInternal(null);
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
			this.profile.setCoachInternal(null);
			this.profile = null;
		}
	}

	// Internal method to avoid recursion
	void setProfileInternal(Profile profile) {
		this.profile = profile;
	}


	private Coach(Builder builder) {
		this.id = builder.id;
		this.licenseNo = builder.licenseNo;
		this.specialization = builder.specialization;
		this.teams = builder.teams;
		this.user = builder.user;
		this.profile = builder.profile;
	}

	public Coach() {
		super();
	}

	public static class Builder {
		private long id;
		private String licenseNo;
		private String specialization;
		private Set<Team> teams = new HashSet<>();
		private User user;
		private Profile profile;

		public Builder setId(long id) {
			this.id = id;
			return this;
		}

		public Builder setLicenseNo(String licenseNo) {
			this.licenseNo = licenseNo;
			return this;
		}

		public Builder setSpecialization(String specialization) {
			this.specialization = specialization;
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

		public Builder setTeams(Set<Team> teams) {
			this.teams = teams;
			return this;
		}

		public Coach build() {
			return new Coach(this);
		}
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
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

}
