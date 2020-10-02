/**
 * Works Cited (for the whole project - everything submitted)
 * Object-Oriented Analysis, Design and Implementation: An Integrated Approach by Brahma Dathan, Sarnath Ramnath 
 * @ author John Sniadajewski, Joseph Marden, Saajine Sathappan
 */
package ics372groupProject1;
import java.util.*;
import java.io.*;

/**
 * The collection class for CreditCard 
 */
public class CreditCardList extends ItemList<CreditCard, String> {
	private static CreditCardList creditCardList;

	/**
	 * Private constructor for singleton pattern
	 * 
	 */
	private CreditCardList() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static CreditCardList instance() {
		if (creditCardList == null) {
			return (creditCardList = new CreditCardList());
		} else {
			return creditCardList;
		}
	}

	/**
	 * Inserts a creditCard into the collection
	 * 
	 * @param creditcard the credit card to be inserted
	 * @return true iff the credit card could be inserted. Currently always true
	 */
	public boolean addCreditCard(CreditCard creditCard) {
		return super.add(creditCard);
	}

	/**
	 * Removes a credit card from the creditcard list
	 * 
	 * @param creditCardNumber credit card to be removed
	 * @return true iff credit card could be removed
	 */
	public boolean removeCreditCard(String creditCardNumber) {
		CreditCard creditCard = search(creditCardNumber);
		if (creditCard == null) {
			return false;
		} else {
			return list.remove(creditCard); 
		}
	}

	/**
	 * Returns an iterator to all credit cards
	 * 
	 * @return iterator to the creditCard list
	 */
	public Iterator<CreditCard> getCreditCards() {
		return super.iterator();
	}

	/**
	 * checks whether a credit card exists in the collection
	 * 
	 * @param creditCardNumber to be checked
	 * @return true iff credit card exists
	 */
	public boolean creditCardAlreadyExists(String creditCardNumber) {
		if (search(creditCardNumber) == null)
			return false;
		return true;
	}

	/**
	 * returns number of creditcards for the given customer
	 * 
	 * @param customerID customer ID for the customer
	 * @return number of credit cards
	 */
	public int numberOfCreditCardsForCustomer(String customerID) {
		int numberOfCreditCards = 0;
		for (Iterator<CreditCard> iterator = super.iterator(); iterator.hasNext();) {
			CreditCard creditCard = (CreditCard) iterator.next();
			if (creditCard.getCustomerID().equals(customerID))
				numberOfCreditCards++;
		}

		return numberOfCreditCards;
	}

	/*
	 * Supports serialization
	 * 
	 * @param output the stream to be written to
	 */
	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(creditCardList);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Supports serialization
	 * 
	 * @param input the stream to be read from
	 */
	private void readObject(java.io.ObjectInputStream input) {
		try {
			if (creditCardList != null) {
				return;
			} else {
				input.defaultReadObject();
				if (creditCardList == null) {
					creditCardList = (CreditCardList) input.readObject();
				} else {
					input.readObject();
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	/**
	 * removes all the creditCard for the given customerID
	 * @param customerID customerID to remove creditCards
	 * @return true iff credit card was removed for the customerID
	 */
	public boolean removeCustomerCards(String customerID) {
		int index = 0;
		boolean result = false;
		for (Iterator<CreditCard> iterator = super.iterator(); iterator.hasNext();) {
			CreditCard creditCard = (CreditCard) iterator.next();
			if (creditCard.getCustomerID().equals(customerID)) {
				list.remove(index);
				result = true;
			}
			index++;
		}

		return result;
	}
}
