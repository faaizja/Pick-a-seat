package audience.information;

import java.util.Random;

public class Audience extends Person {

	// variables (part of constructor)
	private static long ID; // A unique number for each audience member
	private static long seatNumber; // number of seat
	private static String section; // section of the theater (Orchestra, wheelchair, or balcony
	private static String phone; // user-entered phone number
	private static double initialPrice; // original price of ticket
	private static boolean eligibleSpecialOffer; // returns true if eligible for discount
	private static double specialOffer; // returns the amount of discount
	private static double newPrice; // final price at the end
	private static Address address; // object of address class

	// variables (not part of constructor & getters/setters
	private static String enteredAddress; // string address entered by user in inputdetails() that will be used to make
											// an object
	private int areaSelection; // int corresponding to area that will be set in section setter
	final static Random rand = new Random();
	// user input

	// CONSTRUCTOR
	public Audience(int age, String fullname, long ID, long seatNumber, String section, String phone,
			double initialPrice, boolean eligibleSpecialOffer, double specialOffer, double newPrice, Address address) {
		super(age, fullname); // Feilds extended from Person super class
		Audience.ID = ID;
		Audience.seatNumber = seatNumber;
		Audience.section = section;
		Audience.phone = phone;
		Audience.initialPrice = initialPrice;
		Audience.eligibleSpecialOffer = eligibleSpecialOffer;
		Audience.specialOffer = specialOffer;
		Audience.newPrice = newPrice;
		Audience.address = address;
	}

	// empty constructor
	public Audience() {
		super(0, null);
	}
	// GETTERS AND SETTERS

	public static long getID() {
		return ID;
	}

	public void setID(long iD) {
		long idNum = rand.nextLong(8999) + 1000; // all id numbers to be a random 4 digit number

		ID = idNum; // variable used to access id number outside this method
	}

	public static long getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(long seatNumber) {
		Audience.seatNumber = seatNumber;
	}

	public static String getSection() {
		return section;
	}

	public void setSection(String section) { // takes the int selection from isValidSection and converts it to string
												// section choice
		System.out.println(
				"\nWhich section would you like to book? (1/2/3)\n1 - Wheelchair $59\n2 - Orchestra $72\n3 - Balcony $89");
		isValidSection();// Call isValidFunction to validate user input
		// If statement to determine which section the user picks
		if (areaSelection == 1)
			section = "Wheelchair";
		else if (areaSelection == 2)
			section = "Orchestra";
		else if (areaSelection == 3)
			section = "Balcony";
		Audience.section = section;
	}

	public static String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		Audience.phone = phone;
	}

	public static double getInitialPrice() {
		return initialPrice;
	}

	public static void setInitialPrice() {
		switch (section) { // set initial price based off section selected by user
		case "Wheelchair":
			Audience.initialPrice = 59.0;
			break;
		case "Orchestra":
			Audience.initialPrice = 72.0;
			break;
		case "Balcony":
			Audience.initialPrice = 89.0;
			break;
		default:
			break;
		}

	}

	public static boolean getEligibleSpecialOffer() {
		return eligibleSpecialOffer;
	}

	// Check if user is eligible for a special Offer
	public static void setEligibleSpecialOffer(String eligibleSpecialOfferAnswer) {
		if (eligibleSpecialOfferAnswer.equals("y") || eligibleSpecialOfferAnswer.equals("Y"))
			eligibleSpecialOffer = true;
		else
			eligibleSpecialOffer = false;
	}

	public static double getSpecialOffer() {
		return specialOffer;
	}

	public static void setSpecialOffer() {
		System.out.println("Are you one of the following (y/n):\nChild\nStudent\nSenior");// Ask which special offer
																							// user has
		String yesOrNo = scan.next();
		scan.nextLine();
		setEligibleSpecialOffer(yesOrNo); // Call method to see if eligible

		if (getEligibleSpecialOffer()) // if method is true
		{
			System.out.println("Are you a student? (y/n)");
			String studentOrNot = scan.nextLine();

			if ((studentOrNot.equals("y") || studentOrNot.equals("Y")) && (age > 0 && age < 19)) // student and child
			{
				Audience.specialOffer = 0.5; // 50% off
			} else if ((studentOrNot.equals("y") || studentOrNot.equals("Y")) && (age > 59)) // student and senior
			{
				Audience.specialOffer = 0.4; // 40% off
			} else if ((age > 0 && age < 19)) // just a child
			{
				Audience.specialOffer = 0.4; // 40% off
			} else if (age > 59) // just a senior
			{
				Audience.specialOffer = 0.3; // 30% off
			} else if ((studentOrNot.equals("y") || studentOrNot.equals("Y")))
				Audience.specialOffer = 0.1;
		} else
			Audience.specialOffer = 0; // if not true, set nothing // 0% off

	}

	public static double getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(double newPrice) {
		Audience.newPrice = newPrice;
	}

	public static Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		Audience.address = address;
	}

	public void inputDetails() { // method to have user input all details
		System.out.println("Hello!\n");

		System.out.println("Let's start with your full name, then press enter");
		fullname = scan.nextLine();
		setFullname(fullname); // set fullname to the one entered

		System.out.println("Please enter your age");
		age = scan.nextInt();
		setAge(age); // set age to the one entered
		scan.nextLine();

		System.out.println("Please enter your address");
		enteredAddress = scan.nextLine(); // *******this gets skipped when the code runs for some reason******
		Audience.address = new Address(enteredAddress); // set address to the one entered

		System.out.println("Please enter your phone number: (ddd ddd dddd)");
		phone = scan.nextLine();
		setPhone(phone); // set age to the one entered

		displayIDnumber();
	}

	public boolean isValidSection() { // validates user section choice and returns true
		do {

			areaSelection = scan.nextInt();
			// If statement for user validation
			if (areaSelection != 1 && areaSelection != 2 && areaSelection != 3)
				System.out.println("Incorrect selection!\nType in again");

		} while (areaSelection != 1 && areaSelection != 2 && areaSelection != 3); // keep going if invalid selection

		System.out.println("Valid selection! Thank you!\n");

		return true;
	}

	public void displayIDnumber() { // display id number
		setID(ID);
		System.out.println("Your ID number is: " + ID + "\n");
	}

	public static double applyOffer() { // multiply the price by the discount %
		newPrice = initialPrice - (initialPrice * getSpecialOffer());

		return newPrice;
	}

	// Method to issue ticket for option 3
	public static void ticketIssuer() {
		setSpecialOffer(); // Call setSpecialOFfer
		System.out.println();

		System.out.println("Here's your ticket!\n");
		System.out.println("**********");
		System.out.println("ID: " + Audience.getID());
		System.out.println("Name: " + Audience.getFullname());
		System.out.println("Section: " + Audience.getSection());
		System.out.println("Seat Number: " + Audience.getSeatNumber());
		System.out.println("Address: " + Audience.enteredAddress);
		System.out.println("Phone Number: " + Audience.getPhone());
		System.out.println("Initial Price: " + Audience.getInitialPrice());
		System.out.println("Discount applied: " + getSpecialOffer() * 100 + "% off!");
		System.out.println("Final price: " + applyOffer());
		System.out.println("**********");
	}
} // end audience class