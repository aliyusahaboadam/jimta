package academy.interfaces;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import jakarta.mail.MessagingException;
import academy.model.ForgotPasswordToken;
import academy.payload.BodyMessage;

public interface ForgotPasswordService {
	
	Optional<ForgotPasswordToken> findByToken(String token);
	String generateToken();
	LocalDateTime expireTimeRange();
	void sendEmail(String to, String subject, String emailLink) throws MessagingException, UnsupportedEncodingException;
	String getEmailTemplate();
	void saveForgotPasswordToken(ForgotPasswordToken forgotPasswordToken);
    boolean isExpired(ForgotPasswordToken forgotPasswordToken);
    public ResponseEntity<BodyMessage> checkValidity(ForgotPasswordToken forgotPasswordToken, String password);
    
    void savePlayerNewPassword(Long playerId, String password);
    void saveCoachNewPassword(Long coachId, String password);
    void saveAdminNewPassword(Long adminId, String password);

}
