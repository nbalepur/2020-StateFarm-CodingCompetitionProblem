package sf.codingcompetition2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import sf.codingcompetition2020.structures.Agent;
import sf.codingcompetition2020.structures.Claim;
import sf.codingcompetition2020.structures.Customer;
import sf.codingcompetition2020.structures.Vendor;

public class CodingCompCsvUtil {
	
	/* #1 
	 * readCsvFile() -- Read in a CSV File and return a list of entries in that file.
	 * @param filePath -- Path to file being read in.
	 * @param classType -- Class of entries being read in.
	 * @return -- List of entries being returned.
	 */
	public <T> List<T> readCsvFile(String filePath, Class<T> classType) {
		
		// declare our scanner
		Scanner scanner = null;
		// handle any errors with the file path
		try {
			scanner = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// declare a list of entries
		List<T> entries = new ArrayList<T>();
		// skips the first line of the csv file, as it's not needed
		scanner.nextLine();
		
		// iterates while there's still entires
		while (scanner.hasNextLine()) {
			// grabs our entry
			String entry = scanner.nextLine();
			try {
				// attempts to create an object using the string constructor, and adds it to the list of entires
				T parsedObject = classType.getDeclaredConstructor(String.class).newInstance(entry);
				entries.add(parsedObject);
			} 
			// handles all possible errors
			catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		// returns our list of entires
		return entries;
	}

	
	/* #2
	 * getAgentCountInArea() -- Return the number of agents in a given area.
	 * @param filePath -- Path to file being read in.
	 * @param area -- The area from which the agents should be counted.
	 * @return -- The number of agents in a given area
	 */
	public int getAgentCountInArea(String filePath,String area) {
		// declare agent count to 0
		int agentCount = 0;
		// read list of agents from csv
		List<Agent> agentList = readCsvFile(filePath, Agent.class);
		
		// iterate through each agent
		for (Agent agent : agentList) {
			// increment when the areas are equivalent
			if (agent.getArea().equals(area)) {
				agentCount++;
			}
		}
		
		// return the number of agents
		return agentCount;
	}

	
	/* #3
	 * getAgentsInAreaThatSpeakLanguage() -- Return a list of agents from a given area, that speak a certain language.
	 * @param filePath -- Path to file being read in.
	 * @param area -- The area from which the agents should be counted.
	 * @param language -- The language spoken by the agent(s).
	 * @return -- The number of agents in a given area
	 */
	public List<Agent> getAgentsInAreaThatSpeakLanguage(String filePath, String area, String language) {
		// read the agent list from the csv
		List<Agent> agentList = readCsvFile(filePath, Agent.class);
		// declare the langauges list
		List<Agent> agentLangs = new ArrayList<Agent>();
		
		// itearte through each agent
		for (Agent agent : agentList) {
			// check if the areas and languages are equal 
			if (agent.getArea().equals(area) && agent.getLanguage().equals(language)) {
				// append the langauges
				agentLangs.add(agent);
			}
		}
		
		// return the list of languages
		return agentLangs; 
	}
	
	
	/* #4
	 * countCustomersFromAreaThatUseAgent() -- Return the number of individuals from an area that use a certain agent.
	 * @param filePath -- Path to file being read in.
	 * @param customerArea -- The area from which the customers should be counted.
	 * @param agentFirstName -- First name of agent.
	 * @param agentLastName -- Last name of agent.
	 * @return -- The number of customers that use a certain agent in a given area.
	 */
	public short countCustomersFromAreaThatUseAgent(Map<String,String> csvFilePaths, String customerArea, String agentFirstName, String agentLastName) {
		// read the agents and customers from the csv
		List<Customer> custList = readCsvFile(csvFilePaths.get("customerList"), Customer.class);
		List<Agent> agentList = readCsvFile(csvFilePaths.get("agentList"), Agent.class);
		
		// declare the list of customers
		short numCustomers = 0;
		
		// initialize agentID to -1
		int agentID = -1;
		// iterate through each agent
		for (Agent agent : agentList) {
			// update the agentID if the names are equal
			if (agent.getFirstName().equals(agentFirstName) && agent.getLastName().equals(agentLastName)) {
				agentID = agent.getAgentId();
			}
		}
		
		// iterate through each customer
		for (Customer cust : custList) {
			// increment the customers if the area and agent IDs are equivalent
			if (cust.getArea().equals(customerArea) && cust.getAgentId() == agentID) {
				numCustomers++;
			}
		}
		
		// return the number of customers
		return numCustomers;
	}

	
	/* #5
	 * getCustomersRetainedForYearsByPlcyCostAsc() -- Return a list of customers retained for a given number of years, in ascending order of their policy cost.
	 * @param filePath -- Path to file being read in.
	 * @param yearsOfServeice -- Number of years the person has been a customer.
	 * @return -- List of customers retained for a given number of years, in ascending order of policy cost.
	 */
	public List<Customer> getCustomersRetainedForYearsByPlcyCostAsc(String customerFilePath, short yearsOfService) {
		// read a list of customers from the csv
		List<Customer> custList = readCsvFile(customerFilePath, Customer.class);
		
		// declare a list of retained customers
		List<Customer> retainedCusts = new ArrayList<Customer>();
		
		// iterate through each customer
		for (Customer cust : custList) {
			// add to the list if the years of service are equivalent
			if (cust.getYearsOfService() == yearsOfService) {
				retainedCusts.add(cust);
			}
		}
		// sort and return the list
		Collections.sort(retainedCusts);
		return retainedCusts;
	}
	
	/* #6
	 * getLeadsForInsurance() -- Return a list of individuals who’ve made an inquiry for a policy but have not signed up.
	 * *HINT* -- Look for customers that currently have no policies with the insurance company.
	 * @param filePath -- Path to file being read in.
	 * @return -- List of customers who’ve made an inquiry for a policy but have not signed up.
	 */
	public List<Customer> getLeadsForInsurance(String filePath) {
		
		// get list of customers from the CSV files
		List<Customer> customers = readCsvFile(filePath, Customer.class);
		// initialize list of customers with no polocies
		List<Customer> noPolicies = new ArrayList<Customer>();
		
		// iterate through each customer
		for (Customer customer : customers) {
			// filter out customers with no polocies
			if (!customer.isHomePolicy() && !customer.isAutoPolicy() && !customer.isRentersPolicy()) {
				noPolicies.add(customer);
			}
		}
		
		// return the list
		return noPolicies;
	}


	/* #7
	 * getVendorsWithGivenRatingThatAreInScope() -- Return a list of vendors within an area and include options to narrow it down by: 
			a.	Vendor rating
			b.	Whether that vendor is in scope of the insurance (if inScope == false, return all vendors in OR out of scope, if inScope == true, return ONLY vendors in scope)
	 * @param filePath -- Path to file being read in.
	 * @param area -- Area of the vendor.
	 * @param inScope -- Whether or not the vendor is in scope of the insurance.
	 * @param vendorRating -- The rating of the vendor.
	 * @return -- List of vendors within a given area, filtered by scope and vendor rating.
	 */
	public List<Vendor> getVendorsWithGivenRatingThatAreInScope(String filePath, String area, boolean inScope, int vendorRating) {
		
		// read list of vendors from csv
		List<Vendor> vendors = readCsvFile(filePath, Vendor.class);
		
		// declare list of rating-scoped vendors
		List<Vendor> ratingScopeVendors = new ArrayList<Vendor>();
		
		// iterate through each vendor
		for (Vendor vendor : vendors) {
			// check if the areas are equal, the user sorts by not in scope or both are in scope, and the rating is less than the vendor's rating
			if (area.equals(vendor.getArea()) && (!inScope || inScope == vendor.isInScope()) && vendorRating <= vendor.getVendorRating()) {
				ratingScopeVendors.add(vendor);
			}
		}
		
		// return the list of rating-scoped vendors
		return ratingScopeVendors;
	}


	/* #8
	 * getUndisclosedDrivers() -- Return a list of customers between the age of 40 and 50 years (inclusive), who have:
			a.	More than X cars
			b.	less than or equal to X number of dependents.
	 * @param filePath -- Path to file being read in.
	 * @param vehiclesInsured -- The number of vehicles insured.
	 * @param dependents -- The number of dependents on the insurance policy.
	 * @return -- List of customers filtered by age, number of vehicles insured and the number of dependents.
	 */
	public List<Customer> getUndisclosedDrivers(String filePath, int vehiclesInsured, int dependents) {
		
		// read list of customers from csv
		List<Customer> customers = readCsvFile(filePath, Customer.class);
		
		// declare list of undisclosed drivers
		List<Customer> undisclosedDrivers = new ArrayList<Customer>();
		
		// iterate through each customer
		for (Customer customer : customers) {
			// skip if the customer is not the correct age
			if (customer.getAge() < 40 || customer.getAge() > 50) {
				continue;
			}
			// check if the number of vehicles insured and dependents are appropriate
			if (customer.getVehiclesInsured() > vehiclesInsured && customer.getDependents().size() <= dependents) {
				undisclosedDrivers.add(customer);
			}
		}
		
		// return the list of drivers
		return undisclosedDrivers;
	}	


	/* #9
	 * getAgentIdGivenRank() -- Return the agent with the given rank based on average customer satisfaction rating. 
	 * *HINT* -- Rating is calculated by taking all the agent rating by customers (1-5 scale) and dividing by the total number 
	 * of reviews for the agent.
	 * @param filePath -- Path to file being read in.
	 * @param agentRank -- The rank of the agent being requested.
	 * @return -- Agent ID of agent with the given rank.
	 */
	public int getAgentIdGivenRank(String filePath, int agentRank) {
		
		// create map of agent ratings (the first element of the array is the total rating, and the second element is the number of ratings)
		HashMap<Integer, Integer[]> agentRating = new HashMap<Integer, Integer[]>();
		
		// read list of customers from csv
		List<Customer> customers = readCsvFile(filePath, Customer.class);
		
		// iterate through each customer
		for (Customer customer : customers) {
			// get the rating info from the map
			Integer[] ratingInfo = agentRating.get(customer.getAgentId());
			// if there is no info, initialize it to an empty list
			if (ratingInfo == null) {
				ratingInfo = new Integer[] {0, 0};
			} 
			
			// add the rating to the total rating
			ratingInfo[0] = ratingInfo[0] + customer.getAgentRating();
			// add the number of customers to the total customers
			ratingInfo[1] = ratingInfo[1] + 1;
			
			// update this value in the map
			agentRating.put(customer.getAgentId(), ratingInfo);
		}
		
		// initialize a list of ratings
		List<Double> ratings = new ArrayList<Double>();
		
		// iterate through the map, and add the rating to the list
		for (Map.Entry<Integer, Integer[]> entry : agentRating.entrySet()) {
		    ratings.add((1.0 * entry.getValue()[0] / entry.getValue()[1]));
		}
		
		// sort the list
		Collections.sort(ratings, Collections.reverseOrder());
		
		// get the appropriate rank
		double rating = ratings.get(agentRank - 1);
		
		// iterate through the map once more
		for (Map.Entry<Integer, Integer[]> entry : agentRating.entrySet()) {
			// find the rating that matches, and return this entry's key
		    if (rating == ((1.0 * entry.getValue()[0] / entry.getValue()[1]))) {
		    	return entry.getKey();
		    }
		}
		
		// return -1 if not found
		return -1;
	}	

	
	/* #10
	 * getCustomersWithClaims() -- Return a list of customers who’ve filed a claim within the last <numberOfMonths> (inclusive). 
	 * @param filePath -- Path to file being read in.
	 * @param monthsOpen -- Number of months a policy has been open.
	 * @return -- List of customers who’ve filed a claim within the last <numberOfMonths>.
	 */
	public List<Customer> getCustomersWithClaims(Map<String,String> csvFilePaths, short monthsOpen) {
		// read the list of claims and customers from the csvs
		List<Claim> claimList = readCsvFile(csvFilePaths.get("claimList"), Claim.class);
		List<Customer> custList = readCsvFile(csvFilePaths.get("customerList"), Customer.class);
		
		// initialize a list of customer claim
		List<Customer> custClaims = new ArrayList<Customer>();
		
		// iterate through each claim
		for (Claim claim : claimList) {
			// continue if the months open are valid
			if (claim.getMonthsOpen() <= monthsOpen) {
				// iterate through each customer
				for (Customer customer : custList) {
					// check if the customers IDs are equivalent and the claims contain the customer
					if (claim.getCustomerId() == customer.getCustomerId() && !custClaims.contains(customer)) {
						custClaims.add(customer);
					}
				}
			}
		}
		
		// return the list of customer claims
		return custClaims;	
	}		

}
