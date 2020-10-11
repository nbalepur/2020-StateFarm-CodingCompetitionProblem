package sf.codingcompetition2020.structures;

public class Vendor {
	private int vendorId;
	private String area;
	private int vendorRating;
	private boolean inScope;
	
	
	
	public Vendor(String args) {
		// split our arguments by ","
		String[] arguments = args.split(",");
		
		// set our parameters appropriately
		vendorId = Integer.parseInt(arguments[0]);
		area = arguments[1];
		vendorRating = Integer.parseInt(arguments[2]);
		inScope = arguments[3].equals("true");
	}
	
	// -------------------- default constructor --------------------
	
	public Vendor(int vendorId, String area, int vendorRating, boolean inScope) {
		super();
		this.vendorId = vendorId;
		this.area = area;
		this.vendorRating = vendorRating;
		this.inScope = inScope;
	}
	
	// -------------------- getters and setters --------------------
	
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getVendorRating() {
		return vendorRating;
	}
	public void setVendorRating(int vendorRating) {
		this.vendorRating = vendorRating;
	}
	public boolean isInScope() {
		return inScope;
	}
	public void setInScope(boolean inScope) {
		this.inScope = inScope;
	}	
}
