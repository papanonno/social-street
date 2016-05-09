package socialstreet.rest;

import lombok.Setter;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import socialstreet.controller.PersonService;
import socialstreet.model.Person;
import socialstreet.person.UsersRequest;
import socialstreet.person.UsersResponse;

@RestController
public class PersonRestController implements PersonService {
	
	@Setter
	private PersonService personService;

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces={"application/json"}, consumes = {"application/json"})
	@ResponseBody
	public Boolean register(@RequestBody Person p) {
		
		return personService.register(p);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = {"application/json"}, produces={"application/json"})
	@ResponseBody
	public Boolean updateUser(@RequestBody Person person) {
		
		return personService.updateUser(person);
	}
	

	@RequestMapping(value = "/delete/{email}", method = RequestMethod.DELETE, produces={"application/json"})
	public Boolean delete(@PathVariable String email) {
		
		return personService.delete(email);
	}

	@RequestMapping(value = "/getPerson", method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public Person getUserByEmail(@RequestParam(value="email") String email) {
		
		return personService.getUserByEmail(email);
	}

	@RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public UsersResponse getUsers(@RequestBody UsersRequest request) {
		
		return personService.getUsers(request);
	}

	@RequestMapping(value = "/getPersons", method = RequestMethod.POST, consumes = "application/json", produces={"application/json"})
	@ResponseBody
	public UsersResponse getPersons(@RequestBody UsersRequest request) {
		
		return personService.getPersons(request);
	}
}
