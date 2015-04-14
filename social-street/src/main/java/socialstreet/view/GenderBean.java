package socialstreet.view;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import socialstreet.model.Person;
import socialstreet.model.Person.Gender;

@ManagedBean
public class GenderBean {
	
  public SelectItem[] getGenderValues() {
    SelectItem[] items = new SelectItem[Person.Gender.values().length];
    int i = 0;
    for(Gender g: Person.Gender.values()) {
      items[i++] = new SelectItem(g, g.getLabel());
    }
    return items;
  }
}
