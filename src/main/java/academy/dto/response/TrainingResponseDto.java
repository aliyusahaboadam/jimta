package academy.dto.response;

public class TrainingResponseDto {

    private long id;
    private String title;
    private String description;
    private String trainingDate;
    private String startTime;
    private String endTime;
    private String venue;

    // Flattened relationship info
    private Long teamId;
    private String teamName;
    private Long coachId;
    private String coachName;


    public TrainingResponseDto() {}


    public TrainingResponseDto(long id, String title, String description,
                               String trainingDate, String startTime, String endTime,
                               String venue,
                               Long teamId, String teamName,
                               Long coachId, String coachName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.trainingDate = trainingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.teamId = teamId;
        this.teamName = teamName;
        this.coachId = coachId;
        this.coachName = coachName;
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

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public Long getCoachId() { return coachId; }
    public void setCoachId(Long coachId) { this.coachId = coachId; }

    public String getCoachName() { return coachName; }
    public void setCoachName(String coachName) { this.coachName = coachName; }

}