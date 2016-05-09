package socialstreet.controller;

import org.springframework.stereotype.Service;

import socialstreet.model.Person;
import socialstreet.person.UsersRequest;
import socialstreet.person.UsersResponse;

@Service
public interface PersonService {
	
	public Boolean register(Person p);
	
	public Boolean updateUser(Person person);
	
	public Boolean delete(String email);
	
	public Person getUserByEmail(String email);
	
	public UsersResponse getUsers(UsersRequest request);
	
	public UsersResponse getPersons(UsersRequest request);

	
}
