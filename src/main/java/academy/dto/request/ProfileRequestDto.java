package academy.dto.request;

public class ProfileRequestDto {

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

    private ProfileRequestDto(Builder builder) {
        this.id = builder.id;
        this.firstname = builder.firstname;
        this.surname = builder.surname;
        this.lastname = builder.lastname;
        this.dateOfBirth = builder.dateOfBirth;
        this.gender = builder.gender;
        this.phoneNumber = builder.phoneNumber;
        this.playerId = builder.playerId;
        this.coachId = builder.coachId;
        this.adminId = builder.adminId;
    }

    public ProfileRequestDto() {}

    public static class Builder {
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

        public Builder setId(long id) { this.id = id; return this; }
        public Builder setFirstname(String firstname) { this.firstname = firstname; return this; }
        public Builder setSurname(String surname) { this.surname = surname; return this; }
        public Builder setLastname(String lastname) { this.lastname = lastname; return this; }
        public Builder setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; return this; }
        public Builder setGender(String gender) { this.gender = gender; return this; }
        public Builder setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
        public Builder setPlayerId(Long playerId) { this.playerId = playerId; return this; }
        public Builder setCoachId(Long coachId) { this.coachId = coachId; return this; }
        public Builder setAdminId(Long adminId) { this.adminId = adminId; return this; }

        public ProfileRequestDto build() { return new ProfileRequestDto(this); }
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