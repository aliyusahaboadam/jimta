package academy.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import academy.model.Player.Builder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Player.class)
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // ---- Player core fields ----
    private String position;
    private Integer jerseyNumber;
    private Integer playerNumber;

    // ---- PlayerProfile fields (merged) ----
    private String nationality;
    private String preferredFoot;
    private Double heightCm;
    private Double weightKg;
    private String previousClub;
    private String photoUrl;

    // ---- Relationships ----
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne(mappedBy = "player",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true)
  private User user;

  @OneToOne(mappedBy = "player",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true)
  private Profile profile;

  @OneToMany(mappedBy = "player",
             cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
             orphanRemoval = true,
             fetch = FetchType.LAZY)
  private Set<PlayerMatchPerformance> performances = new HashSet<>();

    // ============================================================
    // PERFORMANCE REMOVAL PROCESS
    // ============================================================

    public void removeAllPerformances() {
        Set<PlayerMatchPerformance> performancesToRemove = new HashSet<>(this.performances);
        performancesToRemove.forEach(this::removePerformance);
    }

    public void removePerformance(PlayerMatchPerformance performance) {
        this.performances.remove(performance);
        performance.setPlayerInternal(null);
    }

    void removePerformanceInternal(PlayerMatchPerformance performance) {
        this.performances.remove(performance);
    }

    public void addPerformanceInternal(PlayerMatchPerformance performance) {
        this.performances.add(performance);
    }

    // ============================================================
    // TEAM REMOVAL PROCESS
    // ============================================================

    public void setTeamInternal(Team team) {
        this.team = team;
    }

    // ============================================================
    // USER REMOVAL PROCESS
    // ============================================================

    public void removeUser() {
        if (this.user != null) {
            this.user.setPlayerInternal(null);
            this.user = null;
        }
    }

    void setUserInternal(User user) {
        this.user = user;
    }

    // ============================================================
    // PROFILE REMOVAL PROCESS
    // ============================================================

    public void removeProfile() {
        if (this.profile != null) {
            this.profile.setPlayerInternal(null);
            this.profile = null;
        }
    }

    void setProfileInternal(Profile profile) {
        this.profile = profile;
    }

    // ============================================================
    // CONSTRUCTORS
    // ============================================================

    private Player(Builder builder) {
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
        this.team = builder.team;
        this.performances = builder.performances;
        this.user = builder.user;
        this.profile = builder.profile;
    }

    public Player() {
        super();
    }

    // ============================================================
    // BUILDER
    // ============================================================

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
        private Team team;
        private Set<PlayerMatchPerformance> performances = new HashSet<>();
        private User user;
        private Profile profile;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setPosition(String position) {
            this.position = position;
            return this;
        }

        public Builder setJerseyNumber(Integer jerseyNumber) {
            this.jerseyNumber = jerseyNumber;
            return this;
        }

        public Builder setPlayerNumber(Integer playerNumber) {
            this.playerNumber = playerNumber;
            return this;
        }

        public Builder setNationality(String nationality) {
            this.nationality = nationality;
            return this;
        }

        public Builder setPreferredFoot(String preferredFoot) {
            this.preferredFoot = preferredFoot;
            return this;
        }

        public Builder setHeightCm(Double heightCm) {
            this.heightCm = heightCm;
            return this;
        }

        public Builder setWeightKg(Double weightKg) {
            this.weightKg = weightKg;
            return this;
        }

        public Builder setPreviousClub(String previousClub) {
            this.previousClub = previousClub;
            return this;
        }

        public Builder setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
            return this;
        }

        public Builder setTeam(Team team) {
            this.team = team;
            return this;
        }

        public Builder setPerformances(Set<PlayerMatchPerformance> performances) {
            this.performances = performances;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setProfile(Profile profile) {
            this.profile = profile;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }

    // ============================================================
    // GETTERS / SETTERS
    // ============================================================

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(Integer jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public Integer getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(Integer playerNumber) {
        this.playerNumber = playerNumber;
    }

    // ---- Merged PlayerProfile getters/setters ----

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPreferredFoot() {
        return preferredFoot;
    }

    public void setPreferredFoot(String preferredFoot) {
        this.preferredFoot = preferredFoot;
    }

    public Double getHeightCm() {
        return heightCm;
    }

    public void setHeightCm(Double heightCm) {
        this.heightCm = heightCm;
    }

    public Double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(Double weightKg) {
        this.weightKg = weightKg;
    }

    public String getPreviousClub() {
        return previousClub;
    }

    public void setPreviousClub(String previousClub) {
        this.previousClub = previousClub;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    // ---- Relationships ----

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Set<PlayerMatchPerformance> getPerformances() {
        return performances;
    }

    public void setPerformances(Set<PlayerMatchPerformance> performances) {
        this.performances = performances;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
