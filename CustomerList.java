/**
 * Works Cited (for the whole project - everything submitted)
 * Object-Oriented Analysis, Design and Implementation: An Integrated Approach by Brahma Dathan, Sarnath Ramnath 
 * @ author John Sniadajewski, Joseph Marden, Saajine Sathappan
 */
package ics372groupProject1;
import java.util.*;
import java.io.*;

/**
 * The collection class for Customer
 */
public class CustomerList extends ItemList<Customer, String> {
	private static CustomerList customerList;

	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private CustomerList() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static CustomerList instance() {
		if (customerList == null) {
			return (customerList = new CustomerList());
		} else {
			return customerList;
		}
	}

	/**
	 * Inserts a customer into the collection
	 * 
	 * @param customer the customer to be inserted
	 * @return true iff the customer could be inserted. Currently always true
	 */
	public boolean addCustomer(Customer customer) {
		return super.add(customer);
	}

	/**
	 * Removes a customer from the customer list
	 * 
	 * @param customerID customer to be removed
	 * @return true iff customer could be removed
	 */
	public boolean removeCustomer(String customerID) {
		Customer customer = search(customerID);
		if (customer == null) {
			return false;
		} else {
			return list.remove(customer);
		}
	}

	/**
	 * Returns an iterator to all customers
	 * 
	 * @return iterator to the customerList collection
	 */
	public Iterator<Customer> getCustomers() {
		return super.iterator();
	}

	/*
	 * Supports serialization
	 * 
	 * @param output the stream to be written to
	 */
	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(customerList);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/*
	 * Supports serialization
	 * 
	 * @param input the stream to be read from
	 */
	private void readObject(java.io.ObjectInputStream input) {
		try {
			if (customerList != null) {
				return;
			} else {
				input.defaultReadObject();
				if (customerList == null) {
					customerList = (CustomerList) input.readObject();
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
}
