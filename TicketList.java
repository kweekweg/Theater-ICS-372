package ics372groupProject1;

import java.io.IOException;
import java.util.Iterator;

public class TicketList extends ItemList<Ticket, String> {
	private static TicketList ticketList;
	
	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private TicketList() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static TicketList instance() {
		if (ticketList == null) {
			return (ticketList = new TicketList());
		} else {
			return ticketList;
		}
	}

	/**
	 * Inserts a customer into the collection
	 * 
	 * @param customer the customer to be inserted
	 * @return true iff the customer could be inserted. Currently always true
	 */
	public boolean addTicket(Ticket ticket) {
		return super.add(ticket);
	}

	/**
	 * Returns an iterator to all customers
	 * 
	 * @return iterator to the customerList collection
	 */
	public Iterator<Ticket> getTickets() {
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
			output.writeObject(ticketList);
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
			if (ticketList != null) {
				return;
			} else {
				input.defaultReadObject();
				if (ticketList == null) {
					ticketList = (TicketList) input.readObject();
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
