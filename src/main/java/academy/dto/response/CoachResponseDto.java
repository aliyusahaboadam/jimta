package academy.dto.response;

import java.util.HashSet;
import java.util.Set;

public class CoachResponseDto {

    private long id;
    private String licenseNo;
    private String specialization;
    private Long userId;
    private Set<Long> teamIds = new HashSet<>();
 // Add these two fields near the top, alongside id/licenseNo/specialization:
    private String firstname;
    private String surname;

    // Add this constructor (JPQL constructor expressions need the args in order):
    public CoachResponseDto(long id, String licenseNo, String specialization,
                            String firstname, String surname) {
        this.id = id;
        this.licenseNo = licenseNo;
        this.specialization = specialization;
        this.firstname = firstname;
        this.surname = surname;
    }


    public CoachResponseDto() {}
    
    public CoachResponseDto(long id, String licenseNo, String specialization) {
        this.id = id;
        this.licenseNo = licenseNo;
        this.specialization = specialization;
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
    
    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

}