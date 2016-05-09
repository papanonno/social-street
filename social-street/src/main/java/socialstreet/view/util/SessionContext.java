package socialstreet.view.util;

import java.io.Serializable;

import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import lombok.Getter;
import lombok.Setter;
import socialstreet.model.Person;

@ManagedBean(name="mySessionContext")
@SessionScoped
public class SessionContext implements Serializable{


	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private String context;
	
	@Getter
	@Setter
	private Person person;

	
	public static SessionContext getCurrent(){
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		SessionContext sessionContext = (SessionContext) elContext.getELResolver().getValue(elContext, null, "mySessionContext");
		
		return sessionContext;
	}
	
	public boolean isInvalid(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session==null;
	}
	
	public String logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		if(session!=null){
			session.invalidate();
		}
		
		return "LOGIN";
	}
	
}
