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
import jakarta.persistence.ManyToOne;


@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope = PlayerMatchPerformance.class)
@Entity
public class PlayerMatchPerformance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Integer goals;
	private Integer assists;
	private Integer yellowCards;
	private Integer redCards;
	private Integer minutesPlayed;
	private Boolean started;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "player_id")
	private Player player;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "match_id")
	private Match match;


	//............................PLAYER REMOVAL PROCESS..................

	// Internal method to avoid recursion
	void setPlayerInternal(Player player) {
		this.player = player;
	}


	//............................MATCH REMOVAL PROCESS..................

	// Internal method to avoid recursion
	void setMatchInternal(Match match) {
		this.match = match;
	}


	private PlayerMatchPerformance(Builder builder) {
		this.id = builder.id;
		this.goals = builder.goals;
		this.assists = builder.assists;
		this.yellowCards = builder.yellowCards;
		this.redCards = builder.redCards;
		this.minutesPlayed = builder.minutesPlayed;
		this.started = builder.started;
		this.player = builder.player;
		this.match = builder.match;
	}

	public PlayerMatchPerformance() {
		super();
	}

	public static class Builder {
		private long id;
		private Integer goals;
		private Integer assists;
		private Integer yellowCards;
		private Integer redCards;
		private Integer minutesPlayed;
		private Boolean started;
		private Player player;
		private Match match;

		public Builder setId(long id) {
			this.id = id;
			return this;
		}

		public Builder setGoals(Integer goals) {
			this.goals = goals;
			return this;
		}

		public Builder setAssists(Integer assists) {
			this.assists = assists;
			return this;
		}

		public Builder setYellowCards(Integer yellowCards) {
			this.yellowCards = yellowCards;
			return this;
		}

		public Builder setRedCards(Integer redCards) {
			this.redCards = redCards;
			return this;
		}

		public Builder setMinutesPlayed(Integer minutesPlayed) {
			this.minutesPlayed = minutesPlayed;
			return this;
		}

		public Builder setStarted(Boolean started) {
			this.started = started;
			return this;
		}

		public Builder setPlayer(Player player) {
			this.player = player;
			return this;
		}

		public Builder setMatch(Match match) {
			this.match = match;
			return this;
		}

		public PlayerMatchPerformance build() {
			return new PlayerMatchPerformance(this);
		}
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getGoals() {
		return goals;
	}

	public void setGoals(Integer goals) {
		this.goals = goals;
	}

	public Integer getAssists() {
		return assists;
	}

	public void setAssists(Integer assists) {
		this.assists = assists;
	}

	public Integer getYellowCards() {
		return yellowCards;
	}

	public void setYellowCards(Integer yellowCards) {
		this.yellowCards = yellowCards;
	}

	public Integer getRedCards() {
		return redCards;
	}

	public void setRedCards(Integer redCards) {
		this.redCards = redCards;
	}

	public Integer getMinutesPlayed() {
		return minutesPlayed;
	}

	public void setMinutesPlayed(Integer minutesPlayed) {
		this.minutesPlayed = minutesPlayed;
	}

	public Boolean getStarted() {
		return started;
	}

	public void setStarted(Boolean started) {
		this.started = started;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

}
