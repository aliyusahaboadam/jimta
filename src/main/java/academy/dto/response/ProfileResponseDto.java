package academy.dto.response;
public class ProfileResponseDto {

    private long id;
    private String firstname;
    private String surname;
    private String lastname;
    private String dateOfBirth;
    private String gender;
    private String phoneNumber;
    private String username;
    private String email;

    // 7-arg — kept for backwards compatibility if referenced elsewhere
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

    // 9-arg — used by all three profile queries now
    public ProfileResponseDto(long id, String firstname, String surname, String lastname,
                              String dateOfBirth, String gender, String phoneNumber,
                              String username, String email) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.email = email;
    }

    // getters & setters
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
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}