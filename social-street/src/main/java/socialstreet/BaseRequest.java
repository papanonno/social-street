package socialstreet;

import java.util.Locale;

import lombok.Data;


@Data
public class BaseRequest {

	Locale locale;
	
	private AuthData authData;
}
