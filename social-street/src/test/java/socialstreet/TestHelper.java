package socialstreet;

import socialstreet.model.Person;

public class TestHelper {
	
	public static String EMAIL = "prova@prova.it";
	
	public static Person buildPerson(){
		
		Person p = new Person();
		
		p.setName("Raffaele");
		p.setSurname("De Stasio");
		p.setPassword("password");
		
		p.setEmail(EMAIL);
		
		return p;
	}

}
