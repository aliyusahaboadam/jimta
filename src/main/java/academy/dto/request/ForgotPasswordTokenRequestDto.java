package academy.dto.request;

import java.time.LocalDateTime;

public class ForgotPasswordTokenRequestDto {

    private long id;
    private String token;
    private Long adminId;
    private LocalDateTime expireTime;
    private boolean isUsed;

    private ForgotPasswordTokenRequestDto(Builder builder) {
        this.id = builder.id;
        this.token = builder.token;
        this.adminId = builder.adminId;
        this.expireTime = builder.expireTime;
        this.isUsed = builder.isUsed;
    }

    public ForgotPasswordTokenRequestDto() {}

    public static class Builder {
        private long id;
        private String token;
        private Long adminId;
        private LocalDateTime expireTime;
        private boolean isUsed;

        public Builder setId(long id) { this.id = id; return this; }
        public Builder setToken(String token) { this.token = token; return this; }
        public Builder setAdminId(Long adminId) { this.adminId = adminId; return this; }
        public Builder setExpireTime(LocalDateTime expireTime) { this.expireTime = expireTime; return this; }
        public Builder setUsed(boolean isUsed) { this.isUsed = isUsed; return this; }

        public ForgotPasswordTokenRequestDto build() { return new ForgotPasswordTokenRequestDto(this); }
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Long getAdminId() { return adminId; }
    public void setAdminId(Long adminId) { this.adminId = adminId; }

    public LocalDateTime getExpireTime() { return expireTime; }
    public void setExpireTime(LocalDateTime expireTime) { this.expireTime = expireTime; }

    public boolean isUsed() { return isUsed; }
    public void setUsed(boolean isUsed) { this.isUsed = isUsed; }
}