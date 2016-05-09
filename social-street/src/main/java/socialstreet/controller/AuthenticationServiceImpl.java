package socialstreet.controller;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;

import socialstreet.BaseResponse;
import socialstreet.Utils;
import socialstreet.model.Person;
import socialstreet.model.query.PersonManager;
import socialstreet.person.LoginRequest;
import socialstreet.person.LoginResponse;

@Transactional
public class AuthenticationServiceImpl implements AuthenticationService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Log logger = LogFactory.getLog(AuthenticationServiceImpl.class);
	
	PersonManager mapper;

	/**
	 *  
	 * TODO 
	 * - token
	 * - inserire bcrypt per le password
	 * @see socialstreet.controller.AuthenticationService#login(socialstreet.person.LoginRequest)
	 */
	public LoginResponse login(LoginRequest request) {
		
		LoginResponse resp = new LoginResponse();
		
		SqlSession sqlSession = Utils.getSqlSession();
		sqlSession.getConfiguration().addMapper(Person.class);
		
		Person p = null;
		
		try {
			
	      mapper = sqlSession.getMapper(PersonManager.class);
	      p = mapper.getByEmail(request.getEmail());
	      if (p==null){
	   
		      resp.setStatusCode(LoginResponse.INVALID_EMAIL);
		      resp.setResponseMessage(LoginResponse.INVALID_EMAIL_MESSAGE);
		      return resp;
	      }
	      else if(!p.getPassword().equals(request.getPassword())){
	    	  
		      resp.setStatusCode(LoginResponse.INVALID_PASSWORD);
		      resp.setResponseMessage(LoginResponse.INVALID_PASSWORD_MESSAGE);
		      return resp;
	      }
	      
	      resp.setPerson(p);
	      resp.setStatusCode(BaseResponse.OK_STATUS_CODE);
	      
		}catch(Exception e){
			
			logger.error(e.getMessage());
			
			resp.setStatusCode(BaseResponse.GENERIC_ERROR_CODE);
			resp.setResponseMessage("login.failed");
			return resp;
			
		} finally {
			sqlSession.close(); 
	    }
		return resp;
	}

		
}
