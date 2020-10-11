package sf.codingcompetition2020.structures;

public class Claim {
	private int claimId;
	private int customerId;
	private boolean closed;
	private int monthsOpen;
	
	public Claim(String args) {
		String[] arguments = args.split(",");
		claimId = Integer.parseInt(arguments[0]);
		customerId = Integer.parseInt(arguments[1]);
		closed = arguments[2].equals("true");
		monthsOpen = Integer.parseInt(arguments[3]);
	}
	
	public Claim(int claimId, int customerId, boolean closed, int monthsOpen) {
		super();
		this.claimId = claimId;
		this.customerId = customerId;
		this.closed = closed;
		this.monthsOpen = monthsOpen;
	}
	
	public int getClaimId() {
		return claimId;
	}
	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public boolean isClosed() {
		return closed;
	}
	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	public int getMonthsOpen() {
		return monthsOpen;
	}
	public void setMonthsOpen(int monthsOpen) {
		this.monthsOpen = monthsOpen;
	}
	
	
	
}
