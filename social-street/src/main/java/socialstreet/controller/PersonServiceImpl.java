package socialstreet.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;

import socialstreet.BaseRequestMultipage.SortOrder;
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
	
	private static Log logger = LogFactory.getLog(PersonServiceImpl.class);
	
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

	public Boolean register(Person person) {

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


	public Boolean updateUser(Person person) {
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


	public Boolean delete(String email) {
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
	
	public int countPersons() {
		
		SqlSession sqlSession = Utils.getSqlSession();
		sqlSession.getConfiguration().addMapper(Person.class);
		
		try {
	      mapper = sqlSession.getMapper(PersonManager.class);
	      return  mapper.countPersons();
		}catch(Exception e){
			
			return -1;
		} finally {
			sqlSession.close(); 
	    }

	}
	
	public UsersResponse getPersons(UsersRequest request) {
		
		
		UsersResponse response = new UsersResponse();
		
		SqlSession sqlSession = Utils.getSqlSession();
		sqlSession.getConfiguration().addMapper(Person.class);
		
		List<Person> persons = null;
		
		try {
	      mapper = sqlSession.getMapper(PersonManager.class);
	      
	      
	      Map<String, String> params = new HashMap<String, String>();
	      String sortField = request.getSortField();
	      params.put("sortOrder", "ASC"); 
	      if(sortField!=null){
	    	  params.put("sortColumn", sortField);
		      if (request.getSortOrder().equals(SortOrder.ASCENDING)) {
		        params.put("sortOrder", "ASC");        
		      } else {
		        params.put("sortOrder", "DESC");
		      }
	      }

	      
	      RowBounds rowBounds = new RowBounds(request.getFirstResult(), request.getMaxResults());
	      persons = sqlSession.selectList("getAllPersons", params, rowBounds);
	      
	      logger.info("persons" + persons);

		}catch(Exception e){
			
			response.setStatusCode(BaseResponse.GENERIC_ERROR_CODE);
			return response;
		} finally {
			sqlSession.close(); 
	    }
		
		response.setElements(persons);
		response.setTotalUnrangedElements(countPersons());
		response.setStatusCode(BaseResponse.OK_STATUS_CODE);
		return response;
	}

}
