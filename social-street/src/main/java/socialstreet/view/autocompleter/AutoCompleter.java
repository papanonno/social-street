package socialstreet.view.autocompleter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import org.codehaus.jackson.map.JsonMappingException;
import org.primefaces.event.SelectEvent;

import com.google.gson.JsonParseException;

import edu.macalester.acs.AutocompleteEntry;
import edu.macalester.acs.AutocompleteTree;

/**
 * Abstract Class used for server side autocomplete facility  
 *
 */
public abstract class AutoCompleter<E> {
	
	
	AutocompleteTree<String, E> autocompleteTree = new AutocompleteTree<String, E>();
	
	
	public AutoCompleter() {
		 
		 List<E> autoCompleteList=new ArrayList<E>();
		 
		 try{
			 autoCompleteList = getAutoCompleteList();
		 } catch (Exception e){
			 e.printStackTrace();
		 }
		 
		 
		 for (E entry : autoCompleteList){
			 AutocompleteEntry<String,E> autocompleteEntry = new AutocompleteEntry<String, E>(entry.toString(), entry);
			 autocompleteTree.add(autocompleteEntry);
		 }
		 
		 
	}
	
	/**
	 * Given a query String returns the list of String of corresponding elements 
	 * @param inputString
	 * @return
	 */
	public List<String> autoComplete(String inputString){
		SortedSet<AutocompleteEntry<String, E>> lastAutocompleteSet = autocompleteTree.autocomplete(inputString,Integer.MAX_VALUE);
		
		List<String> resultSet = new ArrayList<String>();
		
		for (AutocompleteEntry<String, E> entry : lastAutocompleteSet){
			resultSet.add(entry.getValue().toString());
		}
		
		return resultSet; 
	}
	
	/**
	 * given an selection event return the relative element
	 * @param event
	 * @return
	 */
	public E getBySelectEvent(SelectEvent event){
		
		String selectedKey = event.getObject().toString();
		
		AutocompleteEntry<String, E> entry = autocompleteTree.get(selectedKey);
		
		return entry.getValue();
		
	}
	
	/**
	 * The Select Event handler, this method must implement the select operations (Ie. update some fields)
	 * @param event
	 */
	public abstract void handleSelect(SelectEvent event);
	
	/**
	 * Implement this method to create the list of entry to be searched
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	abstract List<E> getAutoCompleteList() throws JsonParseException, JsonMappingException, IOException;
}
