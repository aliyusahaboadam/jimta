package academy.exception;

import org.springframework.http.HttpStatus;

public class AcademyException extends RuntimeException {

	public AcademyException(String string, HttpStatus conflict) {
		super();
	}
	
	public AcademyException(String string) {
		super();
	}
	
	

}
