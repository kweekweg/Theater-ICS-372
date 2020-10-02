package ics372groupProject1;
/**
 * Works Cited(for the whole project - everything submitted)
 * Object-Oriented Analysis, Design and Implementation: An Integrated Approach by Brahma Dathan, Sarnath Ramnath
 * @author John Sniadajewski, Joseph Marden, Saajine Sathappan
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
/**
 * This class provides the user interface for the theater program, reads input from users, provides
 * the output, and calls methods from the theater class to perform computations
 * and create and delete new and existing objects
 */
public class UserInterface implements Serializable{
	  private static UserInterface userInterface;
	  private static Theater theater;
	  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	  private static final int EXIT = 0;
	  private static final int ADD_CLIENT = 1;
	  private static final int REMOVE_CLIENT = 2;
	  private static final int LIST_CLIENTS = 3;
	  private static final int ADD_CUSTOMER = 4;
	  private static final int REMOVE_CUSTOMER = 5;
	  private static final int ADD_CREDITCARD = 6;
	  private static final int REMOVE_CREDITCARD = 7;
	  private static final int LIST_CUSTOMERS = 8;
	  private static final int ADD_SHOW = 9;
	  private static final int LIST_SHOWS = 10;
	  private static final int PURCHASE_REGULAR_TICKETS = 11;
	  private static final int PURCHASE_ADVANCE_TICKETS = 12;
	  private static final int PURCHASE_STUDENT_TICKETS = 13;
	  private static final int PAY_CLIENT = 14;
	  private static final int LIST_TICKETS = 15;
	  private static final int SAVE = 16;
	  private static final int RETRIEVE = 17;
	  private static final int HELP = 18;
	  /**
	   * Made private for singleton pattern.
	   * Conditionally looks for any saved data. Otherwise, it gets
	   * a singleton Theater object.
	   */
	  private UserInterface() {
	    if (yesOrNo("Look for saved data and  use it?")) {
	    	retrieve();
	    }
	   }	  
	  /**
	   * Supports the singleton pattern
	   * 
	   * @return the singleton object
	   */
	  public static UserInterface instance() {
	    if (userInterface == null) {
	      return userInterface = new UserInterface();
	    } else {
	      return userInterface;
	    }
	  }
	  /**
	   * Gets a token after prompting
	   * 
	   * @param prompt - whatever the user wants as prompt
	   * @return - the token from the keyboard
	   * 
	   */
	  public String getToken(String prompt) {
	    do {
	      try {
	        System.out.println(prompt);
	        String line = reader.readLine();
	        StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
	        if (tokenizer.hasMoreTokens()) {
	          return tokenizer.nextToken();
	        }
	      } catch (IOException ioe) {
	        System.exit(0);
	      }
	    } while (true);
	  }
	  /**
	   * Queries for a yes or no and returns true for yes and false for no
	   * 
	   * @param prompt The string to be prepended to the yes/no prompt
	   * @return true for yes and false for no
	   * 
	   */
	  private boolean yesOrNo(String prompt) {
	    String more = getToken(prompt + " (Y|y)[es] or anything else for no");
	    if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
	      return false;
	    }
	    return true;
	  }
	  /**
	   * Converts the string to a number
	   * @param prompt the string for prompting
	   * @return the integer corresponding to the string
	   * 
	   */
	  public int getNumber(String prompt) {
	    do {
	      try {
	        String item = getToken(prompt);
	        Integer number = Integer.valueOf(item);
	        return number.intValue();
	      } catch (NumberFormatException nfe) {
	        System.out.println("Please input a number ");
	      }
	    } while (true);
	  }
	  /**
	   * Prompts for a date and gets a date object
	   * @param prompt the prompt
	   * @return the data as a Calendar object
	   */
	  public Calendar getDate(String prompt) {
	    do {
	      try {
	        Calendar date = new GregorianCalendar();
	        String item = getToken(prompt);
	        DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
	        date.setTime(dateFormat.parse(item));
	        return date;
	      } catch (Exception fe) {
	        System.out.println("Please input a date as mm/dd/yy");
	      }
	    } while (true);
	  }
	  /**
	   * Prompts for a command from the keyboard
	   * 
	   * @return a valid command
	   * 
	   */
	  public int getCommand() {
	    do {
	      try {
	        int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
	        if (value >= EXIT && value <= HELP) {
	          return value;
	        }
	      } catch (NumberFormatException nfe) {
	        System.out.println("Enter a number");
	      }
	    } while (true);
	  }
	  /**
	   * Displays the help screen
	   * 
	   */
	  public void help() {
	    System.out.println("Enter a number between 0 and 12 as explained below:");
	    System.out.println(EXIT + " to Exit\n");
	    System.out.println(ADD_CLIENT + " to add a client");
	    System.out.println(REMOVE_CLIENT + " to remove a client");
	    System.out.println(LIST_CLIENTS + " to display a list of all clients");
	    System.out.println(ADD_CUSTOMER + " to add a customer");
	    System.out.println(REMOVE_CUSTOMER + " to remove a customer");
	    System.out.println(ADD_CREDITCARD + " to add a credit card");
	    System.out.println(REMOVE_CREDITCARD + " to remove a credit card");
	    System.out.println(LIST_CUSTOMERS + " to display a list of all customers");
	    System.out.println(ADD_SHOW + " to book a show");
	    System.out.println(LIST_SHOWS + " to display a list of all shows scheduled");
	    System.out.println(PURCHASE_REGULAR_TICKETS + " to purchase tickets the day of a show");
	    System.out.println(PURCHASE_ADVANCE_TICKETS + " to purchase tickets prior to the day of a show");
	    System.out.println(PURCHASE_STUDENT_TICKETS + " to purchase discounted student tickets prior to the day of a show. Student ID will be required at the door");
	    System.out.println(PAY_CLIENT + " to pay all of some of a client's balance to them");
	    System.out.println(LIST_TICKETS + " to display a list of all tickets purchased");
	    System.out.println(SAVE + " to  save data");
	    System.out.println(RETRIEVE + " to  retrieve data");
	    System.out.println(HELP + " for help");
	  }
	  /**
	   * Method to be called for adding a customer.
	   * Prompts the user for the appropriate values and
	   * uses the appropriate Theater method for adding the customer.
	   */
	  public void addCustomer() {
		  if(Theater.isNull()) {
			  theater = Theater.instance();
		  }
	    String name = getToken("Enter customer name");
	    String address = getToken("Enter address");
	    String phone = getToken("Enter phone");
	    String creditCardNum = getToken("Enter credit card number");
	    String expirationDate = getToken("Enter expiration date of credit card");
	    Customer result;
	    result = theater.addCustomer(name, address, phone, creditCardNum, expirationDate);
	    if (result == null) {
	      System.out.println("Could not add customer");
	    }
	    System.out.println(result.toString());
	  }
	  /**
	   * Method to be called for adding a client.
	   * Prompts the user for the appropriate values and
	   * uses the appropriate Theater method for adding the client.
	   *  
	   */
	  public void addClient() {
		  if(Theater.isNull()) {
			  theater = Theater.instance();
		  }
	    String name = getToken("Enter client name");
	    String address = getToken("Enter address");
	    String phone = getToken("Enter phone");
	    Client result;
	    result = theater.addClient(name, address, phone);
	    if (result == null) {
	      System.out.println("Could not add client");
	    }
	    System.out.println(result.toString());
	  }
	  /**
	   * Method to be called for adding a credit card.
	   * Prompts the user for the appropriate values and
	   * uses the appropriate Theater method for adding the credit card.
	   *  
	   */
	  public void addCreditCard() {
		  if(Theater.isNull()) {
			  theater = Theater.instance();
		  }
	    CreditCard result;
	    String customerID = getToken("Enter customer ID");
	    String creditCardNum = getToken("Enter credit card number");
	    String expirationDate = getToken("Enter expiration date of credit card");
	    result = theater.addCreditCard(customerID, creditCardNum, expirationDate);
	    if (result == null) {
	      System.out.println("Credit card could not be added");
	    } else {
	      System.out.println(result.toString());
	    } 
	  }
	 
	 /**
	  * Method to be called for adding a show.
	  * Prompts the user for the appropriate values and
	  * uses the appropriate Theater method for adding the show. 
	  */
	 public void addShow() {
		 if(Theater.isNull()) {
			  theater = Theater.instance();
		  }
		 Show result;
		 String clientID = getToken("Enter client ID");
		 Calendar startDate = getDate("Enter start date in mm/dd/yy format");
		 Calendar endDate = getDate("Enter end date in mm/dd/yy format");
		 while(endDate.before(startDate)) {
			 endDate = getDate("End date must be after start date");
		 }
		 String name = getToken("Enter the name of the show");
		 int price = getNumber("Enter the price of a regular ticket");
		 result = theater.addShow(name, clientID, startDate, endDate, (double) price);
		 if (result == null) {
			 System.out.println("Show could not be added");
		 } else {
			 System.out.println(result);
		 }
	 }
	 /**
	  * Calls the appropriate theater method to return a
	  * string representation of all clients and prints it to the
	  * output.
	  */
	 public void listAllClients() {
		 if(Theater.isNull()) {
			  theater = Theater.instance();
		  }
		 System.out.println(theater.listAllClients());
	 }
	 /**
	  * Calls the appropriate theater method to return a
	  * string representation of all customers and prints it to the
	  * output.
	  */
	 public void listAllCustomers() {
		 if(Theater.isNull()) {
			  theater = Theater.instance();
		  }
		 System.out.println(theater.listAllCustomers());
	 }
	 /**
	  * Calls the appropriate theater method to return a
	  * string representation of all shows and prints it to the
	  * output.
	  */
	 public void listAllShows() {
		 if(Theater.isNull()) {
			  theater = Theater.instance();
		  }
		 System.out.println(theater.listAllShows());
	 }
	 /**
	  * Method to remove a customer based off of the customer ID.
	  * Prompts the user for the customer ID then calls the theater
	  * method to remove all references to that customer.
	  */
	 public void removeCustomer() {
		 if(Theater.isNull()) {
			  theater = Theater.instance();
		  }
		  String customerID = getToken("Enter customer ID");
		  boolean result = theater.removeCustomer(customerID);
		  if (result) {
			  System.out.println("Successfully removed customer");
		  } else {
			  System.out.println("Unable to remove customer");
		  }
	  }
	 /**
	  * Method to remove a credit card based off of the customer ID and the
	  * credit card number.
	  * Prompts the user for the appropriate information then calls the 
	  * theater method to remove all references to that credit card.
	  */
	  public void removeCreditCard() {
		  if(Theater.isNull()) {
			  theater = Theater.instance();
		  }
		  String customerID = getToken("Enter customer ID");
		  String creditCardNum = getToken("Enter credit card number");
		  boolean result = theater.removeCreditCard(customerID, creditCardNum);
		  if (result) {
			  System.out.println("Successfully removed credit card");
		  } else {
			  System.out.println("Unable to remove credit card");
		  }
	  }
	  /**
		  * Method to remove a client based off of the client ID.
		  * Prompts the user for the appropriate information then calls the 
		  * theater method to remove all references to that client.
		  */
	  public void removeClient() {
		  if(Theater.isNull()) {
			  theater = Theater.instance();
		  }
		  String clientID = getToken("Enter client ID");
		  boolean result = theater.removeClient(clientID);
		  if (result) {
			  System.out.println("Successfully removed client");
		  } else {
			  System.out.println("Unable to remove client");
		  }
	  }
	  
	  public void purchaseTickets(int type) {
		  if(Theater.isNull()) {
			  theater = Theater.instance();
		  }
		  String customerID = getToken("Enter the customer ID");
		  Calendar dateOfShow = getDate("Enter the date of the show");
		  int quantity = getNumber("Enter the quantity desired");
		  String creditCardNumber = getToken("Enter the Credit Card Number to be used to purchase");
		  if (type == Theater.STUDENT_TICKET) {
			  System.out.println("NOTICE: Student ID will need to be shown on day of show for each studentvance ticket purchased");
		  }
		  boolean result = theater.purchaseTicket(dateOfShow, customerID, creditCardNumber, quantity, type);
		  if (result) {
			  System.out.println("Successfully purchased ticket(s)");
		  } else {
			  System.out.println("Failed to purchase tickets");
		  }
	  }
	  
	  public void listAllTickets() {
		  if(Theater.isNull()) {
			  theater = Theater.instance();
		  }
		 System.out.println(theater.listAllTickets());
	  }
	  /**
	   * Method to pay the client based off the client ID and pay amount
	   * requested by the client
	   * prompts the user to enter clientID and amount to be paid to
	   * the client
	   * Checks if the pay amount is less or equal to client balance 
	   * and true it pays the client by calling theater method to pay the client
	   */
	  public void payClient(){
		  //We should have a lot of this happen in Theater. You don't want user interface
		  //interacting with data at all. It should pass arguments and receive successful
		  //or failure messages
		if(Theater.isNull()) {
			theater = Theater.instance();
		}
		String clientID = getToken("Enter client ID");
		Client client = theater.searchClient(clientID);
		if (client == null){
			System.out.println("No such client");
			return;
		}
		else{
			double balance = theater.clientBalance(client);
			System.out.println("Available client balance " + balance);
			double payAmount = Double.parseDouble(getToken("Enter required amount"));
			boolean output = theater.payClient(client, payAmount);
			if (output == true) {
				System.out.println("Successfully paid");
			} else {
				System.out.println("Unable to pay client");
			}
		}		
	  }
	  /**
	   * Method to be called for saving the Theater object.
	   * Uses the appropriate Theater method for saving.
	   *  
	   */
	  private void save() {
		  if(Theater.isNull()) {
			  theater = Theater.instance();
		  }
	    if (theater.save()) {
	      System.out.println(" The theater has been successfully saved in the file TheaterData \n" );
	    } else {
	      System.out.println(" There has been an error in saving \n" );
	    }
	  }
	  /**
	   * Method to be called for retrieving saved data.
	   * Uses the appropriate Theater method for retrieval.
	   *  
	   */
	  private void retrieve() {
		  if(Theater.isNull()) {
			  try {
				  Theater tempTheater = Theater.retrieve();
				  if (tempTheater != null) {
					  System.out.println(" The theater has been successfully retrieved from the file TheaterData \n" );
					  theater = tempTheater;
				  } else {
					  System.out.println("File doesnt exist; creating new theater" );
					  theater = theater.instance();
				  }
			  } catch(Exception cnfe) {
				  cnfe.printStackTrace();
			  }
		  }
		  else
			  System.out.println("Data already exists, unable to load file");
	  }
	  /**
	   * Orchestrates the whole process.
	   * Calls the appropriate method for the different functionalities.
	   *  
	   */
	  public void process() {
	    int command;
	    help();
	    while ((command = getCommand()) != EXIT) {
	      switch (command) {
	        case ADD_CLIENT:        addClient();
	                                break;
	        case ADD_CUSTOMER:      addCustomer();
	                                break;
	        case REMOVE_CUSTOMER:   removeCustomer();
	                                break;
	        case REMOVE_CLIENT:     removeClient();
	                                break;
	        case ADD_CREDITCARD:    addCreditCard();
	                                break;
	        case REMOVE_CREDITCARD: removeCreditCard();
	                                break;
	        case ADD_SHOW:          addShow();
	                                break;
	        case LIST_CLIENTS:      listAllClients();
	                                break;
	        case LIST_CUSTOMERS:    listAllCustomers();
	                                break;
	        case LIST_SHOWS:   		listAllShows();
	                                break;
	        case PURCHASE_REGULAR_TICKETS:	purchaseTickets(0);
	        						break;
	        case PURCHASE_ADVANCE_TICKETS:	purchaseTickets(1);
									break;
	        case PURCHASE_STUDENT_TICKETS:	purchaseTickets(2);
									break;
	        case PAY_CLIENT:		payClient();
	        						break;
	        case LIST_TICKETS:		listAllTickets();
	        						break;
	        case SAVE:              save();
	                                break;
	        case RETRIEVE:          retrieve();
	                                break;
	        case HELP:              help();
	                                break;
	      }
	    }
	    Theater.save();
	  }
	  /**
	   * The method to start the application. Simply calls process().
	   * @param args not used
	   */
	  public static void main(String[] args) {
	    UserInterface.instance().process();
	  }
}

