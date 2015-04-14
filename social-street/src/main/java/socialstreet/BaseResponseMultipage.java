package socialstreet;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class BaseResponseMultipage<T extends Object> extends BaseResponse{

	private List<T> elements = new ArrayList<T>();
	
	/**
	 * In Range query the size of elements is limited by "maxResults", so the
	 * total amount of elements are stored in the following variable
	 */
	private int totalUnrangedElements;

	public BaseResponseMultipage(Integer resultCode, String resultString,List<T> elements, int totalUnrangedElements){
		super(resultCode,resultString);
		this.totalUnrangedElements=totalUnrangedElements;
		this.elements=elements;
	}
}
