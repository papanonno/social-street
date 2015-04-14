package socialstreet;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import com.google.common.collect.Lists;

@Data
public class BaseResponse {
	
	public static Integer OK_STATUS_CODE = 0;
	public static String OK_STATUS_STRING="ok";
	
	/**
	 * Invalid data code.
	 */
	public static Integer VALIDATION_ERROR_CODE = 1;
	
	/**
	 * Generic error.
	 */
	public static Integer GENERIC_ERROR_CODE = 2;
	public static String GENERIC_ERROR_STRING="error"; 
	
	
	private Integer statusCode;
	
	
	private List<String> responseMessages = new ArrayList<String>();
	
	public BaseResponse(Integer resultCode, String resultString){
		this.statusCode=resultCode;
		setResponseMessage(resultString);
	}
	
	public BaseResponse(){
		//Note: this empty constructor is required by XStream unmarshaller
	}
	
	public String getResponseMessage(){
		if(responseMessages.isEmpty()){
			return null;
		} else {
			return responseMessages.get(0);
		}
	}
	
	public void setResponseMessage(String message){
		responseMessages = Lists.newArrayList(message);
	}
}
