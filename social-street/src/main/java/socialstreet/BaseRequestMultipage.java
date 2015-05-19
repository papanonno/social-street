package socialstreet;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=true)
public class BaseRequestMultipage extends BaseRequest{

	public enum SortOrder {
		ASCENDING, DESCENDING, UNSORTED
	}
	
	private SortOrder sortOrder = SortOrder.UNSORTED;
	
	private String sortField;

	private boolean rangeQuery = false;
	
	/**
	 * The first result index for paginated results.
	 * Use -1 for disabling pagination. 
	 */
	private int firstResult = 0;
	
	/**
	 * Max number of results for a single result page.
	 */
	private int maxResults = 5;
	
}
