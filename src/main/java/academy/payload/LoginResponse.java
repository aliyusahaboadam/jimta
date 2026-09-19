package academy.payload;

public class LoginResponse {
	
	
	private String jwt;
	private String redirectUrl;
	
	public LoginResponse(String jwt, String redirectUrl) {
		super();
		this.jwt = jwt;
		this.redirectUrl = redirectUrl;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	
	

}
