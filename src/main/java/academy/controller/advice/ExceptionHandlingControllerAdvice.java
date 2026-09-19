package academy.controller.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import academy.exception.EmailExistsException;
import academy.exception.ResourceNotFoundException;
import academy.payload.BodyMessage;

@RestControllerAdvice
public class ExceptionHandlingControllerAdvice {

	// Catches ALL unhandled exceptions in one place
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex) {
		Map<String, Object> error = new HashMap<>();
		error.put("status", 500);
		error.put("error", "Internal Server Error");
		error.put("message", ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}


	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<BodyMessage> resourceNotFoundException(ResourceNotFoundException ex) {
		BodyMessage message = new BodyMessage();
		message.setMessage(ex.getMessage());
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(message);
	}


	@ExceptionHandler(EmailExistsException.class)
	public ResponseEntity<BodyMessage> emailExistsException() {
		BodyMessage message = new BodyMessage();
		message.setMessage("Email Already Registered");
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(message);
	}


	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<BodyMessage> invalidCredential1() {
		BodyMessage message = new BodyMessage();
		message.setMessage("Invalid Credentials");
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(message);
	}


	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<BodyMessage> invalidCredential2() {
		BodyMessage message = new BodyMessage();
		message.setMessage("Invalid Credentials");
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(message);
	}


	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<BodyMessage> handleFileUploadError() {
		BodyMessage message = new BodyMessage();
		message.setMessage("You could not upload file size bigger than 200 KB");
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(message);
	}

}
