package ics372groupProject1;

import java.io.Serializable;
import java.util.Calendar;

public class RegularTicket extends Ticket implements Serializable{
	
	public RegularTicket(Show show, Calendar date, String creditCardNum, String customerID) {
		super(show, date, creditCardNum, customerID);
		this.setPrice(show.getTicketPrice());
	}
	
	public int getType() {
		return Theater.REGULAR_TICKET;
	}
	
	public String toString() {
		String output = super.toString() + ", Type: Regular\n";
		return output;
	}
	
	
}
