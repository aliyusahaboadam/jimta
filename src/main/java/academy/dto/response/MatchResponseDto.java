package academy.dto.response;

import java.util.HashSet;
import java.util.Set;

public class MatchResponseDto {

    private long id;
    private String matchDate;
    private String venue;
    private String status;
    private Integer homeScore;
    private Integer awayScore;
    private Long homeTeamId;
    private Long awayTeamId;
    private String homeTeamName;
    private String awayTeamName;
    
    private Set<Long> performanceIds = new HashSet<>();
    
		    public MatchResponseDto(long id, String matchDate, String venue, String status,
		            String homeTeamName, String awayTeamName,
		            Integer homeScore, Integer awayScore) {
		this.id = id;
		this.matchDate = matchDate;
		this.venue = venue;
		this.status = status;
		this.homeTeamName = homeTeamName;
		this.awayTeamName = awayTeamName;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
		}

    public MatchResponseDto() {}

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
    
    public String getHomeTeamName() { return homeTeamName; }
    public void setHomeTeamName(String homeTeamName) { this.homeTeamName = homeTeamName; }

    public String getAwayTeamName() { return awayTeamName; }
    public void setAwayTeamName(String awayTeamName) { this.awayTeamName = awayTeamName; }
}