package academy.dto.request;

import java.util.HashSet;
import java.util.Set;

import academy.model.Profile;
import academy.model.User;

public class CoachRequestDto {

    private long id;
    private String licenseNo;
    private String specialization;
    private Long userId;
    private Set<Long> teamIds = new HashSet<>();
    private Profile profile;
    private String email;

    

    private CoachRequestDto(Builder builder) {
        this.id = builder.id;
        this.licenseNo = builder.licenseNo;
        this.specialization = builder.specialization;
        this.userId = builder.userId;
        this.teamIds = builder.teamIds;
    }

    public CoachRequestDto() {}

    public static class Builder {
        private long id;
        private String licenseNo;
        private String specialization;
        private Long userId;
        private Set<Long> teamIds = new HashSet<>();

        public Builder setId(long id) { this.id = id; return this; }
        public Builder setLicenseNo(String licenseNo) { this.licenseNo = licenseNo; return this; }
        public Builder setSpecialization(String specialization) { this.specialization = specialization; return this; }
        public Builder setUserId(Long userId) { this.userId = userId; return this; }
        public Builder setTeamIds(Set<Long> teamIds) { this.teamIds = teamIds; return this; }

        public CoachRequestDto build() { return new CoachRequestDto(this); }
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getLicenseNo() { return licenseNo; }
    public void setLicenseNo(String licenseNo) { this.licenseNo = licenseNo; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Set<Long> getTeamIds() { return teamIds; }
    public void setTeamIds(Set<Long> teamIds) { this.teamIds = teamIds; }

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