package socialstreet.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import socialstreet.LazyObject;


@Data
public class Person implements Serializable,LazyObject{
	

	private static final long serialVersionUID = 1L;
	
	private String id;

	private String name;
	
	private String surname;
	
	private String email;
	
	private String password;
	
	/**
	 * age of person
	 * 
	 */
	private Integer age;
	
	/**
	 * gender
	 * 
	 */
	private String gender;
	
	private Date birthday;
		
	/**
	 *
	 * Gender Enum Male and Female
	 *
	 */
	public enum Gender {
		  MALE("Maschile"),
		  FEMALE("Femminile");

		  private final String label;

		  private Gender(String label) {
		    this.label = label;
		  }

		  public String getLabel() {
		    return this.label;
		  }
		}

}
