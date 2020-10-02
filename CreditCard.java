/**
 * Works Cited (for the whole project - everything submitted)
 * Object-Oriented Analysis, Design and Implementation: An Integrated Approach by Brahma Dathan, Sarnath Ramnath 
 * @ author John Sniadajewski, Joseph Marden, Saajine Sathappan
 */
package ics372groupProject1;
import java.util.*;
import java.lang.*;
import java.io.*;
/**
 * Represents a single credit card
 *
 */
public class CreditCard implements Serializable,
Matchable<String> {
  private static final long serialVersionUID = 1L;
  private String customerID;
  private String creditCardNumber;
  private String expirationDate;

  /**
   * Creates a creditcard with the given customerID, creditcardNumber, and
   * expirationDate
   * 
   * @param customerID       customer ID
   * @param creditCardNUmber customer credit card number
   * @param expirationDate   credit card expiration date
   */
  public CreditCard(String customerID, String creditCardNumber, String expirationDate) {
    this.customerID = customerID;
    this.creditCardNumber = creditCardNumber;
    this.expirationDate = expirationDate;
  }

  /**
   * Getter for customer ID
   * 
   * @return customer ID
   */
  public String getCustomerID() {
    return this.customerID;
  }

  /**
   * getter for credit card number
   * 
   * @return credit card number of the customer
   */
  public String getCreditCardNumber() {
    return this.creditCardNumber;
  }

  /**
   * Getter for expirationDate
   * 
   * @return expiration Date of the credit card
   */
  public String getExpirationDate() {
    return this.expirationDate;
  }

  /**
   * Setter for customer ID
   * 
   * @param newCustomerID customer ID
   */
  public void setCustomerID(String newCustomerID) {
    this.customerID = newCustomerID;
  }

  /**
   * Setter for credit card number
   * 
   * @param newCreditCardNumber customer's new credit credit number
   */
  public void setCreditCard(String newCreditCardNumber) {
    this.creditCardNumber = newCreditCardNumber;
  }

  /**
   * Setter for expiration date
   * 
   * @param newExpirationDate customer's credit credit expiration date
   */
  public void setExpirationDate(String newExpirationDate) {
    this.expirationDate = newExpirationDate;
  }

  /**
	 * Returns true if and only if the supplied id is the same as the id of the
	 * item.
	 */
	@Override
	public boolean matches(String id) {
		return (this.creditCardNumber.equals(id));
	}

  /**
   * String form of the credit card with customer ID, creditcard number and expiration date.
   * 
   */
  public String toString() {
    return "Customer ID: " + this.customerID + ", Credit Card Number: " + this.creditCardNumber + ", Expiration Date: "
        + this.expirationDate + "\n";
  }
}
