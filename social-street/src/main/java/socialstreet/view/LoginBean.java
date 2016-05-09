package socialstreet.view;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;
import socialstreet.WebConstants;
import socialstreet.controller.AuthenticationService;
import socialstreet.model.Person;
import socialstreet.person.LoginRequest;
import socialstreet.person.LoginResponse;
import socialstreet.view.util.SessionContext;

@ManagedBean(name = "login")
@ViewScoped
public class LoginBean implements Serializable {


	@ManagedProperty(value = "#{mySessionContext}")
	@Setter
	private SessionContext sessionContext;

	@ManagedProperty(value = "#{AuthenticationService}")
	@Setter
	private AuthenticationService authenticationService;

	@Getter
	@Setter
	private String username;

	@Getter
	@Setter
	private String password;
	


	public String login() {

		LoginResponse response = null;
		LoginRequest request = new LoginRequest();
		request.setPassword(password);
		request.setEmail(username);

		response = authenticationService.login(request);
		if(!LoginResponse.OK_STATUS_CODE.equals(response.getStatusCode())){
			
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getResponseMessage(), null));
			return "LOGIN";
		}
		
		//sessionContext.setContext(loginResponse.getToken());
		Person person = response.getPerson();
		sessionContext.setPerson(person);
		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(WebConstants.AUTHENTICATED_SESSION_KEY, Boolean.TRUE);

		return "USERS";
	}
	
}
