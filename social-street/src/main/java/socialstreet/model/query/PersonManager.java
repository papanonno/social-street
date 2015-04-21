package socialstreet.model.query;

import java.util.List;

import socialstreet.model.Person;



public interface PersonManager {

	
	void createPersonTable();
	
	void insert(Person person);
	
	void update(Person person);
	
	void delete(String email);
	
	Person getByEmail(String email);
	
	List<Person> getAllPersons();
	
	int countPersons();

}
