package academy.dto.response;

import java.util.HashSet;
import java.util.Set;

public class TeamResponseDto {

    private long id;
    private String name;
    private String ageGroup;
    private String division;
    private Long coachId;
    private Set<Long> playerIds = new HashSet<>();
    private Set<Long> homeMatchIds = new HashSet<>();
    private Set<Long> awayMatchIds = new HashSet<>();
    private Integer playerCount;
    
    
 // Add fields:
  
    private String coachName;

    // Extend this constructor (list with player count):
    public TeamResponseDto(long id, String name, String ageGroup, String division,
                           Integer playerCount, Long coachId, String coachName) {
        this.id = id;
        this.name = name;
        this.ageGroup = ageGroup;
        this.division = division;
        this.playerCount = playerCount;
        this.coachId = coachId;
        this.coachName = coachName;
    }

 
  
    
    
    public TeamResponseDto(long id, String name, String ageGroup, String division) {
        this.id = id;
        this.name = name;
        this.ageGroup = ageGroup;
        this.division = division;
    }
    
    
    public TeamResponseDto(long id, String name, String ageGroup, String division, Integer playerCount) {
        this.id = id;
        this.name = name;
        this.ageGroup = ageGroup;
        this.division = division;
        this.playerCount = playerCount;
    }

    public TeamResponseDto() {}

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAgeGroup() { return ageGroup; }
    public void setAgeGroup(String ageGroup) { this.ageGroup = ageGroup; }

    public String getDivision() { return division; }
    public void setDivision(String division) { this.division = division; }

    public Long getCoachId() { return coachId; }
    public void setCoachId(Long coachId) { this.coachId = coachId; }

    public Set<Long> getPlayerIds() { return playerIds; }
    public void setPlayerIds(Set<Long> playerIds) { this.playerIds = playerIds; }

    public Set<Long> getHomeMatchIds() { return homeMatchIds; }
    public void setHomeMatchIds(Set<Long> homeMatchIds) { this.homeMatchIds = homeMatchIds; }

    public Set<Long> getAwayMatchIds() { return awayMatchIds; }
    public void setAwayMatchIds(Set<Long> awayMatchIds) { this.awayMatchIds = awayMatchIds; }


	public Integer getPlayerCount() {
		return playerCount;
	}


	public void setPlayerCount(Integer playerCount) {
		this.playerCount = playerCount;
	}
	
	  public String getCoachName() { return coachName; }
	    public void setCoachName(String coachName) { this.coachName = coachName; }
    
    
}