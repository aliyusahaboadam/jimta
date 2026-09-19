package academy.service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.FetchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.repository.AdminRepository;
import academy.repository.CoachRepository;
import academy.repository.ForgotPasswordTokenRepository;
import academy.repository.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import academy.model.ForgotPasswordToken;
import academy.model.Admin;
import academy.model.Coach;
import academy.model.Player;
import academy.model.User;
import academy.payload.BodyMessage;
import academy.repository.UserRepository;
import academy.interfaces.ForgotPasswordService;


@Transactional
@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {
    
	@Autowired
	private ForgotPasswordTokenRepository forgotPasswordRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private CoachRepository coachRepository;
	
    @Autowired
	private JavaMailSender javaMailSender;
     
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
	private AdminRepository adminRepository;
    

	
	private final int MINUTES = 20;
	
	
	
	@Override
	public Optional<ForgotPasswordToken> findByToken(String token) {
		// TODO Auto-generated method stub
		return forgotPasswordRepository.findByToken(token);
	}

	@Override
	public String generateToken() {
		// TODO Auto-generated method stub
		return UUID.randomUUID().toString();
	}

	@Override
	public LocalDateTime expireTimeRange() {
		// TODO Auto-generated method stub
		return LocalDateTime.now().plusMinutes(MINUTES);
	}


	@Override
	public void sendEmail(String to, String subject, String emailLink)
	        throws MessagingException, UnsupportedEncodingException {

	    MimeMessage message = javaMailSender.createMimeMessage();

	    MimeMessageHelper helper =
	            new MimeMessageHelper(message, true, "UTF-8");

	    String emailContent =
	            getEmailTemplate().replace("{{LINK}}", emailLink);

	    helper.setText(emailContent, true);

	    helper.setFrom(
	            "miqwiitechnologies@gmail.com",
	            "Jimta Football Academy"
	    );

	    helper.setSubject(subject);
	    helper.setTo(to);

	    javaMailSender.send(message);
	}


	
	@Override
	public String getEmailTemplate() {

	    return "<!DOCTYPE html>\r\n"
	            + "<html>\r\n"
	            + "<head>\r\n"
	            + "  <meta charset=\"utf-8\">\r\n"
	            + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
	            + "  <title>Jimta Football Academy - Password Reset</title>\r\n"
	            + "  <style>\r\n"
	            + "    body {\r\n"
	            + "      font-family: Arial, sans-serif;\r\n"
	            + "      background-color: #f7f7f9;\r\n"
	            + "      margin: 0;\r\n"
	            + "      padding: 20px;\r\n"
	            + "      color: #9a99ac;\r\n"
	            + "    }\r\n"
	            + "    .container {\r\n"
	            + "      max-width: 600px;\r\n"
	            + "      margin: 0 auto;\r\n"
	            + "      background-color: #ffffff;\r\n"
	            + "      border-radius: 10px;\r\n"
	            + "      overflow: hidden;\r\n"
	            + "      box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);\r\n"
	            + "    }\r\n"
	            + "    .header {\r\n"
	            + "      background: linear-gradient(135deg, #d71b3b 0%, #a9142d 100%);\r\n"
	            + "      color: #ffffff;\r\n"
	            + "      padding: 30px;\r\n"
	            + "      text-align: center;\r\n"
	            + "    }\r\n"
	            + "    .header h1 {\r\n"
	            + "      margin: 0;\r\n"
	            + "      font-size: 24px;\r\n"
	            + "      font-weight: 700;\r\n"
	            + "    }\r\n"
	            + "    .icon {\r\n"
	            + "      display: flex;\r\n"
	            + "      align-items: center;\r\n"
	            + "      justify-content: center;\r\n"
	            + "      margin: 0 auto 20px;\r\n"
	            + "    }\r\n"
	            + "    .company-logo {\r\n"
	            + "      width: 100px;\r\n"
	            + "      height: 100px;\r\n"
	            + "      object-fit: contain;\r\n"
	            + "      background: #ffffff;\r\n"
	            + "      border-radius: 10px;\r\n"
	            + "      padding: 5px;\r\n"
	            + "    }\r\n"
	            + "    .content {\r\n"
	            + "      padding: 40px 30px;\r\n"
	            + "      text-align: center;\r\n"
	            + "    }\r\n"
	            + "    .content h2 {\r\n"
	            + "      color: #d71b3b;\r\n"
	            + "      margin-bottom: 15px;\r\n"
	            + "      font-size: 20px;\r\n"
	            + "    }\r\n"
	            + "    .highlight {\r\n"
	            + "      color: #d71b3b;\r\n"
	            + "      font-weight: bold;\r\n"
	            + "    }\r\n"
	            + "    .info {\r\n"
	            + "      color: #9a99ac;\r\n"
	            + "      margin: 20px 0;\r\n"
	            + "      line-height: 1.6;\r\n"
	            + "      word-break: break-word;\r\n"
	            + "    }\r\n"
	            + "    .link-box {\r\n"
	            + "      background-color: #faf0f2;\r\n"
	            + "      border: 2px dashed #d71b3b;\r\n"
	            + "      border-radius: 8px;\r\n"
	            + "      padding: 18px;\r\n"
	            + "      margin: 20px 0;\r\n"
	            + "      color: #3D52A0;\r\n"
	            + "      font-weight: 600;\r\n"
	            + "      word-break: break-all;\r\n"
	            + "    }\r\n"
	            + "    .warning-box {\r\n"
	            + "      background: linear-gradient(135deg, #fff3f5 0%, #f5f6ff 100%);\r\n"
	            + "      border: 1px solid #d71b3b;\r\n"
	            + "      border-radius: 8px;\r\n"
	            + "      padding: 15px;\r\n"
	            + "      margin: 20px 0;\r\n"
	            + "      color: #3D52A0;\r\n"
	            + "      font-weight: 500;\r\n"
	            + "    }\r\n"
	            + "    .button-style {\r\n"
	            + "      background: linear-gradient(135deg, #d71b3b 0%, #b91632 100%);\r\n"
	            + "      color: #ffffff !important;\r\n"
	            + "      padding: 13px 28px;\r\n"
	            + "      border-radius: 25px;\r\n"
	            + "      text-decoration: none;\r\n"
	            + "      display: inline-block;\r\n"
	            + "      margin: 10px 0;\r\n"
	            + "      font-weight: bold;\r\n"
	            + "      box-shadow: 0 4px 15px rgba(215, 27, 59, 0.3);\r\n"
	            + "    }\r\n"
	            + "    .footer {\r\n"
	            + "      background-color: #f5f5f7;\r\n"
	            + "      padding: 20px;\r\n"
	            + "      text-align: center;\r\n"
	            + "      color: #9a99ac;\r\n"
	            + "      font-size: 12px;\r\n"
	            + "    }\r\n"
	            + "    .footer p {\r\n"
	            + "      margin: 5px 0;\r\n"
	            + "    }\r\n"
	            + "    .footer .copyright {\r\n"
	            + "      margin-top: 15px;\r\n"
	            + "      font-size: 10px;\r\n"
	            + "      color: #999999;\r\n"
	            + "    }\r\n"
	            + "  </style>\r\n"
	            + "</head>\r\n"
	            + "<body>\r\n"
	            + "  <div class=\"container\">\r\n"
	            + "\r\n"
	            + "    <div class=\"header\">\r\n"
	            + "      <div class=\"icon\">\r\n"
	            + "        <img src=\"https://raw.githubusercontent.com/aliyusahaboadam/email-assets/main/logo.jpg\"\r\n"
	            + "             alt=\"Jimta Football Academy Logo\"\r\n"
	            + "             class=\"company-logo\">\r\n"
	            + "      </div>\r\n"
	            + "      <h1>🔐 Password Reset</h1>\r\n"
	            + "    </div>\r\n"
	            + "\r\n"
	            + "    <div class=\"content\">\r\n"
	            + "      <h2>Change Your <span class=\"highlight\">Password</span></h2>\r\n"
	            + "\r\n"
	            + "      <p class=\"info\">\r\n"
	            + "        We received a request to change the password for your\r\n"
	            + "        Jimta Football Academy account.\r\n"
	            + "      </p>\r\n"
	            + "\r\n"
	            + "      <div class=\"link-box\">\r\n"
	            + "        🔗 {{LINK}}\r\n"
	            + "      </div>\r\n"
	            + "\r\n"
	            + "      <div class=\"warning-box\">\r\n"
	            + "        ⏰ <strong>Important:</strong> This password reset link will\r\n"
	            + "        expire in <strong>20 minutes</strong> for security purposes.\r\n"
	            + "      </div>\r\n"
	            + "\r\n"
	            + "      <p class=\"info\">\r\n"
	            + "        🛡️ If you didn't request a password reset, please ignore\r\n"
	            + "        this email and make sure your account remains secure.\r\n"
	            + "      </p>\r\n"
	            + "\r\n"
	            + "      <a href=\"{{LINK}}\" class=\"button-style\">\r\n"
	            + "        ✅ Change Password\r\n"
	            + "      </a>\r\n"
	            + "    </div>\r\n"
	            + "\r\n"
	            + "    <div class=\"footer\">\r\n"
	            + "      <p>⚽ This is an automated message from Jimta Football Academy.</p>\r\n"
	            + "      <p>Please do not reply to this email.</p>\r\n"
	            + "      <p class=\"copyright\">\r\n"
	            + "        © 2026 Jimta Football Academy. All rights reserved.\r\n"
	            + "      </p>\r\n"
	            + "    </div>\r\n"
	            + "\r\n"
	            + "  </div>\r\n"
	            + "</body>\r\n"
	            + "</html>";
	}

	@Override
	public void saveForgotPasswordToken(ForgotPasswordToken forgotPasswordToken) {
		forgotPasswordRepository.save(forgotPasswordToken);
		
	}

	@Override
	public boolean isExpired(ForgotPasswordToken forgotPasswordToken) {
		return LocalDateTime.now().isAfter(forgotPasswordToken.getExpireTime());
	}

	@Override
	public ResponseEntity<BodyMessage> checkValidity(ForgotPasswordToken forgotPasswordToken, String password) {
		 
		 if (forgotPasswordToken == null) {
			 BodyMessage bodyMessage = new BodyMessage();
			 bodyMessage.setMessage("Invalid Token!"); 
			 return ResponseEntity.status(HttpStatus.CONFLICT).body(bodyMessage);
		 } 
		 else if (forgotPasswordToken.isUsed()) {
			 BodyMessage bodyMessage = new BodyMessage();
			 bodyMessage.setMessage("The Token is Already Used!"); 
			 return ResponseEntity.status(HttpStatus.CONFLICT).body(bodyMessage);
		 }
		 
		 else if (isExpired(forgotPasswordToken)) {
			 BodyMessage bodyMessage = new BodyMessage();
			 bodyMessage.setMessage("The Token is Expired!");
			 return ResponseEntity.status(HttpStatus.CONFLICT).body(bodyMessage);
		 } else {
			
			 User user = forgotPasswordToken.getAdmin().getUser();
			 String encodedPassword = passwordEncoder.encode(password);
			 
			 user.setPassword(encodedPassword);
			 forgotPasswordToken.setUsed(true);
			 userRepository.save(user);
			 forgotPasswordRepository.save(forgotPasswordToken);
			 
			 
		
			 BodyMessage bodyMessage = new BodyMessage();
			 bodyMessage.setMessage("Password Reset succesfully!");
			 return ResponseEntity.status(HttpStatus.ACCEPTED).body(bodyMessage); 
		 }
	}

	@Override
	public void savePlayerNewPassword(Long playerId, String password) {
		
		Player player = playerRepository.findById(playerId)
				.orElseThrow(() -> new FetchNotFoundException("Player", playerId)); 
		
		User user = player.getUser();
		String encodedPassword = passwordEncoder.encode(password);
		user.setPassword(encodedPassword);
		userRepository.save(user);
		
	}

	@Override
	public void saveCoachNewPassword(Long coachId, String password) {

		Coach coach = coachRepository.findById(coachId)
				.orElseThrow(() -> new FetchNotFoundException("Player", coachId));
		
		User user = coach.getUser();
		String encodedPassword = passwordEncoder.encode(password);
		user.setPassword(encodedPassword);
		userRepository.save(user);
	}
	
	
	

	@Override
	public void saveAdminNewPassword(Long adminId, String password) {
	    Admin admin = adminRepository.findById(adminId)
	            .orElseThrow(() -> new FetchNotFoundException("Admin", adminId));
	    User user = admin.getUser();
	    if (user == null) {
	        throw new RuntimeException("No user linked to admin " + adminId);
	    }
	    user.setPassword(passwordEncoder.encode(password));
	    userRepository.save(user);
	}

	
}
