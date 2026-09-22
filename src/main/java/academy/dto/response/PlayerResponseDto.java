package academy.dto.response;

import java.util.HashSet;
import java.util.Set;

public class PlayerResponseDto {

    private long id;
    private String position;
    private Integer jerseyNumber;
    private Integer playerNumber;

    // Merged PlayerProfile fields
    private String nationality;
    private String preferredFoot;
    private Double heightCm;
    private Double weightKg;
    private String previousClub;
    private String photoUrl;
    
    private String firstname;
    private String surname;
    private String lastname;
    // + getters/setters

    private Long teamId;
    private Long userId;
    private Set<Long> performanceIds = new HashSet<>();
    
    private String teamAgeGroup;
    private String teamDivision;
    private String coachName;
    
    
 
    private String teamName;

    // add a constructor overload or extra args
    public PlayerResponseDto(long id, String firstname, String surname, String lastname,
                             String position, Integer jerseyNumber,
                             Long teamId, String teamName) {
        // ...
        this.teamId = teamId;
        this.teamName = teamName;
    }

    public PlayerResponseDto() {}
    
    public PlayerResponseDto(long id, String firstname, String surname, String lastname,
            String position, Integer jerseyNumber) {
this.id = id;
this.firstname = firstname;
this.surname = surname;
this.lastname = lastname;
this.position = position;
this.jerseyNumber = jerseyNumber;
}
    
    
    public PlayerResponseDto(long id, String firstname, String surname, String lastname,
            String position, Integer jerseyNumber,
            String nationality, String preferredFoot,
            Double heightCm, Double weightKg,
            Integer playerNumber, String previousClub,
            Long teamId, String teamName) {
this.id = id;
this.firstname = firstname;
this.surname = surname;
this.lastname = lastname;
this.position = position;
this.jerseyNumber = jerseyNumber;
this.nationality = nationality;
this.preferredFoot = preferredFoot;
this.heightCm = heightCm;
this.weightKg = weightKg;
this.playerNumber = playerNumber;
this.previousClub = previousClub;
this.teamId = teamId;
this.teamName = teamName;
}
    
    
    public PlayerResponseDto(long id, String firstname, String surname, String lastname,
            String position, Integer jerseyNumber,
            String nationality, String preferredFoot,
            Double heightCm, Double weightKg,
            Integer playerNumber, String previousClub,
            String photoUrl,
            Long teamId, String teamName) {
this.id = id;
this.firstname = firstname;
this.surname = surname;
this.lastname = lastname;
this.position = position;
this.jerseyNumber = jerseyNumber;
this.nationality = nationality;
this.preferredFoot = preferredFoot;
this.heightCm = heightCm;
this.weightKg = weightKg;
this.playerNumber = playerNumber;
this.previousClub = previousClub;
this.photoUrl = photoUrl;
this.teamId = teamId;
this.teamName = teamName;
}
    
    public PlayerResponseDto(long id, String firstname, String surname, String lastname,
            String position, Integer jerseyNumber,
            String nationality, String preferredFoot,
            Double heightCm, Double weightKg,
            Integer playerNumber, String previousClub,
            String photoUrl,
            Long teamId, String teamName,
            String teamAgeGroup, String teamDivision,
            String coachName) {
this.id = id;
this.firstname = firstname;
this.surname = surname;
this.lastname = lastname;
this.position = position;
this.jerseyNumber = jerseyNumber;
this.nationality = nationality;
this.preferredFoot = preferredFoot;
this.heightCm = heightCm;
this.weightKg = weightKg;
this.playerNumber = playerNumber;
this.previousClub = previousClub;
this.photoUrl = photoUrl;
this.teamId = teamId;
this.teamName = teamName;
this.teamAgeGroup = teamAgeGroup;
this.teamDivision = teamDivision;
this.coachName = coachName;
}
    
    public PlayerResponseDto(long id, String firstname, String surname, String lastname,
            String position, Integer jerseyNumber,
            String nationality, String preferredFoot,
            Double heightCm, Double weightKg,
            Integer playerNumber, String previousClub) {
this.id = id;
this.firstname = firstname;
this.surname = surname;
this.lastname = lastname;
this.position = position;
this.jerseyNumber = jerseyNumber;
this.nationality = nationality;
this.preferredFoot = preferredFoot;
this.heightCm = heightCm;
this.weightKg = weightKg;
this.playerNumber = playerNumber;
this.previousClub = previousClub;
}
    
    public PlayerResponseDto(long id, String firstname, String surname, String lastname,
            String position, Integer jerseyNumber,
            String nationality, String preferredFoot,
            Double heightCm, Double weightKg,
            Integer playerNumber, String previousClub,
            String photoUrl) {
    	this.id = id;
    	this.firstname = firstname;
    	this.surname = surname;
    	this.lastname = lastname;
    	this.position = position;
    	this.jerseyNumber = jerseyNumber;
    	this.nationality = nationality;
    	this.preferredFoot = preferredFoot;
    	this.heightCm = heightCm;
    	this.weightKg = weightKg;
    	this.playerNumber = playerNumber;
    	this.previousClub = previousClub;
this.photoUrl = photoUrl;
}
    
    
    
    
    
    public PlayerResponseDto(long id, String firstname, String surname, String lastname,
            String position, Integer jerseyNumber,
            String nationality, String preferredFoot,
            Double heightCm, Double weightKg) {
this.id = id;
this.firstname = firstname;
this.surname = surname;
this.lastname = lastname;
this.position = position;
this.jerseyNumber = jerseyNumber;
this.nationality = nationality;
this.preferredFoot = preferredFoot;
this.heightCm = heightCm;
this.weightKg = weightKg;
}

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public Integer getJerseyNumber() { return jerseyNumber; }
    public void setJerseyNumber(Integer jerseyNumber) { this.jerseyNumber = jerseyNumber; }

    public Integer getPlayerNumber() { return playerNumber; }
    public void setPlayerNumber(Integer playerNumber) { this.playerNumber = playerNumber; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getPreferredFoot() { return preferredFoot; }
    public void setPreferredFoot(String preferredFoot) { this.preferredFoot = preferredFoot; }

    public Double getHeightCm() { return heightCm; }
    public void setHeightCm(Double heightCm) { this.heightCm = heightCm; }

    public Double getWeightKg() { return weightKg; }
    public void setWeightKg(Double weightKg) { this.weightKg = weightKg; }

    public String getPreviousClub() { return previousClub; }
    public void setPreviousClub(String previousClub) { this.previousClub = previousClub; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public Long getTeamId() { return teamId; }
    public void setTeamId(Long teamId) { this.teamId = teamId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Set<Long> getPerformanceIds() { return performanceIds; }
    public void setPerformanceIds(Set<Long> performanceIds) { this.performanceIds = performanceIds; }

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

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamAgeGroup() {
		return teamAgeGroup;
	}

	public void setTeamAgeGroup(String teamAgeGroup) {
		this.teamAgeGroup = teamAgeGroup;
	}

	public String getTeamDivision() {
		return teamDivision;
	}

	public void setTeamDivision(String teamDivision) {
		this.teamDivision = teamDivision;
	}

	public String getCoachName() {
		return coachName;
	}

	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	
	
	
	
    
    
}