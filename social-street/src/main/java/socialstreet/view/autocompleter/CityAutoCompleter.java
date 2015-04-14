package socialstreet.view.autocompleter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.primefaces.event.SelectEvent;


/**
 * City autocomplete class, this class provides city autocomplete functionality and updates the address fields
 *
 */
public class CityAutoCompleter extends AutoCompleter<City>{
	
	private final String JSON_CITY_LIST="cities_autocomplete/cities.json";
		
	@Override
	List<City> getAutoCompleteList() throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		JsonFactory jfactory = new JsonFactory();
		
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

		InputStream citiesStream = contextClassLoader.getResourceAsStream(JSON_CITY_LIST);
		
		JsonParser jsoncityParser = jfactory.createJsonParser(citiesStream);
		TypeReference<List<City>> typeReference = new TypeReference<List<City>>(){};
		
		List<City> cityList = mapper.readValue(jsoncityParser, typeReference);

		return cityList;
		
	}
	

	@Override
	public void handleSelect(SelectEvent event){
//		if (privateAddress!=null){
//			City city=getBySelectEvent( event);
//			
//			privateAddress.setCity(city.getCity());
//			privateAddress.setProvince(city.getProvince());
//			privateAddress.setFax(city.getPrefix());
//			privateAddress.setAlternativeFax(city.getPrefix());
//			privateAddress.setPhone(city.getPrefix());
//			privateAddress.setZipCode(city.getZipcode());
//		}
	};
		
}
