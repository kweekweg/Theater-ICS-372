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
 * Represents a single client
 *
 */
public class Client implements Serializable,
		Matchable<String> {
	private static final long serialVersionUID = 1L;
	private String name;
	private String phone;
	private String address;
	private String clientID;
	private double balance;
	private static final String CLIENT_STRING = "C";
	private List<Show> showList = new LinkedList<Show>();

	/**
	* Creates a client with the given name, address, and phone
	* 
	* @param name    client name
	* @param address client address
	* @param phone   client phone
	*/
	public Client(String name, String address, String phone) {
		this.name = name;
		this.phone = phone;
		this.clientID = CLIENT_STRING + (MemberIdServer.instance()).getId();
		this.address = address;
		this.balance = 0;
	}

	/**
	* Getter for name
	* 
	* @return client name
	*/
	public String getName() {
		return name;
	}

	/**
	* getter for phone
	* 
	* @return client phone number
	*/
	public String getPhone() {
		return phone;
	}

	/**
	* Getter for address
	* 
	* @return client address
	*/
	public String getAddress() {
		return address;
	}

	/**
	* Getter for clientID
	* 
	* @return client identification number
	*/
	public String getClientID() {
		return clientID;
	}

	/**
	* Iterator for show list
	* 
	* @return the iterator for client's shows
	*/
	public Iterator<Show> getShowList() {
		return this.showList.iterator();
	}

	/**
	* Getter for balance
	* 
	* @return client balance
	*/
	public double getBalance(){
		return this.balance;
	}
  
	/**
	* Setter for name
	* 
	* @param newName client's new name
	*/
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	* Setter for address
	* 
	* @param newName client's new address
	*/
	public void setAddress(String newAddress) {
		this.address = newAddress;
	}

	/**
	* Setter for phone
	* 
	* @param newName Client's new phone
	*/
	public void setPhone(String newPhone) {
		this.phone = newPhone;
	}

	/**
	* adds a show into the client's show list
	* 
	* @param newShow show to be added
	* @return true iff the show was added succesfully else false
	*/
	public boolean addShow(Show newShow) {
		boolean output = false;
		if (this.showList.add(newShow)) {
			output = true;
		}
		return output;
	}

	/**
    * pays the client if the pay amount is 
    * less than or equal to client's balance
    * @param payAmount amount to be paid
    * @return truee iff the client was paid
    */
	public boolean payClient(double payAmount){
		boolean output = false;
		if(payAmount <= this.balance){
		this.balance = this.balance - payAmount;
			output = true;
		}
		return output;
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
	* String form of the Client with clinet's name, phone number, address, client
	* ID, balance and show list
	* 
	*/
    public String toString() {
		String string = "Client Name: " + this.name + ", Client Phone: " + this.phone + ", Client Address: " + this.address
			+ ", Client ID: " + this.clientID + "Client Balance: "+ this.balance +   "\n";
		string += "Shows: ";
		for (Iterator<Show> iterator = showList.iterator(); iterator.hasNext();) {
			Show show = (Show) iterator.next();
			string += show.toString();
		}
		string += "\n";
		return string;
	}
  
	public void addBalance(double balance) {
		this.balance += balance;
	}
}
