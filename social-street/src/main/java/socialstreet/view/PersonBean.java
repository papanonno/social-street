package socialstreet.view;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import socialstreet.controller.PersonService;
import socialstreet.model.Person;
import socialstreet.view.autocompleter.City;

	@ManagedBean(name="personBean")
	@SessionScoped
	public class PersonBean implements Serializable{
	
		private static final long serialVersionUID = 1L;
		
		private static Log logger = LogFactory.getLog(PersonBean.class);

		@ManagedProperty(value="#{PersonService}")
		@Setter
		private PersonService personService;
		
//		@Getter
//		private CityAutoCompleter autocompleter=new CityAutoCompleter();
		
		/**
		 * 
		 * TODO Da sosituire con oggetto vero, non entities
		 */
		@Getter
		private City city = new City();
		
		@Getter
		private Person person = new Person();
		
		public String registration(){
			
//			WebApplicationContext ctx =  FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
//		    personService = ctx.getBean(PersonService.class);
			
			boolean esito = personService.register(person);
			
			logger.info("[personBean] registration started");
						
			if(!esito){
			    ResourceBundle bundle = ResourceBundle.getBundle("locale.labels", FacesContext.getCurrentInstance().getViewRoot().getLocale());
			    String text = bundle.getString("error.insert");
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, text, text);
				FacesContext.getCurrentInstance().addMessage("messages", facesMessage);
				return "";
			}
			
			return "USERS";
		}
		

		public String updatePerson(){
			

			return "UPDATE";
		}
		
		

}
