package academy.dto.response;

import java.time.LocalDateTime;

public class ForgotPasswordTokenResponseDto {

    private long id;
    private String token;
    private Long adminId;
    private LocalDateTime expireTime;
    private boolean isUsed;

    public ForgotPasswordTokenResponseDto() {}

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