package socialstreet;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Stores data for authentication and authorization.
 * 
 * @author ctasso
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AuthData {

	/**
	 * The authentication token.
	 */
	@Getter
	@Setter
	private String token;
}
