package academy.dto.request;

import java.util.HashSet;
import java.util.Set;

public class TeamRequestDto {

    private long id;
    private String name;
    private String ageGroup;
    private String division;
    private Long coachId;
    private Set<Long> playerIds = new HashSet<>();
    private Set<Long> homeMatchIds = new HashSet<>();
    private Set<Long> awayMatchIds = new HashSet<>();

    private TeamRequestDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.ageGroup = builder.ageGroup;
        this.division = builder.division;
        this.coachId = builder.coachId;
        this.playerIds = builder.playerIds;
        this.homeMatchIds = builder.homeMatchIds;
        this.awayMatchIds = builder.awayMatchIds;
    }

    public TeamRequestDto() {}

    public static class Builder {
        private long id;
        private String name;
        private String ageGroup;
        private String division;
        private Long coachId;
        private Set<Long> playerIds = new HashSet<>();
        private Set<Long> homeMatchIds = new HashSet<>();
        private Set<Long> awayMatchIds = new HashSet<>();

        public Builder setId(long id) { this.id = id; return this; }
        public Builder setName(String name) { this.name = name; return this; }
        public Builder setAgeGroup(String ageGroup) { this.ageGroup = ageGroup; return this; }
        public Builder setDivision(String division) { this.division = division; return this; }
        public Builder setCoachId(Long coachId) { this.coachId = coachId; return this; }
        public Builder setPlayerIds(Set<Long> playerIds) { this.playerIds = playerIds; return this; }
        public Builder setHomeMatchIds(Set<Long> homeMatchIds) { this.homeMatchIds = homeMatchIds; return this; }
        public Builder setAwayMatchIds(Set<Long> awayMatchIds) { this.awayMatchIds = awayMatchIds; return this; }

        public TeamRequestDto build() { return new TeamRequestDto(this); }
    }

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
}