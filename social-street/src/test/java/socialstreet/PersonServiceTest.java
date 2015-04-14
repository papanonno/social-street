package socialstreet;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import socialstreet.controller.PersonService;
import socialstreet.model.Person;
import socialstreet.person.UsersRequest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/social-street-context.xml"})
public class PersonServiceTest {
	
	private static String TES_NAME = "Pippo";

	@Autowired 
	PersonService personService;

	@Before
	public void setUp() {
		//mockControl.resetToDefault();
	}


	@Test
	public void testGetUserById() {

		Person buildPerson = TestHelper.buildPerson();
		boolean esito = personService.register(buildPerson);
		
		Assert.assertEquals(esito, true);
		
		Person personCreated = personService.getUserByEmail(TestHelper.EMAIL);
		
		Assert.assertEquals(personCreated, buildPerson);
		
		personService.delete(TestHelper.EMAIL);
	}

	@Test
	public void testDeleteUser() {
		
		Person buildPerson = TestHelper.buildPerson();
		personService.register(buildPerson);
		personService.getUserByEmail(TestHelper.EMAIL);
		personService.delete(TestHelper.EMAIL);
		Person deletedPerson = personService.getUserByEmail(TestHelper.EMAIL);
		
		Assert.assertNull(deletedPerson);

	}
	
	@Test
	public void testGetAllUsers() {
		
		Person buildPerson = TestHelper.buildPerson();
		personService.register(buildPerson);
		
		List<Person> users = personService.getUsers(new UsersRequest()).getElements();
		Assert.assertNotNull(users);
		for (Person user : users) {
			Assert.assertEquals(user, buildPerson);
		}

		personService.delete(TestHelper.EMAIL);
		
	}

	@Test
    public void testUpdateUser() 
    {
		personService.delete(TestHelper.EMAIL);
		
		Person buildPerson = TestHelper.buildPerson();
		personService.register(buildPerson);
		
		buildPerson.setName(TES_NAME);
		
		boolean updated = personService.updateUser(buildPerson);
		Assert.assertTrue(updated);
		
		Person updatedPerson = personService.getUserByEmail(TestHelper.EMAIL);
		
		Assert.assertEquals(TES_NAME, updatedPerson.getName());
		
		personService.delete(TestHelper.EMAIL);
		
 }

}
