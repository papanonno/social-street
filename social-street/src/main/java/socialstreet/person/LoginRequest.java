package socialstreet.person;

import lombok.Data;
import socialstreet.BaseRequest;

@Data
public class LoginRequest extends BaseRequest{
	
	String email;
	
	String password;
}
