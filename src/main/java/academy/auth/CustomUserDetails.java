package academy.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import academy.model.Admin;
import academy.model.Coach;
import academy.model.Player;
import academy.model.Profile;
import academy.model.User;

public class CustomUserDetails implements UserDetails {

    private final long id;
    private final String username;
    private final String password;
    private final String email;
    private final String role;

    private final String firstname;
    private final String surname;
    private final String lastname;
    private final String phoneNumber;

    private final Long playerId;
    private final Long coachId;
    private final Long adminId;

    private final Collection<? extends GrantedAuthority> authorities;

    // ============================================================
    // PLAYER CONSTRUCTOR
    // ============================================================
    public CustomUserDetails(Player player,
                             Collection<? extends GrantedAuthority> authorities) {
        User user = player.getUser();
        Profile profile = null; // if Profile exists for player, fetch it via repo before calling

        this.id          = user.getId();
        this.username    = user.getUsername();
        this.password    = user.getPassword();
        this.email       = user.getEmail();
        this.role        = user.getRole();
        this.authorities = authorities;

        this.firstname   = profile != null ? profile.getFirstname() : null;
        this.surname     = profile != null ? profile.getSurname()   : null;
        this.lastname    = profile != null ? profile.getLastname()  : null;
        this.phoneNumber = profile != null ? profile.getPhoneNumber(): null;

        this.playerId    = player.getId();
        this.coachId     = null;
        this.adminId     = null;
    }

    // ============================================================
    // COACH CONSTRUCTOR
    // ============================================================
    public CustomUserDetails(Coach coach,
                             Collection<? extends GrantedAuthority> authorities) {
        User user = coach.getUser();

        this.id          = user.getId();
        this.username    = user.getUsername();
        this.password    = user.getPassword();
        this.email       = user.getEmail();
        this.role        = user.getRole();
        this.authorities = authorities;

        // Coach entity has no firstname/surname/phone — those live on Profile
        this.firstname   = null;
        this.surname     = null;
        this.lastname    = null;
        this.phoneNumber = null;

        this.playerId    = null;
        this.coachId     = coach.getId();
        this.adminId     = null;
    }

    // ============================================================
    // ADMIN CONSTRUCTOR
    // ============================================================
    public CustomUserDetails(Admin admin,
                             Collection<? extends GrantedAuthority> authorities) {
        User user = admin.getUser();

        this.id          = user.getId();
        this.username    = user.getUsername();
        this.password    = user.getPassword();
        this.email       = user.getEmail();
        this.role        = user.getRole();
        this.authorities = authorities;

        this.firstname   = admin.getFirstname();
        this.surname     = admin.getSurname();
        this.lastname    = admin.getLastname();
        this.phoneNumber = admin.getPhoneNumber();

        this.playerId    = null;
        this.coachId     = null;
        this.adminId     = admin.getId();
    }

    // ============================================================
    // UserDetails contract
    // ============================================================

    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }
    @Override public String getPassword()  { return password; }
    @Override public String getUsername()  { return username; }

    @Override public boolean isAccountNonExpired()     { return true; }
    @Override public boolean isAccountNonLocked()      { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled()               { return true; }

    // ============================================================
    // Getters
    // ============================================================

    public long getId()             { return id; }
    public String getEmail()        { return email; }
    public String getRole()         { return role; }

    public String getFirstname()    { return firstname; }
    public String getSurname()      { return surname; }
    public String getLastname()     { return lastname; }
    public String getPhoneNumber()  { return phoneNumber; }

    public Long getPlayerId()       { return playerId; }
    public Long getCoachId()        { return coachId; }
    public Long getAdminId()        { return adminId; }
}