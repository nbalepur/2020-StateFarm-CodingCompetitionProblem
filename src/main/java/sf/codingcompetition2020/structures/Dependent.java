package sf.codingcompetition2020.structures;

public class Dependent {
	private String firstName;
	private String lastName;
	
	
	// -------------------- default constructors --------------------
	
	public Dependent(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	// -------------------- getters and setters --------------------
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

}
