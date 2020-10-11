package sf.codingcompetition2020.structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Customer implements Comparable<Customer> {
	
	private int customerId;
	private String firstName;
	private String lastName;
	private int age;
	private String area;
	private int agentId;
	private short agentRating;
	private String primaryLanguage;
	private List<Dependent> dependents;
	private boolean homePolicy;
	private boolean autoPolicy;
	private boolean rentersPolicy;
	private String totalMonthlyPremium;
	private short yearsOfService;
	private Integer vehiclesInsured;
	
	public Customer(String args) {
		// format the argument string to account for the JSON
		args = args.replaceAll(",,", ",~,");
		args = args.replaceAll("\",\"", " ~ ");
		args = args.replaceAll("},", "}~");
		
		// split the arguments by ","
		String[] arguments = args.split(",");
		
		// assign the non-json variables
		customerId = Integer.parseInt(arguments[0]);
		firstName = arguments[1];
		lastName = arguments[2];
		age = Integer.parseInt(arguments[3]);
		area = arguments[4];
		agentId = Integer.parseInt(arguments[5]);
		agentRating = Short.parseShort(arguments[6]);
		primaryLanguage = arguments[7];
		homePolicy = arguments[9].equals("true");
		autoPolicy = arguments[10].equals("true");
		rentersPolicy = arguments[11].equals("true");
		totalMonthlyPremium = arguments[12];
		yearsOfService = Short.parseShort(arguments[13]);
		vehiclesInsured = Integer.parseInt(arguments[14]);
		
		// create a new array list of dependents
		dependents = new ArrayList<Dependent>();
		
		// checks if there is a JSON object
		if (!arguments[8].equals("~")) {
			
			// if there is, reformat it with our ~ delimiter
			String json = arguments[8];
			json = json.replaceAll(" ~ ", "\",\"");
			json = json.replaceAll("}~", "},");
			json = json.replaceAll("\"\"", "\"");
			json = json.substring(1, json.length() - 1);
			
			try {
				 // try to create a JSON array
			     JSONArray jsonArray = new JSONArray(json);
			     
			     // iterate through each JSON element
			     for (int dependent_index = 0 ; dependent_index < jsonArray.length(); dependent_index++) {
			    	 // grab a JSON object from the array
			         JSONObject json_dependent = jsonArray.getJSONObject(dependent_index);
			         // initialize a dependent and add it to the list
			         Dependent dependent = new Dependent(json_dependent.getString("firstName"), json_dependent.getString("lastName"));
			         dependents.add(dependent);
			     }
			     
			} catch (JSONException err){
				 // print if there's an error
			     System.out.println("error");
			}
		}
	}
	
	// -------------------- default constructor --------------------
	
	public Customer(int customerId, String firstName, String lastName, int age, String area, int agentId,
			short agentRating, String primaryLanguage, List<Dependent> dependents, boolean homePolicy,
			boolean autoPolicy, boolean rentersPolicy, String totalMonthlyPremium, short yearsOfService,
			Integer vehiclesInsured) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.area = area;
		this.agentId = agentId;
		this.agentRating = agentRating;
		this.primaryLanguage = primaryLanguage;
		this.dependents = dependents;
		this.homePolicy = homePolicy;
		this.autoPolicy = autoPolicy;
		this.rentersPolicy = rentersPolicy;
		this.totalMonthlyPremium = totalMonthlyPremium;
		this.yearsOfService = yearsOfService;
		this.vehiclesInsured = vehiclesInsured;
	}
	
	// -------------------- getters and setters --------------------
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getAgentId() {
		return agentId;
	}
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}
	public short getAgentRating() {
		return agentRating;
	}
	public void setAgentRating(short agentRating) {
		this.agentRating = agentRating;
	}
	public String getPrimaryLanguage() {
		return primaryLanguage;
	}
	public void setPrimaryLanguage(String primaryLanguage) {
		this.primaryLanguage = primaryLanguage;
	}
	public List<Dependent> getDependents() {
		return dependents;
	}
	public void setDependents(List<Dependent> dependents) {
		this.dependents = dependents;
	}
	public boolean isHomePolicy() {
		return homePolicy;
	}
	public void setHomePolicy(boolean homePolicy) {
		this.homePolicy = homePolicy;
	}
	public boolean isAutoPolicy() {
		return autoPolicy;
	}
	public void setAutoPolicy(boolean autoPolicy) {
		this.autoPolicy = autoPolicy;
	}
	public boolean isRentersPolicy() {
		return rentersPolicy;
	}
	public void setRentersPolicy(boolean rentersPolicy) {
		this.rentersPolicy = rentersPolicy;
	}
	public String getTotalMonthlyPremium() {
		return totalMonthlyPremium;
	}
	public void setTotalMonthlyPremium(String totalMonthlyPremium) {
		this.totalMonthlyPremium = totalMonthlyPremium;
	}
	public short getYearsOfService() {
		return yearsOfService;
	}
	public void setYearsOfService(short yearsOfService) {
		this.yearsOfService = yearsOfService;
	}
	public Integer getVehiclesInsured() {
		return vehiclesInsured;
	}
	public void setVehiclesInsured(Integer vehiclesInsured) {
		this.vehiclesInsured = vehiclesInsured;
	}
	
	/**
	 * Helper method for getCustomersRetainedForYearsByPlcyCostAsc() to sort customers by premium cost
	 * @params Customer c
	 * c: Customer object to compare to
	 */
	public int compareTo(Customer c) {
		// null check
	    if (getTotalMonthlyPremium() == null || c.getTotalMonthlyPremium() == null) {
	      return 0;
	    }
	    // compare the premium costs
	    return getTotalMonthlyPremium().compareTo(c.getTotalMonthlyPremium());
	}

}
