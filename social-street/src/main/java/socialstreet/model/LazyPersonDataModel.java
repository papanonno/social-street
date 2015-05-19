package socialstreet.model;

import org.primefaces.model.SortOrder;

import socialstreet.BaseRequestMultipage;
import socialstreet.BaseResponseMultipage;
import socialstreet.LazyObjectDataModel;
import socialstreet.controller.PersonService;
import socialstreet.person.UsersRequest;

public class LazyPersonDataModel extends LazyObjectDataModel<Person> {

	private static final long serialVersionUID = 1L;
	
	private PersonService personService;
	
	public LazyPersonDataModel(PersonService personService){
		this.personService = personService;
	}

	@Override
	protected BaseRequestMultipage getRequest() {
		UsersRequest personServiceRequest = new UsersRequest();
		return personServiceRequest;
	}

	@Override
	protected BaseResponseMultipage<Person> runRequest(BaseRequestMultipage usersRequest) {
		BaseResponseMultipage<Person> response = personService.getPersons((UsersRequest) usersRequest);
		return response;
	}

	@Override
	protected void setRequestParams(BaseRequestMultipage request, int first,
			int pageSize, String sortField, SortOrder sortOrder) {
		
		request.setFirstResult(first);
		request.setMaxResults(pageSize);
		request.setRangeQuery(true);
		request.setSortField(sortField);
		switch (sortOrder) {
		case ASCENDING:
			request.setSortOrder(BaseRequestMultipage.SortOrder.ASCENDING);
			break;
		case DESCENDING:
			request.setSortOrder(BaseRequestMultipage.SortOrder.DESCENDING);
			break;
		case UNSORTED:
			request.setSortOrder(BaseRequestMultipage.SortOrder.UNSORTED);
		}
	}

}
