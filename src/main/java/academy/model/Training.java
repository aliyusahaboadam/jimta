package academy.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Training.class)
@Entity
@Table(name = "trainings")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 120)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private String trainingDate;      // "YYYY-MM-DD"

    @Column(nullable = false, length = 10)
    private String startTime;         // "HH:mm" 24-hour

    @Column(length = 10)
    private String endTime;           // "HH:mm" optional

    @Column(nullable = false, length = 200)
    private String venue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coach_id")
    private Coach coach;              // optional override; falls back to team's coach


    public Training() {
        super();
    }


    private Training(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.trainingDate = builder.trainingDate;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.venue = builder.venue;
        this.team = builder.team;
        this.coach = builder.coach;
    }


    public static class Builder {
        private long id;
        private String title;
        private String description;
        private String trainingDate;
        private String startTime;
        private String endTime;
        private String venue;
        private Team team;
        private Coach coach;

        public Builder setId(long id) { this.id = id; return this; }
        public Builder setTitle(String title) { this.title = title; return this; }
        public Builder setDescription(String description) { this.description = description; return this; }
        public Builder setTrainingDate(String trainingDate) { this.trainingDate = trainingDate; return this; }
        public Builder setStartTime(String startTime) { this.startTime = startTime; return this; }
        public Builder setEndTime(String endTime) { this.endTime = endTime; return this; }
        public Builder setVenue(String venue) { this.venue = venue; return this; }
        public Builder setTeam(Team team) { this.team = team; return this; }
        public Builder setCoach(Coach coach) { this.coach = coach; return this; }

        public Training build() { return new Training(this); }
    }


    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getTrainingDate() { return trainingDate; }
    public void setTrainingDate(String trainingDate) { this.trainingDate = trainingDate; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }

    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }

    public Coach getCoach() { return coach; }
    public void setCoach(Coach coach) { this.coach = coach; }

}