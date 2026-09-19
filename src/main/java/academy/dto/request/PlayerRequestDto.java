package academy.dto.request;

import java.util.HashSet;
import java.util.Set;

import academy.model.Profile;
import academy.model.Team;
import academy.model.User;

public class PlayerRequestDto {

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
    private String email;
    private Team team;
    private Long teamId;
    private Long userId;
    private Profile profile;

   
    private Set<Long> performanceIds = new HashSet<>();

    private PlayerRequestDto(Builder builder) {
        this.id = builder.id;
        this.position = builder.position;
        this.jerseyNumber = builder.jerseyNumber;
        this.playerNumber = builder.playerNumber;
        this.nationality = builder.nationality;
        this.preferredFoot = builder.preferredFoot;
        this.heightCm = builder.heightCm;
        this.weightKg = builder.weightKg;
        this.previousClub = builder.previousClub;
        this.photoUrl = builder.photoUrl;
        this.performanceIds = builder.performanceIds;
    }

    public PlayerRequestDto() {}

    public static class Builder {
        private long id;
        private String position;
        private Integer jerseyNumber;
        private Integer playerNumber;
        private String nationality;
        private String preferredFoot;
        private Double heightCm;
        private Double weightKg;
        private String previousClub;
        private String photoUrl;
        private Long teamId;
        private Long userId;
        private Set<Long> performanceIds = new HashSet<>();

        public Builder setId(long id) { this.id = id; return this; }
        public Builder setPosition(String position) { this.position = position; return this; }
        public Builder setJerseyNumber(Integer jerseyNumber) { this.jerseyNumber = jerseyNumber; return this; }
        public Builder setPlayerNumber(Integer playerNumber) { this.playerNumber = playerNumber; return this; }
        public Builder setNationality(String nationality) { this.nationality = nationality; return this; }
        public Builder setPreferredFoot(String preferredFoot) { this.preferredFoot = preferredFoot; return this; }
        public Builder setHeightCm(Double heightCm) { this.heightCm = heightCm; return this; }
        public Builder setWeightKg(Double weightKg) { this.weightKg = weightKg; return this; }
        public Builder setPreviousClub(String previousClub) { this.previousClub = previousClub; return this; }
        public Builder setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; return this; }
        public Builder setTeamId(Long teamId) { this.teamId = teamId; return this; }
        public Builder setUserId(Long userId) { this.userId = userId; return this; }
        public Builder setPerformanceIds(Set<Long> performanceIds) { this.performanceIds = performanceIds; return this; }

        public PlayerRequestDto build() { return new PlayerRequestDto(this); }
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

    public Set<Long> getPerformanceIds() { return performanceIds; }
    public void setPerformanceIds(Set<Long> performanceIds) { this.performanceIds = performanceIds; }



	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	
	
	
    
    
}