package ics372groupProject1;

/**
 * Works Cited (for the whole project - everything submitted)
 * Object-Oriented Analysis, Design and Implementation: An Integrated Approach by Brahma Dathan, Sarnath Ramnath 
 * @ author John Sniadajewski, Joseph Marden, Saajine Sathappan
 */
import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Represents a single customer
 *
 */
public class Customer implements Serializable,
Matchable<String> {
	private static final long serialVersionUID = 1L;
	private String name;
	private String phone;
	private String address;
	private String customerID;
	private static final String CUSTOMER_STRING = "M";
	private List<CreditCard> creditCards = new LinkedList<CreditCard>();
	private List<Ticket> ticketList = new LinkedList<Ticket>();

	/**
	 * Creates a customer with the given name, phone, and address
	 * 
	 * @param name    customer name
	 * @param phone   customer phone number
	 * @param address customer address
	 */
	public Customer(String name, String phone, String address) {
		this.name = name;
		this.phone = phone;
		this.customerID = CUSTOMER_STRING + (MemberIdServer.instance()).getId();
		this.address = address;
	}

	/**
	 * Getter for name
	 * 
	 * @return customer name
	 */
	public String getName() {
		return name;
	}

	/**
	 * getter for phone
	 * 
	 * @return customer phone number
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Getter for address
	 * 
	 * @return customer address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Getter for customerID
	 * 
	 * @return customer identification number
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * Iterator for show list
	 * 
	 * @return the iterator for customer's creditcards
	 */
	public Iterator<CreditCard> getCreditCards() {
		return (creditCards.iterator());
	}

	/**
	 * Setter for name
	 * 
	 * @param newName customer's new name
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * Setter for address
	 * 
	 * @param newName customer's new address
	 */
	public void setAddress(String newAddress) {
		address = newAddress;
	}

	/**
	 * Setter for phone
	 * 
	 * @param newName customers's new phone
	 */
	public void setPhone(String newPhone) {
		phone = newPhone;
	}

	/**
	 * adds a credit card into the customer's credit list
	 * 
	 * @param newCreditCard creditcard to be added
	 * @return true iff the credit card was added succesfully else false
	 */
	public boolean addCreditCard(CreditCard newCreditCard) {
		boolean output = false;
		if (this.creditCards.add(newCreditCard)) {
			output = true;
		}
		return output;
	}

	/**
	 * Removes a credit card from the customer's creditcard list
	 * 
	 * @param creditCardNumber credit card to be removed
	 * @return true iff credit card could be removed
	 */
	public boolean removeCreditCard(String creditCardNumber) {
		boolean output = false;
		for (Iterator<CreditCard> iterator = creditCards.iterator(); iterator.hasNext();) {
			CreditCard creditCard = (CreditCard) iterator.next();
			if (creditCard.getCreditCardNumber().equals(creditCardNumber)) {
				this.creditCards.remove(creditCard);
				output = true;
			}
		}
		return output;
	}

	/**
	 * returns number of creditcards for a customer
	 * 
	 * @return number of credit cards
	 */
	public int numberOfCreditCards() {
		int numberOfCards = 0;
		for (Iterator<CreditCard> iterator = creditCards.iterator(); iterator.hasNext();) {
			CreditCard creditCard = (CreditCard) iterator.next();
			if (creditCard.getCustomerID().equals(this.customerID)) {
				numberOfCards++;
			}
		}
		return numberOfCards;
	}
	  
	/**
	 * Returns true if and only if the supplied id is the same as the id of the
	 * item.
	 */
	@Override
	public boolean matches(String id) {
		return (this.customerID.equals(id));
	}

	/**
	 * String form of the customer with customer name, address, ID and credit card list
	 * 
	 */
	public String toString() {
		String string = "Name: " + name + ", Phone: " + phone + ", Address: " + address + ", Customer ID: " + customerID
			+ "\n";
		for (Iterator<CreditCard> iterator = creditCards.iterator(); iterator.hasNext();) {
			CreditCard creditCard = (CreditCard) iterator.next();
			string += "Credit Card: " + creditCard.getCreditCardNumber() + ", Exp date: " + creditCard.getExpirationDate()
				+ "\n";
		}
		for(Iterator<Ticket> iterator = ticketList.iterator();iterator.hasNext();) {
		Ticket ticket = (Ticket)iterator.next();
		string += ticket.toString();
	}
		return string;
	}
	
	public boolean addTicket(Ticket ticket) {
		return ticketList.add(ticket);
	}
	
}
