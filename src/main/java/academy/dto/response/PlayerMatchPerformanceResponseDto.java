package academy.dto.response;

public class PlayerMatchPerformanceResponseDto {

    private long id;
    private Integer goals;
    private Integer assists;
    private Integer yellowCards;
    private Integer redCards;
    private Integer minutesPlayed;
    private Boolean started;
    private Long playerId;
    private Long matchId;
    private String firstname;
    private String surname;
    private Long teamId;

    public PlayerMatchPerformanceResponseDto() {}
    
    
    public PlayerMatchPerformanceResponseDto(
            Long id,
            Long playerId,
            Long teamId,
            String firstname,
            String surname,
            Integer goals,
            Integer assists,
            Integer yellowCards,
            Integer redCards,
            Integer minutesPlayed,
            Boolean started) {
        this.id = id;
        this.playerId = playerId;
        this.teamId = teamId;
        this.firstname = firstname;
        this.surname = surname;
        this.goals = goals;
        this.assists = assists;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.minutesPlayed = minutesPlayed;
        this.started = started;
    }
    
    public PlayerMatchPerformanceResponseDto(long id, String firstname, String surname,
            Integer goals, Integer assists,
            Integer yellowCards, Integer redCards,
            Integer minutesPlayed, Boolean started) {
this.id = id;
this.firstname = firstname;
this.surname = surname;
this.goals = goals;
this.assists = assists;
this.yellowCards = yellowCards;
this.redCards = redCards;
this.minutesPlayed = minutesPlayed;
this.started = started;
}
    
    
    public PlayerMatchPerformanceResponseDto(Long goals, Long assists,
            Long yellowCards, Long redCards,
            Long minutesPlayed) {
this.goals = goals == null ? null : goals.intValue();
this.assists = assists == null ? null : assists.intValue();
this.yellowCards = yellowCards == null ? null : yellowCards.intValue();
this.redCards = redCards == null ? null : redCards.intValue();
this.minutesPlayed = minutesPlayed == null ? null : minutesPlayed.intValue();
}

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Integer getGoals() { return goals; }
    public void setGoals(Integer goals) { this.goals = goals; }

    public Integer getAssists() { return assists; }
    public void setAssists(Integer assists) { this.assists = assists; }

    public Integer getYellowCards() { return yellowCards; }
    public void setYellowCards(Integer yellowCards) { this.yellowCards = yellowCards; }

    public Integer getRedCards() { return redCards; }
    public void setRedCards(Integer redCards) { this.redCards = redCards; }

    public Integer getMinutesPlayed() { return minutesPlayed; }
    public void setMinutesPlayed(Integer minutesPlayed) { this.minutesPlayed = minutesPlayed; }

    public Boolean getStarted() { return started; }
    public void setStarted(Boolean started) { this.started = started; }

    public Long getPlayerId() { return playerId; }

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
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

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	
	
    
    
}