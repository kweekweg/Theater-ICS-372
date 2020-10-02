package ics372groupProject1;

import java.io.Serializable;
import java.util.Calendar;

public class StudentTicket extends Ticket implements Serializable{

	public StudentTicket(Show show, Calendar date, String creditCardNum, String customerID) {
		super(show, date, creditCardNum, customerID);
		this.setPrice(show.getStudentTicketPrice());
	}
	
	public int getType() {
		return Theater.STUDENT_TICKET;
	}
	
	public String toString() {
		String output = super.toString() + ", Type: Student Advance\n";
		return output;
	}
	
}
