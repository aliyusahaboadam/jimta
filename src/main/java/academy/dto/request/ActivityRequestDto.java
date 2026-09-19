package academy.dto.request;

import java.util.ArrayList;
import java.util.List;

public class ActivityRequestDto {

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


    private ActivityRequestDto(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.activityDate = builder.activityDate;
        this.location = builder.location;
        this.photoUrls = builder.photoUrls;
        this.facebookUrl = builder.facebookUrl;
        this.instagramUrl = builder.instagramUrl;
        this.tiktokUrl = builder.tiktokUrl;
        this.teamId = builder.teamId;
    }

    public ActivityRequestDto() {}


    public static class Builder {
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

        public Builder setId(long id) { this.id = id; return this; }
        public Builder setTitle(String title) { this.title = title; return this; }
        public Builder setDescription(String description) { this.description = description; return this; }
        public Builder setActivityDate(String activityDate) { this.activityDate = activityDate; return this; }
        public Builder setLocation(String location) { this.location = location; return this; }
        public Builder setPhotoUrls(List<String> photoUrls) { this.photoUrls = photoUrls; return this; }
        public Builder setFacebookUrl(String facebookUrl) { this.facebookUrl = facebookUrl; return this; }
        public Builder setInstagramUrl(String instagramUrl) { this.instagramUrl = instagramUrl; return this; }
        public Builder setTiktokUrl(String tiktokUrl) { this.tiktokUrl = tiktokUrl; return this; }
        public Builder setTeamId(Long teamId) { this.teamId = teamId; return this; }

        public ActivityRequestDto build() { return new ActivityRequestDto(this); }
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

}