package hospital;

import java.io.Serializable;

public class Person implements Serializable {

	private String firstName;
	private String lastName;
	
	/**
	 * @param firstName
	 * @param lastName
	 * used to set the name fields for a person
	 */
	public void SetName (String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	/**
	 * @return a name in the general format Lastname, Firstname
	 */
	public String getFormattedName () {
		return this.lastName + ", " + this.firstName;
	}
	
	/**
	 * @return just the person's first name, used for additional formatting in subclasses
	 */
	protected String getFirstName () {
		return this.firstName;
	}
	
	/**
	 * @return just the person's last name, used for additional formatting in subclasses
	 */
	protected String getLastName () {
		return this.lastName;
	}
}
