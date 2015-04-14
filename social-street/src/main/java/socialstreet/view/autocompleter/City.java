package socialstreet.view.autocompleter;

import lombok.Data;

@Data
public class City {
	
	String searchString;
	String city;
	String zipcode;
	String province;
	String prefix;
	
	public String toString(){
		return searchString;
	}

}
