/**
 * Works Cited(for the whole project - everything submitted)
 * Object-Oriented Analysis, Design and Implementation: An Integrated Approach by Brahma Dathan, Sarnath Ramnath
 * @author John Sniadajewski, Joseph Marden, Saajine Sathappan
 */
package ics372groupProject1;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
/**
 *	This class represents the theater as a whole, and contains collection classes for each of the objects
 *	associated with the theater. It also calls the appropriate methods for each object to add, remove,
 *	and manipulate the objects
 */
public class Theater implements Serializable{
	private static final long serialVersionUID = 1L;
	private CustomerList customerList;
	private ClientList clientList;
	private ShowList showList;
	private CreditCardList creditCardList;
	private TicketList ticketList;
	private ArrayList<Calendar> calendar = new ArrayList<Calendar>();
	private static Theater theater;
	public static final int REGULAR_TICKET = 0;
	public static final int ADVANCE_TICKET = 1;
	public static final int STUDENT_TICKET = 2;
	
	/**
	 * Constructor for Theater
	 * Takes no arguments and creates empty lists for each of the list collection
	 * objects in Theater
	 */
	private Theater() {
		customerList = CustomerList.instance();
		clientList = ClientList.instance();
		creditCardList = CreditCardList.instance();
		showList = ShowList.instance();
		ticketList = TicketList.instance();
	}
	
	/**
	 * Implementation of the singleton method
	 * Checks to see if a theater object exists and, if not, calls the contructor
	 * and if one does exist it returns the existing one instead
	 * @return	Theater	- either the existing theater or a newly instantiated one if one exists already
	 */
	public static Theater instance() {
		if (theater == null) {
			MemberIdServer.instance();
			return (theater = new Theater());
		} else {
			return theater;
		}
	}
	
	/**
	 * Takes the parameters required for instantiating a new client object
	 * and passes them to the constructor in client, then adds the newly
	 * instantiated client to the clientList object
	 * @param name		Client name
	 * @param phone		Client phone number
	 * @param address	Client address
	 * @return			Newly instantiated client
	 */
	public Client addClient(String name, String phone, String address) {
		Client client = new Client(name, phone, address);
		if (clientList.addClient(client)) {
			return(client);
		}
		return null;
	}
	
	/**
	 * Takes the clientID of the client to be removed as a parameter, 
	 * checks to make sure the client exists within the clientList object,
	 * and if it finds the client it makes sure they have no future shows booked and
	 * removes them if this is true
	 * @param clientID	the ID of the client to be removed
	 * @return			true if the client was removed successfully, false otherwise
	 */
	public boolean removeClient(String clientID) {
		Client client = clientList.search(clientID);
		if(client != null) {
			for(Iterator<Show> clientShows = client.getShowList(); clientShows.hasNext();) {
				
				Show show = (Show) clientShows.next();
				if (show.hasFutureDate()) {
					return false;
				}
			}
			return(clientList.removeClient(clientID));
		}
		return false;
	}
	
	
	/**
	 * 	Returns a string representation of all clients within clientList
 	 * 	@return	a string representation of all clients within clientList
 	 */
	public String listAllClients() {
		String output = clientList.toString();
		return output;
	}
	
	/**
	 * Takes the necessary parameters for instantiating a new customer and their
	 * credit card, and calls the constructors to instantiate the two objects
	 * Then adds the credit card to the customer and creditCardList, and customer
	 * to customerList. Returns the final customer object if it was successful
	 * and null if not
	 * @param name				The customers name
	 * @param phone				The customers phone number
	 * @param address			The customers address
	 * @param creditCardNum		The customers credit card number
	 * @param expirationDate	The customers credit card expiration date
	 * @return					the final customer object if it was successful
	 * 							and null if not
	 */
	public Customer addCustomer(String name, String phone, String address, 
			String creditCardNum, String expirationDate) {
		CreditCard creditCard = creditCardList.search(creditCardNum);
		if(creditCard != null) {
			return null;
		}
		Customer customer = new Customer(name, phone, address);
		creditCard = new CreditCard(customer.getCustomerID(), creditCardNum, expirationDate);
		customer.addCreditCard(creditCard);
		if (customerList.addCustomer(customer) && creditCardList.addCreditCard(creditCard)) {
			return(customer);
		}
		return null;
	}
	
	/**
	 * Takes the customerID of the customer to be removed as a parameter, 
	 * checks to make sure the customer exists within the customerList object,
	 * and if it finds the customer it removes the customer from customerList and
	 * any credit cards associated with them from creditCardList
	 * @param customerID	the ID of the customer to be removed
	 * @return				true if the customer was removed successfully, false otherwise
	 */
	public boolean removeCustomer(String customerID) {
		Customer customer = customerList.search(customerID);
		if (customer != null) {
			boolean customerResult = customerList.removeCustomer(customerID);
			boolean creditCardResult = creditCardList.removeCustomerCards(customerID);
			return (customerResult && creditCardResult);
		}
		return false;
	}
	
	/**
	 * Takes the credit card number, expiration date, and customerID of a credit card
	 * checks to make sure the credit card doesn't already exist, and
	 * instantiates a new creditCard object, then adds the creditCard to 
	 * creditCardList and the associated customer object
	 * @param customerID		The customer ID associated with the credit card
	 * @param creditCardNum		The credit card number
	 * @param expirationDate	The credit card expiration date
	 * @return					True if successful, false if not
	 */
	public CreditCard addCreditCard(String customerID, String creditCardNum, String expirationDate) {
		CreditCard creditCard = creditCardList.search(creditCardNum);
		if(creditCard != null) {
			return null;
		}
		Customer customer = customerList.search(customerID);
		if(customer != null) {
			creditCard = new CreditCard(customerID, creditCardNum, expirationDate);
			creditCardList.addCreditCard(creditCard);
			customerList.search(customerID).addCreditCard(creditCard);
			return(creditCard);
		}
		return null;		
	}
	
	/**
	 * Returns a string representation of all customers in customerList
	 * @return	A string representation of all customers in customerList
	 */
	public String listAllCustomers() {
		String output = customerList.toString();
		return output;
	}
	
	/**
	 * Takes the below parameters, checks the dates provided by the user against
	 * the dates currently scheduled in the calendar object by calling the checkDates method,
	 * and if the dates are valid it creates a new Show object, then adds it to the
	 * associated client object and adds it to the showList collection
	 * @param name			The name of the show
	 * @param clientID		The client ID booking the show
	 * @param startDate		The start date of the show
	 * @param endDate		The end date of the show
	 * @return				The instantiated show if successful, null if not
	 */
	public Show addShow(String name, String clientID, Calendar startDate, Calendar endDate, double price) {
		Client client = clientList.search(clientID);
		if(client != null && this.checkDates(startDate, endDate)) {
			Calendar addDates = new GregorianCalendar();
			for(addDates.set(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DATE)); addDates.before(endDate) == true; addDates.add(Calendar.DATE, 1)) {
				Calendar newDate = new GregorianCalendar();
				newDate.set(addDates.get(Calendar.YEAR), addDates.get(Calendar.MONTH), addDates.get(Calendar.DATE));
				calendar.add(newDate);
			}
			calendar.add(endDate);
			Show show = new Show(clientID, name, startDate, endDate, price);
			if(clientList.search(clientID).addShow(show) && showList.addShow(show))
				return show;
		}
		return null;
	}
	
	/**
	 * Returns a string representation of all shows in showList
	 * @return	A string representation of all shows in showList
	 */
	public String listAllShows() {
		String output = showList.toString();
		return output;
	}
	
	/**
	 * searches for a given client 
	 * @param clientID ID of the client
	 * @returntrue iff the member is in the client list collection
	 */
	public Client searchClient(String clientID){		
		return clientList.search(clientID);		
	}

	/**
	 * returns the balance for the given client
	 * @param client client to check balance
	 * @return given client's balance
	 */
	public double clientBalance(Client client){	
		return client.getBalance();
	}

	/**
	 * method to pay a client 
	 * @param client client to be paid
	 * @param payAmount amount to be paid to the client
	 * @return true iff the client was paid
	 */
	public boolean payClient(Client client, double payAmount){
		return client.payClient(payAmount);
	}
	
	/**
	 * If a theater object does not currently exist, reads in a theater
	 * object from the saved data on the disk, as well as the MemberIdServer
	 * object, and returns the theater obtained from the disk
	 * @return	The theater object retrieved from disk
	 */
	public static Theater retrieve() {
		if(Theater.isNull()) {
			try {
				FileInputStream file = new FileInputStream("TheaterData");
				ObjectInputStream input = new ObjectInputStream(file);
				input.readObject();
				MemberIdServer.retrieve(input);
				return theater;
			} catch(IOException ioe) {
				ioe.printStackTrace();
				return null;
			} catch(ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	/**
	 * Saves the current theater object and memberIdServer to the disk
	 * @return	true if successful, false otherwise
	 */
	public static boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("TheaterData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(theater);
			output.writeObject(MemberIdServer.instance());
			return true;
		} catch(IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}
	
	/**
	 * The method called in save() to write the theater object to the disk
	 * @param output
	 */
	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(theater);
		} catch(IOException ioe) {
			System.out.println(ioe);
		}
	}
	
	/**
	 * The method called in retrieve() to read the theater object from the disk
	 * @param input
	 */
	private void readObject(java.io.ObjectInputStream input) {
		try {
			input.defaultReadObject();
		    if (theater == null) {
		    	theater = (Theater) input.readObject();
		    } else {
		    	input.readObject();
		    }
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Used in to make sure a theater object does not exist before reading one in
	 * from the disk
	 * @return	true if no theater currently exists, false otherwise
	 */
	public static boolean isNull() {
		if(theater == null)
			return true;
		return false;
	}
	
	/**
	 * Searches the customerList to see if a customer object based on customerID
	 * exists or not
	 * @param customerID	the customerID of the customer searched for
	 * @return				true if the customer exists, false otherwise
	 */
	public boolean checkCustomer(String customerID) {
		Customer customer = customerList.search(customerID);
		if(customer != null)
			return true;
		return false;
	}
	
	/**
	 * Removes a credit card object from both creditCardList and the associated customer
	 * based on the below parameters
	 * @param customerID		The ID of the customer who the credit card belongs to
	 * @param creditCardNumber	The number of the credit card
	 * @return					True if successful, false otherwise
	 */
	public boolean removeCreditCard(String customerID, String creditCardNumber) {
		Customer customer = customerList.search(customerID);
		if(customer != null) {
			if(customer.numberOfCreditCards() > 1) {
				return (customer.removeCreditCard(creditCardNumber) && creditCardList.removeCreditCard(creditCardNumber));
			}
			return false;
		}
		return false;
	}

	/**
	 * Takes a start date and an end date and checks the inclusive range of those dates
	 * against all dates currently in the calendar object in the theater, and returns
	 * true if there are no collisions, otherwise returns false
	 * @param startDate	The first date in the range to check
	 * @param endDate	The last date in the range to check
	 * @return			true if no collisions with dates in calendar, false otherwise
	 */
	public boolean checkDates(Calendar startDate, Calendar endDate) {
		Calendar dateCheck = new GregorianCalendar();
		dateCheck.set(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DATE));
		for(dateCheck.set(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DATE)); dateCheck.before(endDate) == true; dateCheck.add(Calendar.DAY_OF_MONTH, 1))
		{
			for(int i = 0; i < calendar.size(); i++) {
				if(calendar.get(i).get(Calendar.DATE) == dateCheck.get(Calendar.DATE) && calendar.get(i).get(Calendar.MONTH) == dateCheck.get(Calendar.MONTH) && calendar.get(i).get(Calendar.YEAR) == dateCheck.get(Calendar.YEAR)) {
					return false;
				}
			}
		}
		dateCheck.add(Calendar.DAY_OF_MONTH, 1);
		for(int i = 0; i < calendar.size(); i++) {
			if(calendar.get(i).get(Calendar.DATE) == dateCheck.get(Calendar.DATE) && calendar.get(i).get(Calendar.MONTH) == dateCheck.get(Calendar.MONTH) && calendar.get(i).get(Calendar.YEAR) == dateCheck.get(Calendar.YEAR))
				return false;
		}
		return true;
	}
	
	/**
	 * Searches the clientList to see if a client object based on clientID
	 * exists or not
	 * @param customerID	the clientID of the client searched for
	 * @return				true if the client exists, false otherwise
	 */
	public boolean checkClient(String clientID) {
		Client client = clientList.search(clientID);
		if (client != null) 
			return true;
		return false;
	}
	
	/**
	 * Returns a string representation of the entire theater object by
	 * calling the toString methods of the collection classes inside
	 * theater
	 */
	public String toString() {
		String output = "";
		output += "Customers on file: \n" + customerList.toString();
		output += "Clients on file: \n" + clientList.toString();
		output += "Shows scheduled: \n" + showList.toString();
		output += "Tickets purchased: \n" + ticketList.toString();
		return output;
	}
	
	public String listAllTickets() {
		return ticketList.toString();
	}
	
	public boolean purchaseTicket(Calendar dateOfShow, String customerID, String creditCardNumber, int quantity, int type) {
		Customer customer = customerList.search(customerID);
		
		if (customer == null) {
			return false;
		}
		if (checkDates(dateOfShow, dateOfShow)) {
			return false;
		}
		CreditCard creditCard = creditCardList.search(creditCardNumber);
		if (creditCard == null) {
			return false;
		}
		if (creditCard.getCustomerID().compareTo(customerID) != 0) {
			return false;
		}
		if (type == REGULAR_TICKET) {
			Calendar currentDate = Calendar.getInstance();
			if(currentDate.get(Calendar.DATE) != dateOfShow.get(Calendar.DATE) || currentDate.get(Calendar.MONTH) != dateOfShow.get(Calendar.MONTH) || currentDate.get(Calendar.YEAR) != dateOfShow.get(Calendar.YEAR)) {
				return false;
			}
		}
		Show show = showList.getShowOnDate(dateOfShow);
		double totalPrice = 0;
		for(int i = 0; i < quantity; i++) {
			Ticket ticket = TicketFactory.instance().createTicket(show, dateOfShow, customerID, creditCardNumber, type);
			ticketList.addTicket(ticket);
			customer.addTicket(ticket);
			totalPrice += ticket.getPrice();
		}
		
		Client client = clientList.search(show.getClientID());
		client.addBalance(0.5 * totalPrice);
		
		return true;
	}
}
