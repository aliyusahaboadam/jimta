package academy.dto.response;

import java.util.ArrayList;
import java.util.List;

public class ActivityResponseDto {

    private long id;
    private String title;
    private String description;
    private String activityDate;
    private String location;
    private List<String> photoUrls = new ArrayList<>();
    private String facebookUrl;
    private String instagramUrl;
    private String tiktokUrl;

    private Long teamId;
    private String teamName;


    public ActivityResponseDto() {}


    public ActivityResponseDto(long id, String title, String description,
                               String activityDate, String location,
                               List<String> photoUrls,
                               String facebookUrl, String instagramUrl, String tiktokUrl,
                               Long teamId, String teamName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.activityDate = activityDate;
        this.location = location;
        this.photoUrls = photoUrls;
        this.facebookUrl = facebookUrl;
        this.instagramUrl = instagramUrl;
        this.tiktokUrl = tiktokUrl;
        this.teamId = teamId;
        this.teamName = teamName;
    }


    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getActivityDate() { return activityDate; }
    public void setActivityDate(String activityDate) { this.activityDate = activityDate; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public List<String> getPhotoUrls() { return photoUrls; }
    public void setPhotoUrls(List<String> photoUrls) { this.photoUrls = photoUrls; }

    public String getFacebookUrl() { return facebookUrl; }
    public void setFacebookUrl(String facebookUrl) { this.facebookUrl = facebookUrl; }

    public String getInstagramUrl() { return instagramUrl; }
    public void setInstagramUrl(String instagramUrl) { this.instagramUrl = instagramUrl; }

    public String getTiktokUrl() { return tiktokUrl; }
    public void setTiktokUrl(String tiktokUrl) { this.tiktokUrl = tiktokUrl; }

    public Long getTeamId() { return teamId; }
    public void setTeamId(Long teamId) { this.teamId = teamId; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

}