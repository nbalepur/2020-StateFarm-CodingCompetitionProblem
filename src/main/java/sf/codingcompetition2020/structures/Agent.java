package sf.codingcompetition2020.structures;

public class Agent {
	
	private int agentId;
	private String area;
	private String language;
	private String firstName;
	private String lastName;
	
	public Agent(String args) {
		// split the arguments by ","
		String[] arguments = args.split(",");
		
		// assign the variables appropriately
		agentId = Integer.parseInt(arguments[0]);
		area = arguments[1];
		language = arguments[2];
		firstName = arguments[3];
		lastName = arguments[4];
	}
	
	// -------------------- default constructor --------------------
	
	public Agent(int agentId, String area, String language, String firstName, String lastName) {
		super();
		this.agentId = agentId;
		this.area = area;
		this.language = language;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	// -------------------- getters and setters --------------------
	
	public int getAgentId() {
		return agentId;
	}
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
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
