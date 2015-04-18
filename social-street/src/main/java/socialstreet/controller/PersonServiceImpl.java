package socialstreet.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;

import socialstreet.BaseResponse;
import socialstreet.Utils;
import socialstreet.model.Person;
import socialstreet.model.query.PersonManager;
import socialstreet.person.UsersRequest;
import socialstreet.person.UsersResponse;

@Transactional
public class PersonServiceImpl implements PersonService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	PersonManager mapper;

	@PostConstruct
	public void init() {
		SqlSession sqlSession = Utils.getSqlSession();
		sqlSession.getConfiguration().addMapper(Person.class);

		try {
	      mapper = sqlSession.getMapper(PersonManager.class);
	      mapper.createPersonTable();
		} finally {
			sqlSession.close(); 
	    }
		
	}

	
	public boolean register(Person person) {

		SqlSession sqlSession = Utils.getSqlSession();
		sqlSession.getConfiguration().addMapper(Person.class);
		
		try {
	      mapper = sqlSession.getMapper(PersonManager.class);
	      mapper.insert(person);
	      sqlSession.commit();
		}catch(Exception e){
			
			return false;
		} finally {
			sqlSession.close(); 
	    }
		
		return true;
	}


	public boolean updateUser(Person person) {
		SqlSession sqlSession = Utils.getSqlSession();
		sqlSession.getConfiguration().addMapper(Person.class);
		
		try {
	      mapper = sqlSession.getMapper(PersonManager.class);
	      mapper.update(person);
	      sqlSession.commit();
		}catch(Exception e){
			
			return false;
		} finally {
			sqlSession.close(); 
	    }
		
		
		return true;
	}


	public boolean delete(String email) {
		SqlSession sqlSession = Utils.getSqlSession();
		sqlSession.getConfiguration().addMapper(Person.class);
		
		try {
	      mapper = sqlSession.getMapper(PersonManager.class);
	      mapper.delete(email);
	      sqlSession.commit();
		}catch(Exception e){
			
			return false;
		} finally {
			sqlSession.close(); 
	    }
		
		
		return true;
	}


	public Person getUserByEmail(String email) {
		
		SqlSession sqlSession = Utils.getSqlSession();
		sqlSession.getConfiguration().addMapper(Person.class);
		
		Person p = null;
		
		try {
	      mapper = sqlSession.getMapper(PersonManager.class);
	      p = mapper.getByEmail(email);
		}catch(Exception e){
			
			return p;
		} finally {
			sqlSession.close(); 
	    }
		return p;
	}


	public UsersResponse getUsers(UsersRequest request) {
		
		
		UsersResponse response = new UsersResponse();
		
		SqlSession sqlSession = Utils.getSqlSession();
		sqlSession.getConfiguration().addMapper(Person.class);
		
		List<Person> persons = null;
		
		try {
	      mapper = sqlSession.getMapper(PersonManager.class);
	      persons = mapper.getAllPersons();
		}catch(Exception e){
			
			response.setStatusCode(BaseResponse.GENERIC_ERROR_CODE);
			return response;
		} finally {
			sqlSession.close(); 
	    }
		
		response.setElements(persons);
		response.setStatusCode(BaseResponse.OK_STATUS_CODE);
		return response;
	}
	

}
