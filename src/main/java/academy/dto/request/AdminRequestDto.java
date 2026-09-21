package academy.dto.request;

import academy.model.Profile;

public class AdminRequestDto {

    private long id;
    private String firstname;
    private String surname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private Long userId;
    private Profile profile;
    
    private String password;

  

    private AdminRequestDto(Builder builder) {
        this.id = builder.id;
        this.firstname = builder.firstname;
        this.surname = builder.surname;
        this.lastname = builder.lastname;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.userId = builder.userId;
    }

    public AdminRequestDto() {}

    public static class Builder {
        private long id;
        private String firstname;
        private String surname;
        private String lastname;
        private String email;
        private String phoneNumber;
        private Long userId;

        public Builder setId(long id) { this.id = id; return this; }
        public Builder setFirstname(String firstname) { this.firstname = firstname; return this; }
        public Builder setSurname(String surname) { this.surname = surname; return this; }
        public Builder setLastname(String lastname) { this.lastname = lastname; return this; }
        public Builder setEmail(String email) { this.email = email; return this; }
        public Builder setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
        public Builder setUserId(Long userId) { this.userId = userId; return this; }

        public AdminRequestDto build() { return new AdminRequestDto(this); }
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
}