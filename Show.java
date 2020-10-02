/**
 * Works Cited (for the whole project - everything submitted)
 * Object-Oriented Analysis, Design and Implementation: An Integrated Approach by Brahma Dathan, Sarnath Ramnath 
 * @ author John Sniadajewski, Joseph Marden, Saajine Sathappan
 */
package ics372groupProject1;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Represents a single show
 */
public class Show implements Serializable,
Matchable<String> {
	private static final long serialVersionUID = 1L;
	private String clientID;
	private String name;
	private Calendar startDate;
	private Calendar endDate;
	private double ticketPrice;
	
	/**
	* Creates a show with the given clientID, name, startDate, endDate, ticketPrice
	* 
	* @param clientID  		client ID
	* @param name      		show name
	* @param startDate 		show start date
	* @param endDate   		show end date
	* @param ticketPrice	price of a regular ticket
	*/
	public Show(String clientID, String name, Calendar startDate, Calendar endDate, double ticketPrice) {
		this.clientID = clientID;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.ticketPrice = ticketPrice;
	}
	
	/**
	* Getter for client ID
	* 
	* @return client ID
	*/
	public String getClientID() {
		return this.clientID;
	}
	
	/**
	* Getter for show name
	* 
	* @return show name
	*/
	public String getName() {
		return this.name;
	}
	
	/**
	* Getter for show start date
	* 
	* @return show end date
	*/
	public Calendar getStartDate() {
		return this.startDate;
	}
	
	/**
	* Getter for show end date
	* 
	* @return show end date
	*/
	public Calendar getEndDate() {
		return this.endDate;
	}
	
	public double getTicketPrice() {
		return this.ticketPrice;
	}
	public double getAdvanceTicketPrice() {
		return this.ticketPrice * 0.7; 
	}
	public double getStudentTicketPrice() {
		return this.ticketPrice * 0.35;
	}
	
	/**
	* Setter for client ID
	* 
	* @param clientID client ID
	*/
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	
	/**
	* Setter for name
	* 
	* @param name show name
	*/
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	* Setter for show start date
	* 
	* @param startdate show start date
	*/
	public void setStartDate(Calendar startdate) {
		this.startDate = startdate;
	}
	
	/**
	* Setter for show end date
	* 
	* @param enddate show end date
	*/
	public void setEndDate(Calendar enddate) {
		this.endDate = enddate;
	}
	
	/**
	* checks if the show has a future end date
	* 
	* @return true iff the show has end dates in the future
	*/
	public boolean hasFutureDate() {
		boolean output = false;
		Calendar now = new GregorianCalendar();
		output = endDate.before(now);
		return !output;
	}
	
	/**
		* Returns true if and only if the supplied id is the same as the id of the
		* item.
		*/
		@Override
		public boolean matches(String id) {
			return (this.clientID.equals(id));
		}
	
	/**
	* returns date in month, date and year format
	* 
	* @param targetDate date to be converted
	* @return date as month, date and year
	*/
	public String getDate(Calendar targetDate) {
		int month = targetDate.get(Calendar.MONTH) + 1;
		return month + "/" + targetDate.get(Calendar.DATE) + "/" + targetDate.get(Calendar.YEAR);
	}
	
	/**
	* String form of show name, client ID, show start date and show end date
	* 
	*/
	public String toString() {
		return "Show name " + this.name + ", Client ID " + this.clientID + ", Show startdate " + getDate(startDate)
			+ ", Show enddate " + getDate(endDate) + ", Regular ticket price " + ticketPrice;
	}
	}			