package academy.dto.response;

public class AdminResponseDto {

    private long id;
    private String firstname;
    private String surname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private Long userId;
    
    public AdminResponseDto(long id, String firstname, String surname,
            String lastname, String email, String phoneNumber) {
this.id = id;
this.firstname = firstname;
this.surname = surname;
this.lastname = lastname;
this.email = email;
this.phoneNumber = phoneNumber;
}

    public AdminResponseDto() {}

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
}