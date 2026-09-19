package academy.controller;

import java.io.UnsupportedEncodingException;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import academy.model.ForgotPasswordToken;
import academy.model.User;
import academy.payload.BodyMessage;
import academy.payload.PasswordResetRequest;
import academy.repository.UserRepository;
import academy.interfaces.ForgotPasswordService;
import academy.auth.AuthUserService;

@RequestMapping("/v1/api/password")
@RestController
public class ForgotPasswordController {
	
	
	@Autowired
	private AuthUserService authUserService;
//	
     @Autowired
     private UserRepository userRepository;
     
     @Autowired
     private ForgotPasswordService forgotPasswordService;
	
	
	@PostMapping("/save-reset-password")
	public ResponseEntity<BodyMessage> saveResetPassword(@RequestBody PasswordResetRequest passwordResetRequest) {
		System.out.println("Password " + passwordResetRequest.getPassword());
		Optional<ForgotPasswordToken> forgotPasswordTokenGotten = forgotPasswordService.findByToken(passwordResetRequest.getResetToken());
		ForgotPasswordToken forgotPasswordToken = forgotPasswordTokenGotten.get();
		return forgotPasswordService.checkValidity(forgotPasswordToken, passwordResetRequest.getPassword());
	}
	
	
	@PostMapping("/save-reset-password-player")
	public ResponseEntity<BodyMessage> saveResetPasswordStudent(@RequestBody PasswordResetRequest passwordResetRequest) {
		System.out.println("Password " + passwordResetRequest.getPassword());
		System.out.println(authUserService.authenticatedPlayerId());
		forgotPasswordService.savePlayerNewPassword(authUserService.authenticatedPlayerId(), passwordResetRequest.getPassword());
		BodyMessage bodyMessage = new BodyMessage();
		bodyMessage.setMessage("Password Reset Successfully!");
		return ResponseEntity.status(HttpStatus.OK).body(bodyMessage);
	}
	
	
	@PostMapping("/save-reset-password-coach")
	public ResponseEntity<BodyMessage> saveResetPasswordTeacher(@RequestBody PasswordResetRequest passwordResetRequest) {
		System.out.println("Password " + passwordResetRequest.getPassword());
		forgotPasswordService.saveCoachNewPassword(authUserService.authenticatedCoachId(), passwordResetRequest.getPassword());
		BodyMessage bodyMessage = new BodyMessage();
		bodyMessage.setMessage("Password Reset Successfully!");
		return ResponseEntity.status(HttpStatus.OK).body(bodyMessage);
	}
	

	@PostMapping("/save-password-request/{email}")
	public ResponseEntity<BodyMessage> savePasswordRequest(@PathVariable String email) {
	
		User user = userRepository.findByEmail(email);
		if (user == null) {
			BodyMessage bodyMessage = new BodyMessage();
			 bodyMessage.setMessage("This Email is not registered!");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(bodyMessage);
		}
		
		ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken();
		forgotPasswordToken.setExpireTime(forgotPasswordService.expireTimeRange());
		forgotPasswordToken.setToken(forgotPasswordService.generateToken());
		forgotPasswordToken.setUsed(false);
		forgotPasswordToken.setAdmin(user.getAdmin());
		
		forgotPasswordService.saveForgotPasswordToken(forgotPasswordToken);		
		String emailLink = "https://jimtafootballacademy.com/password/password-reset?resetToken=" + forgotPasswordToken.getToken();
		
		try {
			forgotPasswordService.sendEmail(user.getEmail(), "Password Reset Link", emailLink);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			BodyMessage bodyMessage = new BodyMessage();
			 bodyMessage.setMessage("Error While Sending Email!");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(bodyMessage);
		}
		
		BodyMessage bodyMessage = new BodyMessage();
		bodyMessage.setMessage("Password Reset Link Send to your Email!");
		return ResponseEntity.status(HttpStatus.OK).body(bodyMessage);
	}
	
	
	@PostMapping("/save-reset-password-admin")
	public ResponseEntity<BodyMessage> saveResetPasswordAdmin(
	        @RequestBody PasswordResetRequest passwordResetRequest) {
	    forgotPasswordService.saveAdminNewPassword(
	            authUserService.authenticatedAdminId(),
	            passwordResetRequest.getPassword());
	    BodyMessage bodyMessage = new BodyMessage();
	    bodyMessage.setMessage("Password Reset Successfully!");
	    return ResponseEntity.status(HttpStatus.OK).body(bodyMessage);
	}
	

}
