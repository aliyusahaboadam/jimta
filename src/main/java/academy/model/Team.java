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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import academy.model.Team.Builder;

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope = Team.class)
@Entity
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String ageGroup;
	private String division;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "coach_id")
	private Coach coach;

	@OneToMany(mappedBy = "team", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	private Set<Player> players = new HashSet<>();

	@OneToMany(mappedBy = "homeTeam", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	private Set<Match> homeMatches = new HashSet<>();

	@OneToMany(mappedBy = "awayTeam", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	private Set<Match> awayMatches = new HashSet<>();


	// PLAYER ........................REMOVAL..............PROCESS

	public void removePlayer(Player player) {
		this.players.remove(player);
		player.setTeamInternal(null);
	}

	public void removeAllPlayers() {
		Set<Player> playersToRemove = new HashSet<>(this.players);
		playersToRemove.forEach(this::removePlayer);
	}

	public void addPlayerInternal(Player player) {
		this.players.add(player);
	}

	void removePlayerInternal(Player player) {
		this.players.remove(player);
	}


	// HOME MATCH ........................REMOVAL..............PROCESS

	public void removeHomeMatch(Match match) {
		this.homeMatches.remove(match);
		match.setHomeTeamInternal(null);
	}

	public void addHomeMatchInternal(Match match) {
		this.homeMatches.add(match);
	}

	void removeHomeMatchInternal(Match match) {
		this.homeMatches.remove(match);
	}


	// AWAY MATCH ........................REMOVAL..............PROCESS

	public void removeAwayMatch(Match match) {
		this.awayMatches.remove(match);
		match.setAwayTeamInternal(null);
	}

	public void addAwayMatchInternal(Match match) {
		this.awayMatches.add(match);
	}

	void removeAwayMatchInternal(Match match) {
		this.awayMatches.remove(match);
	}


	//............................ACADEMY REMOVAL PROCESS..................

	// Internal method to avoid recursion



	//............................COACH REMOVAL PROCESS..................

	// Internal method to avoid recursion
	void setCoachInternal(Coach coach) {
		this.coach = coach;
	}


	private Team(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.ageGroup = builder.ageGroup;
		this.division = builder.division;
		this.coach = builder.coach;
		this.players = builder.players;
		this.homeMatches = builder.homeMatches;
		this.awayMatches = builder.awayMatches;
	}

	public Team() {
		super();
	}

	public static class Builder {
		private long id;
		private String name;
		private String ageGroup;
		private String division;
		private Coach coach;
		private Set<Player> players = new HashSet<>();
		private Set<Match> homeMatches = new HashSet<>();
		private Set<Match> awayMatches = new HashSet<>();

		public Builder setId(long id) {
			this.id = id;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setAgeGroup(String ageGroup) {
			this.ageGroup = ageGroup;
			return this;
		}

		public Builder setDivision(String division) {
			this.division = division;
			return this;
		}

		

		public Builder setCoach(Coach coach) {
			this.coach = coach;
			return this;
		}

		public Builder setPlayers(Set<Player> players) {
			this.players = players;
			return this;
		}

		public Builder setHomeMatches(Set<Match> homeMatches) {
			this.homeMatches = homeMatches;
			return this;
		}

		public Builder setAwayMatches(Set<Match> awayMatches) {
			this.awayMatches = awayMatches;
			return this;
		}

		public Team build() {
			return new Team(this);
		}
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	public Set<Match> getHomeMatches() {
		return homeMatches;
	}

	public void setHomeMatches(Set<Match> homeMatches) {
		this.homeMatches = homeMatches;
	}

	public Set<Match> getAwayMatches() {
		return awayMatches;
	}

	public void setAwayMatches(Set<Match> awayMatches) {
		this.awayMatches = awayMatches;
	}

}
