package academy.dto.request;

public class UserRequestDto {

    private long id;
    private String username;
    private String password;
    private String email;
    private String role;
    private Long playerId;
    private Long adminId;

    private UserRequestDto(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
        this.email = builder.email;
        this.role = builder.role;
        this.playerId = builder.playerId;
        this.adminId = builder.adminId;
    }

    public UserRequestDto() {}

    public static class Builder {
        private long id;
        private String username;
        private String password;
        private String email;
        private String role;
        private Long playerId;
        private Long adminId;

        public Builder setId(long id) { this.id = id; return this; }
        public Builder setUsername(String username) { this.username = username; return this; }
        public Builder setPassword(String password) { this.password = password; return this; }
        public Builder setEmail(String email) { this.email = email; return this; }
        public Builder setRole(String role) { this.role = role; return this; }
        public Builder setPlayerId(Long playerId) { this.playerId = playerId; return this; }
        public Builder setAdminId(Long adminId) { this.adminId = adminId; return this; }

        public UserRequestDto build() { return new UserRequestDto(this); }
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Long getPlayerId() { return playerId; }
    public void setPlayerId(Long playerId) { this.playerId = playerId; }

    public Long getAdminId() { return adminId; }
    public void setAdminId(Long adminId) { this.adminId = adminId; }
}