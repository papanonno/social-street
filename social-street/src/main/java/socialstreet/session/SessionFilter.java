package socialstreet.session;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import socialstreet.WebConstants;


public class SessionFilter implements Filter {


	private static Logger LOGGER = LoggerFactory.getLogger(SessionFilter.class);

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();

		String pathInfo = httpRequest.getPathInfo();
		
		if(pathInfo.contains("Users")){
			chain.doFilter(httpRequest, httpResponse);
			return ;
		}
		
		Boolean authenticated = (Boolean) session.getAttribute(WebConstants.AUTHENTICATED_SESSION_KEY);
		if(authenticated==null){
			authenticated = Boolean.FALSE;
		}
		
		if(!WebConstants.LOGIN_PATH_INFO.equals(pathInfo) && !isResource(pathInfo) && !authenticated){
			String redirectUrl = httpRequest.getContextPath()+httpRequest.getServletPath()+WebConstants.LOGIN_PATH_INFO;
			httpResponse.sendRedirect(redirectUrl);
			
			LOGGER.debug("Unauthorized access to "+pathInfo+". Redirecting to Login page");
		} else {
			
			chain.doFilter(httpRequest, httpResponse);
		}
		
		
	}
	
	private boolean isResource(String pathInfo){
		return pathInfo!=null &&
				pathInfo.startsWith("/javax.faces.resource");
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}