package academy.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Activity.class)
@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 120)
    private String title;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private String activityDate;      // "YYYY-MM-DD"

    @Column(nullable = false, length = 200)
    private String location;

    // Up to four photos, stored as S3 keys.
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "activity_photos",
            joinColumns = @JoinColumn(name = "activity_id"))
    @Column(name = "photo_url", length = 500, nullable = false)
    @OrderColumn(name = "display_order")
    private List<String> photoUrls = new ArrayList<>();

    @Column(length = 500)
    private String facebookUrl;

    @Column(length = 500)
    private String instagramUrl;

    @Column(length = 500)
    private String tiktokUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;


    public Activity() {
        super();
    }


    private Activity(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.activityDate = builder.activityDate;
        this.location = builder.location;
        this.photoUrls = builder.photoUrls;
        this.facebookUrl = builder.facebookUrl;
        this.instagramUrl = builder.instagramUrl;
        this.tiktokUrl = builder.tiktokUrl;
        this.team = builder.team;
    }


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
        private Team team;

        public Builder setId(long id) { this.id = id; return this; }
        public Builder setTitle(String title) { this.title = title; return this; }
        public Builder setDescription(String description) { this.description = description; return this; }
        public Builder setActivityDate(String activityDate) { this.activityDate = activityDate; return this; }
        public Builder setLocation(String location) { this.location = location; return this; }
        public Builder setPhotoUrls(List<String> photoUrls) { this.photoUrls = photoUrls; return this; }
        public Builder setFacebookUrl(String facebookUrl) { this.facebookUrl = facebookUrl; return this; }
        public Builder setInstagramUrl(String instagramUrl) { this.instagramUrl = instagramUrl; return this; }
        public Builder setTiktokUrl(String tiktokUrl) { this.tiktokUrl = tiktokUrl; return this; }
        public Builder setTeam(Team team) { this.team = team; return this; }

        public Activity build() { return new Activity(this); }
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

    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }

}