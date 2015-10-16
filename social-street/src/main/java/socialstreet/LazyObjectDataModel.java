


package socialstreet;


import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Base class for Lazy Data Model objects requests this class interacts with
 * PrimeFaces data table with lazy loading enabled and sets the base values
 * (sorting and range) for the request

 */
public abstract class LazyObjectDataModel<T extends LazyObject> extends LazyDataModel<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1970391623985782437L;
	

	protected List<T> cachedElements;
	
	@Override  
    public T getRowData(String rowKey) {  
        for(T element : cachedElements) {  
            if(element.getId().equals(rowKey))  
                return element;  
        }  
        return null;  
    }  
  
    @Override  
    public Object getRowKey(T element) {  
        return element.getId();
    }  
 
    @Override
	public List<T> load(int first, int pageSize, String sortField,
			org.primefaces.model.SortOrder sortOrder,
			Map<String, Object> filters) {
		
    	//getting base request
    	BaseRequestMultipage request  = getRequest();
		
    	//settings request data parameters 
		setRequestParams(request, first, pageSize, sortField, sortOrder);

		//make service call
		BaseResponseMultipage<T> elementsResponse = runRequest(request);

		//manage response (creating local data) and return obtained data
		return manageRespose(elementsResponse);
	}
    
    protected List<T> manageRespose(BaseResponseMultipage<T> response) {
    	//settings the total number of rows (not only sub-range)
		int totalElements = response.getTotalUnrangedElements();
		this.setRowCount(totalElements);

		//caching elemnts
		cachedElements = (List<T>) response.getElements();

		return cachedElements;
	}
  
    /**
     * This method must returns the base request for lazy service calls
     * @return
     */
    protected abstract BaseRequestMultipage getRequest();
    
    /**
     * This method must set request parameters to obtain the specific range of data 
     * @param request
     * @param first
     * @param pageSize
     * @param sortField
     * @param sortOrder
     */
    protected abstract void setRequestParams(BaseRequestMultipage request, int first, int pageSize, String sortField, SortOrder sortOrder);
    
    /**
     * This method must call the lazy service for loading data 
     * @param request
     * @return
     */
    protected abstract BaseResponseMultipage<T> runRequest(BaseRequestMultipage request);

}
