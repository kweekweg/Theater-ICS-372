package ics372groupProject1;
/**
 * Works Cited (for the whole project - everything submitted)
 * Object-Oriented Analysis, Design and Implementation: An Integrated Approach by Brahma Dathan, Sarnath Ramnath 
 * @ author John Sniadajewski, Joseph Marden, Saajine Sathappan
 */
import java.util.*;
import java.io.*;

/**
 * The collection class for Clients 
 */
public class ClientList extends ItemList<Client, String> {
	private static ClientList clientList;

	/**
	 * Private constructor for singleton pattern
	 * 
	 */
	private ClientList() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static ClientList instance() {
		if (clientList == null) {
			return (clientList = new ClientList());
		} else {
			return clientList;
		}
	}

	/**
	 * Inserts a client into the collection
	 * 
	 * @param client the client to be inserted
	 * @return true iff the client could be inserted. Currently always true
	 */
	public boolean addClient(Client client) {
		return super.add(client);
	}

	/**
	 * Removes a client from the clientlist
	 * 
	 * @param clientId id
	 * @return true iff client could be removed
	 */
	public boolean removeClient(String clientID) {
		Client client = search(clientID);
		if (client == null) {
			return false;
		} else {
			return super.remove(client);
		}
	}

	/**
	 * Returns an iterator to all clients
	 * 
	 * @return iterator to the collection
	 */
	public Iterator<Client> getClients() {
		return super.iterator();
	}

	/**
	 * Supports serialization
	 * 
	 * @param output the stream to be written to
	 */
	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(clientList);
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
			if (clientList != null) {
				return;
			} else {
				input.defaultReadObject();
				if (clientList == null) {
					clientList = (ClientList) input.readObject();
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
