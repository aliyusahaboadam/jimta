package academy.payload;



public class BodyMessage {
    
	private Long id;
	private String message;
	
    
    
    
    

	public BodyMessage() {
		super();
	}

	public BodyMessage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
    
    
}
