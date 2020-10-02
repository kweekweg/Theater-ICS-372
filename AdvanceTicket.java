package ics372groupProject1;

import java.io.Serializable;
import java.util.Calendar;

public class AdvanceTicket extends Ticket implements Serializable{
	
	public AdvanceTicket(Show show, Calendar date, String creditCardNum, String customerID) {
		super(show, date, creditCardNum, customerID);
		this.setPrice(show.getAdvanceTicketPrice());
	}
	
	public int getType() {
		return Theater.ADVANCE_TICKET;
	}
	
	public String toString() {
		String output = super.toString() + ", Type: Advance\n";
		return output;
	}
}
