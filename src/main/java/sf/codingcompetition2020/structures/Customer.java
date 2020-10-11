package sf.codingcompetition2020.structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Customer {
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
		//System.out.println(args);
		args = args.replaceAll(",,", ",~,");
		args = args.replaceAll("\",\"", " ~ ");
		args = args.replaceAll("},", "}~");
		
		String[] arguments = args.split(",");
		
		//System.out.println(Arrays.asList(arguments));
		
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
		
		dependents = new ArrayList<Dependent>();
		if (!arguments[8].equals("~")) {
			String json = arguments[8];
			json = json.replaceAll(" ~ ", "\",\"");
			json = json.replaceAll("}~", "},");
			json = json.replaceAll("\"\"", "\"");
			json = json.substring(1, json.length() - 1);
			
			try {
			     JSONArray jsonArray = new JSONArray(json);
			     
			     for (int i = 0 ; i < jsonArray.length(); i++) {
			         JSONObject obj = jsonArray.getJSONObject(i);
			         Dependent dependent = new Dependent(obj.getString("firstName"), obj.getString("lastName"));
			         dependents.add(dependent);
			     }
			     
			} catch (JSONException err){
			     System.out.println("error");
			}
		}
	}
	
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
	
	

}
