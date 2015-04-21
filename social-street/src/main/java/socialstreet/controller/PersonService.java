package socialstreet.controller;

import org.springframework.stereotype.Service;

import socialstreet.model.Person;
import socialstreet.person.UsersRequest;
import socialstreet.person.UsersResponse;

@Service
public interface PersonService {
	
	public boolean register(Person p);
	
	public boolean updateUser(Person person);
	
	public boolean delete(String email);
	
	public Person getUserByEmail(String email);
	
	public UsersResponse getUsers(UsersRequest request);
	
	public UsersResponse getUsersPaginate(UsersRequest request);

	
}
