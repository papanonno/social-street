package socialstreet.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lombok.Getter;
import lombok.Setter;
import socialstreet.controller.PersonService;
import socialstreet.model.LazyPersonDataModel;
import socialstreet.model.Person;
import socialstreet.person.UsersRequest;

	@ManagedBean(name="usersBean")
	@ViewScoped
	public class UsersBean implements Serializable{
	
		private static final long serialVersionUID = 1L;
		
		//private static Log logger = LogFactory.getLog(UsersBean.class);

		@ManagedProperty(value="#{PersonService}")
		@Setter
		private PersonService personService;
		
//		@Getter
//		private List<Person> persons = new ArrayList<Person>();
		
		@Getter
		private LazyPersonDataModel persons;
		
		@PostConstruct
		public void init(){
			
			persons = new LazyPersonDataModel(personService);
//			UsersRequest request = new UsersRequest();
//			
//			persons = personService.getUsersPaginate(request).getElements();
		}

}
