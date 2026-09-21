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
import jakarta.persistence.Table;

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope = Match.class)
@Entity
@Table(name = "matches")
public class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String matchDate;
	private String venue;
	private String status;
	private Integer homeScore;
	private Integer awayScore;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "home_team_id")
	private Team homeTeam;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "away_team_id")
	private Team awayTeam;

	@OneToMany(mappedBy = "match", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	private Set<PlayerMatchPerformance> performances = new HashSet<>();


	// PERFORMANCE ........................REMOVAL..............PROCESS

	public void removeAllPerformances() {
		Set<PlayerMatchPerformance> performancesToRemove = new HashSet<>(this.performances);
		performancesToRemove.forEach(this::removePerformance);
	}

	public void removePerformance(PlayerMatchPerformance performance) {
		this.performances.remove(performance);
		performance.setMatchInternal(null);
	}

	public void addPerformanceInternal(PlayerMatchPerformance performance) {
		this.performances.add(performance);
	}

	void removePerformanceInternal(PlayerMatchPerformance performance) {
		this.performances.remove(performance);
	}


	//............................HOME TEAM REMOVAL PROCESS..................

	// Internal method to avoid recursion
	public void setHomeTeamInternal(Team homeTeam) {
		this.homeTeam = homeTeam;
	}


	//............................AWAY TEAM REMOVAL PROCESS..................

	// Internal method to avoid recursion
	public void setAwayTeamInternal(Team awayTeam) {
		this.awayTeam = awayTeam;
	}


	//............................ACADEMY REMOVAL PROCESS..................

	// Internal method to avoid recursion
	


	private Match(Builder builder) {
		this.id = builder.id;
		this.matchDate = builder.matchDate;
		this.venue = builder.venue;
		this.status = builder.status;
		this.homeScore = builder.homeScore;
		this.awayScore = builder.awayScore;
		this.homeTeam = builder.homeTeam;
		this.awayTeam = builder.awayTeam;
		this.performances = builder.performances;
	}

	public Match() {
		super();
	}

	public static class Builder {
		private long id;
		private String matchDate;
		private String venue;
		private String status;
		private Integer homeScore;
		private Integer awayScore;
		private Team homeTeam;
		private Team awayTeam;
		private Set<PlayerMatchPerformance> performances = new HashSet<>();

		public Builder setId(long id) {
			this.id = id;
			return this;
		}

		public Builder setMatchDate(String matchDate) {
			this.matchDate = matchDate;
			return this;
		}

		public Builder setVenue(String venue) {
			this.venue = venue;
			return this;
		}

		public Builder setStatus(String status) {
			this.status = status;
			return this;
		}

		public Builder setHomeScore(Integer homeScore) {
			this.homeScore = homeScore;
			return this;
		}

		public Builder setAwayScore(Integer awayScore) {
			this.awayScore = awayScore;
			return this;
		}

		public Builder setHomeTeam(Team homeTeam) {
			this.homeTeam = homeTeam;
			return this;
		}

		public Builder setAwayTeam(Team awayTeam) {
			this.awayTeam = awayTeam;
			return this;
		}

	

		public Builder setPerformances(Set<PlayerMatchPerformance> performances) {
			this.performances = performances;
			return this;
		}

		public Match build() {
			return new Match(this);
		}
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(Integer homeScore) {
		this.homeScore = homeScore;
	}

	public Integer getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(Integer awayScore) {
		this.awayScore = awayScore;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}


	public Set<PlayerMatchPerformance> getPerformances() {
		return performances;
	}

	public void setPerformances(Set<PlayerMatchPerformance> performances) {
		this.performances = performances;
	}

}
