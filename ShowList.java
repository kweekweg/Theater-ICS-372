/**
 * Works Cited (for the whole project - everything submitted)
 * Object-Oriented Analysis, Design and Implementation: An Integrated Approach by Brahma Dathan, Sarnath Ramnath 
 * @ author John Sniadajewski, Joseph Marden, Saajine Sathappan
 */
package ics372groupProject1;
import java.util.*;
import java.io.*;

/**
 * The collection class for Show
 */
public class ShowList extends ItemList<Show, String> {
	private static ShowList showList;

	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private ShowList() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static ShowList instance() {
		if (showList == null) {
			return (showList = new ShowList());
		} else {
			return showList;
		}
	}

	/**
	 * Inserts a show into the collection
	 * 
	 * @param show the show to be inserted
	 * @return true iff the show could be inserted. Currently always true
	 */
	public boolean addShow(Show show) {
		return super.add(show);
	}

	/**
	 * Returns an iterator to all shows
	 * 
	 * @return iterator to the collection
	 */
	public Iterator<Show> getShows() {
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
			output.writeObject(showList);
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
			if (showList != null) {
				return;
			} else {
				input.defaultReadObject();
				if (showList == null) {
					showList = (ShowList) input.readObject();
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
	
	public Show getShowOnDate(Calendar date) {
		Calendar dateCheck = new GregorianCalendar();
		dateCheck.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DATE));
		
		Calendar showDates = new GregorianCalendar();
		
		for(Iterator<Show> iterator = getShows(); iterator.hasNext(); ){
			Show show = (Show)iterator.next();
		    	for(showDates.set(show.getStartDate().get(Calendar.YEAR), show.getStartDate().get(Calendar.MONTH), show.getStartDate().get(Calendar.DATE)); showDates.before(show.getEndDate()) == true; showDates.add(Calendar.DAY_OF_MONTH, 1))
				{
		    		if(showDates.get(Calendar.DATE) == dateCheck.get(Calendar.DATE) && showDates.get(Calendar.MONTH) == dateCheck.get(Calendar.MONTH) && showDates.get(Calendar.YEAR) == dateCheck.get(Calendar.YEAR)) {
		    			return show;
				}
		    }
		    if(show.getEndDate().get(Calendar.DATE) == dateCheck.get(Calendar.DATE) && show.getEndDate().get(Calendar.MONTH) == dateCheck.get(Calendar.MONTH) && show.getEndDate().get(Calendar.YEAR) == dateCheck.get(Calendar.YEAR)) {
	    			return show;
		    }		
		}
		
		return null;
	}
}
