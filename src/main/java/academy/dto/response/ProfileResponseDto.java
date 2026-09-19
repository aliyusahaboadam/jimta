package academy.dto.response;

public class ProfileResponseDto {

    private long id;
    private String firstname;
    private String surname;
    private String lastname;
    private String dateOfBirth;
    private String gender;
    private String phoneNumber;
    private Long playerId;
    private Long coachId;
    private Long adminId;

    public ProfileResponseDto() {}
    
    public ProfileResponseDto(long id, String firstname, String surname, String lastname,
            String dateOfBirth, String gender, String phoneNumber) {
this.id = id;
this.firstname = firstname;
this.surname = surname;
this.lastname = lastname;
this.dateOfBirth = dateOfBirth;
this.gender = gender;
this.phoneNumber = phoneNumber;
}

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public Long getPlayerId() { return playerId; }
    public void setPlayerId(Long playerId) { this.playerId = playerId; }

    public Long getCoachId() { return coachId; }
    public void setCoachId(Long coachId) { this.coachId = coachId; }

    public Long getAdminId() { return adminId; }
    public void setAdminId(Long adminId) { this.adminId = adminId; }
}