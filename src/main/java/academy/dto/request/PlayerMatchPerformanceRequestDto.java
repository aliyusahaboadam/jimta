package academy.dto.request;

public class PlayerMatchPerformanceRequestDto {

    private long id;
    private Integer goals;
    private Integer assists;
    private Integer yellowCards;
    private Integer redCards;
    private Integer minutesPlayed;
    private Boolean started;
    private Long playerId;
    private Long matchId;

    private PlayerMatchPerformanceRequestDto(Builder builder) {
        this.id = builder.id;
        this.goals = builder.goals;
        this.assists = builder.assists;
        this.yellowCards = builder.yellowCards;
        this.redCards = builder.redCards;
        this.minutesPlayed = builder.minutesPlayed;
        this.started = builder.started;
        this.playerId = builder.playerId;
        this.matchId = builder.matchId;
    }

    public PlayerMatchPerformanceRequestDto() {}

    public static class Builder {
        private long id;
        private Integer goals;
        private Integer assists;
        private Integer yellowCards;
        private Integer redCards;
        private Integer minutesPlayed;
        private Boolean started;
        private Long playerId;
        private Long matchId;

        public Builder setId(long id) { this.id = id; return this; }
        public Builder setGoals(Integer goals) { this.goals = goals; return this; }
        public Builder setAssists(Integer assists) { this.assists = assists; return this; }
        public Builder setYellowCards(Integer yellowCards) { this.yellowCards = yellowCards; return this; }
        public Builder setRedCards(Integer redCards) { this.redCards = redCards; return this; }
        public Builder setMinutesPlayed(Integer minutesPlayed) { this.minutesPlayed = minutesPlayed; return this; }
        public Builder setStarted(Boolean started) { this.started = started; return this; }
        public Builder setPlayerId(Long playerId) { this.playerId = playerId; return this; }
        public Builder setMatchId(Long matchId) { this.matchId = matchId; return this; }

        public PlayerMatchPerformanceRequestDto build() { return new PlayerMatchPerformanceRequestDto(this); }
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
    public void setPlayerId(Long playerId) { this.playerId = playerId; }

    public Long getMatchId() { return matchId; }
    public void setMatchId(Long matchId) { this.matchId = matchId; }
}