package academy.dto.request;

import java.util.HashSet;
import java.util.Set;

public class MatchRequestDto {

    private long id;
    private String matchDate;
    private String venue;
    private String status;
    private Integer homeScore;
    private Integer awayScore;
    private Long homeTeamId;
    private Long awayTeamId;
    private Set<Long> performanceIds = new HashSet<>();

    private MatchRequestDto(Builder builder) {
        this.id = builder.id;
        this.matchDate = builder.matchDate;
        this.venue = builder.venue;
        this.status = builder.status;
        this.homeScore = builder.homeScore;
        this.awayScore = builder.awayScore;
        this.homeTeamId = builder.homeTeamId;
        this.awayTeamId = builder.awayTeamId;
        this.performanceIds = builder.performanceIds;
    }

    public MatchRequestDto() {}

    public static class Builder {
        private long id;
        private String matchDate;
        private String venue;
        private String status;
        private Integer homeScore;
        private Integer awayScore;
        private Long homeTeamId;
        private Long awayTeamId;
        private Set<Long> performanceIds = new HashSet<>();

        public Builder setId(long id) { this.id = id; return this; }
        public Builder setMatchDate(String matchDate) { this.matchDate = matchDate; return this; }
        public Builder setVenue(String venue) { this.venue = venue; return this; }
        public Builder setStatus(String status) { this.status = status; return this; }
        public Builder setHomeScore(Integer homeScore) { this.homeScore = homeScore; return this; }
        public Builder setAwayScore(Integer awayScore) { this.awayScore = awayScore; return this; }
        public Builder setHomeTeamId(Long homeTeamId) { this.homeTeamId = homeTeamId; return this; }
        public Builder setAwayTeamId(Long awayTeamId) { this.awayTeamId = awayTeamId; return this; }
        public Builder setPerformanceIds(Set<Long> performanceIds) { this.performanceIds = performanceIds; return this; }

        public MatchRequestDto build() { return new MatchRequestDto(this); }
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getMatchDate() { return matchDate; }
    public void setMatchDate(String matchDate) { this.matchDate = matchDate; }

    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getHomeScore() { return homeScore; }
    public void setHomeScore(Integer homeScore) { this.homeScore = homeScore; }

    public Integer getAwayScore() { return awayScore; }
    public void setAwayScore(Integer awayScore) { this.awayScore = awayScore; }

    public Long getHomeTeamId() { return homeTeamId; }
    public void setHomeTeamId(Long homeTeamId) { this.homeTeamId = homeTeamId; }

    public Long getAwayTeamId() { return awayTeamId; }
    public void setAwayTeamId(Long awayTeamId) { this.awayTeamId = awayTeamId; }

    public Set<Long> getPerformanceIds() { return performanceIds; }
    public void setPerformanceIds(Set<Long> performanceIds) { this.performanceIds = performanceIds; }
}