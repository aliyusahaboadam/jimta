package academy.dto.request;

public class TrainingRequestDto {

    private long id;
    private String title;
    private String description;
    private String trainingDate;
    private String startTime;
    private String endTime;
    private String venue;
    private Long teamId;
    private Long coachId;


    private TrainingRequestDto(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.trainingDate = builder.trainingDate;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.venue = builder.venue;
        this.teamId = builder.teamId;
        this.coachId = builder.coachId;
    }

    public TrainingRequestDto() {}


    public static class Builder {
        private long id;
        private String title;
        private String description;
        private String trainingDate;
        private String startTime;
        private String endTime;
        private String venue;
        private Long teamId;
        private Long coachId;

        public Builder setId(long id) { this.id = id; return this; }
        public Builder setTitle(String title) { this.title = title; return this; }
        public Builder setDescription(String description) { this.description = description; return this; }
        public Builder setTrainingDate(String trainingDate) { this.trainingDate = trainingDate; return this; }
        public Builder setStartTime(String startTime) { this.startTime = startTime; return this; }
        public Builder setEndTime(String endTime) { this.endTime = endTime; return this; }
        public Builder setVenue(String venue) { this.venue = venue; return this; }
        public Builder setTeamId(Long teamId) { this.teamId = teamId; return this; }
        public Builder setCoachId(Long coachId) { this.coachId = coachId; return this; }

        public TrainingRequestDto build() { return new TrainingRequestDto(this); }
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

    public Long getTeamId() { return teamId; }
    public void setTeamId(Long teamId) { this.teamId = teamId; }

    public Long getCoachId() { return coachId; }
    public void setCoachId(Long coachId) { this.coachId = coachId; }

}