package socialstreet.person;

import lombok.Data;
import socialstreet.BaseResponse;
import socialstreet.model.Person;

@Data
public class LoginResponse extends BaseResponse{
	
	/**
	 * Invalid Password.
	 */
	public static Integer INVALID_EMAIL = 101;
	public static String INVALID_EMAIL_MESSAGE= "invalid.email"; 
	
	/**
	 * Invalid Password.
	 */
	public static Integer INVALID_PASSWORD = 102;
	public static String INVALID_PASSWORD_MESSAGE= "invalid.password"; 
	
	Person person;
	
	//TODO
	String token;
}
