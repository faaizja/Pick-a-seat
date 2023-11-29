package arts.center;

import java.util.*;
import audience.information.*; // import previous package

public class Ticket {
	// CLASS WIDE
	static ArrayList<Long> IdNumbers = new ArrayList<Long>(); // dynamic array to hold ID numbers
	static ArrayList<Long> takenSeats = new ArrayList<Long>(); // arraylist to hold the seats that are taken
	static Audience[][] audienceMembers; // array to hold all audience member objects
	static Audience newAudience;

	// VARIABLES
	static Scanner scan = new Scanner(System.in); // Scanner
	static int menuSelection; // the option the user chooses from menu to pass to call methods
	static String sectionChosen; // will receive the value from setSection method in Audience
	static int choice = 0; // user choice
	static String selectedSection = null; // section

	// MAIN
	public static void main(String[] args) {

		audienceMembers = new Audience[3][20]; // array that will hold all objects of audience
		// idx [0][0] = wheelchair, [1][0] = orchestra, [2][0] = balcony
		// initialize all indexes to null
		for (int x = 0; x < audienceMembers.length; x++) {
			for (int z = 0; z < audienceMembers[x].length; z++) {
				audienceMembers[x][z] = null;
			}
		}
		// pre made audience members' address
		Address mike = new Address("55 Street");
		Address mary = new Address("15 Street");
		Address joe = new Address("25 Street");
		Address jake = new Address("35 Street");
		Address bob = new Address("45 Street");
		Address emily = new Address("65 Street");
		Address claudio = new Address("75 Street");
		Address stormy = new Address("85 Street");
		Address jane = new Address("95 Street");

		// initialize 3 audience members from each section
		Audience MikeWheelchair = new Audience(20, "Mike mike", 1231, 7, "Wheelchar", "6044759982", 59, false, 0, 0,
				mike);
		Audience MaryWheelChair = new Audience(45, "Mary", 2312, 16, "Wheelchar", "7776662222", 59, false, 0, 0, mary);
		Audience JoeWheelChair = new Audience(34, "Joe", 3344, 2, "Wheelchar", "9987775552", 59, false, 0, 0, joe);
		Audience JakeOrchestra = new Audience(19, "Jake", 5564, 4, "Orchestra", "0098876655", 72, false, 0, 0, jake);
		Audience BobOrchestra = new Audience(76, "Bob", 9985, 8, "Orchestra", "1123343323", 72, false, 0, 0, bob);
		Audience EmilyOrchestra = new Audience(50, "Emily", 3764, 11, "Orchestra", "6675564435", 72, false, 0, 0, emily);
		Audience ClaudioBalcony = new Audience(35, "Claudio", 1023, 4, "Balcony", "8876669876", 89, false, 0, 0, claudio);
		Audience StormyBalcony = new Audience(23, "Stormy", 9976, 7, "Balcony", "0987807765", 89, false, 0, 0, stormy);
		Audience JaneBalcony = new Audience(39, "Jane", 4658, 14, "Balcony", "7765568899", 89, false, 0, 0, jane);

		// push ID nums to arraylist of ID nums
		IdNumbers.add(Audience.getID());
		IdNumbers.add(Audience.getID());
		IdNumbers.add(Audience.getID());
		IdNumbers.add(Audience.getID());
		IdNumbers.add(Audience.getID());
		IdNumbers.add(Audience.getID());
		IdNumbers.add(Audience.getID());
		IdNumbers.add(Audience.getID());
		IdNumbers.add(Audience.getID());

		// set locations for pre-determined audience members
		audienceMembers[0][7] = MikeWheelchair;
		audienceMembers[0][16] = MaryWheelChair;
		audienceMembers[0][2] = JoeWheelChair;
		audienceMembers[1][4] = JakeOrchestra;
		audienceMembers[1][8] = BobOrchestra;
		audienceMembers[1][11] = EmilyOrchestra;
		audienceMembers[2][4] = ClaudioBalcony;
		audienceMembers[2][7] = StormyBalcony;
		audienceMembers[2][14] = JaneBalcony;

		while (true) {
			displayMenu(); // first display menu which returns the int menu option
			callMethods(menuSelection, audienceMembers, newAudience); // receives the menu option and calls respective methods
		}

	} // end main

	// METHODS

	// DisplayMenu method will display the menu and ask for user input for which selection they would like
	public static int displayMenu() {

		System.out.println("Welcome to the booking system!\nChoose an option from the menu below:\n");
		System.out.println("1 - New Booking\n2 - Changing seat\n3 - Issue a ticket"
				+ "\n4 - Print all booked seats of each section\n5 - Exit");

		menuSelection = scan.nextInt();

		while (menuSelection < 1 || menuSelection > 5) { // validate user option
			System.out.println("Invalid option\n");
			System.out.println("1 - New Booking\n2 - Changing seat\n3 - Issue a ticket"
					+ "\n4 - Print all booked seats of each section\n5 - Exit");
			menuSelection = scan.nextInt(); // re-enter
		}

		return menuSelection; // Return user selection
	}

	// callMethods method is responsible for using the menuOption to call a different method
	public static void callMethods(int menuOption, Audience audienceMembers[][], Audience newAudience) {

		if (menuOption == 1) // Calls the method which books a new seat for option 1
		{
			newBooking(audienceMembers);
		} 
		else if (menuOption == 2) { // Calls the method which switches seats for option 2
			changeSeat(newAudience);
		} 
		else if (menuOption == 3) {// Calls the method which issues the ticket for option 3
			issueTicket();
		} 
		else if (menuOption == 4) {// Calls the method which prints all the booked seats for option 2
			printAllBookedSeats(audienceMembers);
		} 
		else if (menuOption == 5) {
			Exit();// Exit if menu option = 5
		} 
		else {
			System.out.println("Invalid selection!");// Validate user input
		}
	}

	// MENU OPTION METHODS

	public static void newBooking(Audience audienceMembers[][]) {
		// create new audience member
		newAudience = new Audience(); 			// Create object
		newAudience.inputDetails(); 			// add the input details to the Object
		IdNumbers.add(Audience.getID()); 		// add ID num to arraylist
		newAudience.setSection(sectionChosen); 	// Call setsection to find which section is chosen
		sectionChosen = Audience.getSection(); 	// returns the section chosen by user to string
		printSeatingPlan(audienceMembers); 		// prints seats
		pickSeat(newAudience); 					// user chooses a seat
		// make method to set seat number to what user picks for the new audience object
	}

	// Change seat method for if the user wants to change seats
	public static void changeSeat(Audience newAudience) {
		System.out.println("Enter your ID number: \n"); // Enter user ID for validation that the user has a seat
		
		long userID = scan.nextLong(); // Enter next Long
		boolean valid = false; // Boolean for validating
		int newSectionint = 0;
		String[] locations = { "Wheelchair: ", "Orchestra: ", "Balcony: " };// Locations

		for (int x = 0; x < audienceMembers.length; x++) // For loops to iterate through the array
		{
			for (int z = 0; z < audienceMembers[x].length; z++) {
				if (Audience.getID() == userID) {
					valid = true;
					break;
				}
			}
		}
		if (valid) { // If boolean = true then which do you move to
			System.out
					.println("Which section do you want to move to (1,2,3)? 1 - Wheelchair 2 - Orchestra 3 - Balcony");
			newSectionint = scan.nextInt();

			while (newSectionint > 3 || newSectionint < 1) {// While loop to validate user input
				System.out.println(
						"Invalid\nWhich section do you want to move to (1,2,3)? 1 - Wheelchair 2 - Orchestra 3 - Balcony");
				newSectionint = scan.nextInt();
			}
			// Switch statement to print all seats in each section
			switch (newSectionint) {
			case 1:
				for (int i = 0; i < 1; i++) {
					System.out.print(locations[i]);
					for (Integer x = 0; x < 20; x++) {
						if (audienceMembers[0][x] == null) {
							Integer seatNum = x + 1;
							System.out.print(seatNum.toString() + " ");
						} else
							System.out.print("X" + " ");
					}
					System.out.println();
				}
				;
				break;
			case 2:
				for (int i = 1; i < 2; i++) {
					System.out.print(locations[i]);
					for (Integer x = 0; x < 20; x++) {
						if (audienceMembers[1][x] == null) {
							Integer seatNum = x + 1;
							System.out.print(seatNum.toString() + " ");
						} else
							System.out.print("X" + " ");
					}
					System.out.println();
				}
				;
				break;
			case 3:
				for (int i = 2; i < 3; i++) {
					System.out.print(locations[i]);
					for (Integer x = 0; x < 20; x++) {
						if (audienceMembers[2][x] == null) {
							Integer seatNum = x + 1;
							System.out.print(seatNum.toString() + " ");
						} else
							System.out.print("X" + " ");
					}
					System.out.println();
				}
				;
				break;
			default:
			}
			pickSeat(newAudience);
		} // end if (valid)

		else
			System.out.println("Incorrect ID number!\nPlease complete booking through menu first\n");
	}

	public static void issueTicket() { // menu option 3 - prints a ticket
		System.out.println("Enter your ID number:\n");
		boolean validTicket = false;
		long enteredID = scan.nextLong();

		for (int x = 0; x < audienceMembers.length; x++) {
			for (int z = 0; z < audienceMembers[x].length; z++) {
				if (Audience.getID() == enteredID) {
					validTicket = true;
					break;
				}
			}
		}
		if (validTicket) {
			Audience.ticketIssuer();
		} else
			System.out.println("Invalid ticket number!\nBook a ticket through menu first\n");
	}

	public static void printAllBookedSeats(Audience audienceMembers[][]) { // display the section that user wants to see
																			// (menu option 4)
		String[] locations = { "Wheelchair: ", "Orchestra: ", "Balcony: " };
		int choice2 = 0;
		System.out.println("Which section would you like to check?\n 1-Wheelchair \n 2-Orchestra\n 3-Balcony");
		choice2 = scan.nextInt();

		while (choice2 < 1 || choice2 > 3) { // validate user choice
			System.out.println("Incorrect selection!");
			System.out.println("Which section would you like to check?\n 1-Wheelchair \n 2-Orchestra\n 3-Balcony");
			choice2 = scan.nextInt();
		}
		// Switch statement to print all the seats in each section
		switch (choice2) {
		case 1:
			for (int i = 0; i < 1; i++) {
				System.out.print(locations[i]);
				for (Integer x = 0; x < 20; x++) {
					if (audienceMembers[0][x] == null) {
						Integer seatNum = x + 1;
						System.out.print(seatNum.toString() + " ");
					} else
						System.out.print("X" + " ");
				}
				System.out.println();
			}
			;
			break;
		case 2:
			for (int i = 1; i < 2; i++) {
				System.out.print(locations[i]);
				for (Integer x = 0; x < 20; x++) {
					if (audienceMembers[1][x] == null) {
						Integer seatNum = x + 1;
						System.out.print(seatNum.toString() + " ");
					} else
						System.out.print("X" + " ");
				}
				System.out.println();
			}
			;
			break;
		case 3:
			for (int i = 2; i < 3; i++) {
				System.out.print(locations[i]);
				for (Integer x = 0; x < 20; x++) {
					if (audienceMembers[2][x] == null) {
						Integer seatNum = x + 1;
						System.out.print(seatNum.toString() + " ");
					} else
						System.out.print("X" + " ");
				}
				System.out.println();
			}
			;
			break;
		default:
		}
		System.out.println(" ");
	}

	public static void printSeatingPlan(Audience audienceMembers[][]) { // display seats for user to pick from through
																		// menu option 1
		String[] locations = { "Wheelchair: ", "Orchestra: ", "Balcony: " };// Each section
		// Switch statement to display all seats in each section
		switch (sectionChosen) {
		case "Wheelchair":
			for (int i = 0; i < 1; i++) {
				System.out.print(locations[i]);
				for (Integer x = 0; x < 20; x++) {
					if (audienceMembers[0][x] == null) {
						Integer seatNum = x + 1;
						System.out.print(seatNum.toString() + " ");
					} else
						System.out.print("X" + " ");
				}
				System.out.println();
			}
			;
			break;
		case "Orchestra":
			for (int i = 1; i < 2; i++) {
				System.out.print(locations[i]);
				for (Integer x = 0; x < 20; x++) {
					if (audienceMembers[1][x] == null) {
						Integer seatNum = x + 1;
						System.out.print(seatNum.toString() + " ");
					} else
						System.out.print("X" + " ");
				}
				System.out.println();
			}
			;
			break;
		case "Balcony":
			for (int i = 2; i < 3; i++) {
				System.out.print(locations[i]);
				for (Integer x = 0; x < 20; x++) {
					if (audienceMembers[2][x] == null) {
						Integer seatNum = x + 1;
						System.out.print(seatNum.toString() + " ");
					} else
						System.out.print("X" + " ");
				}
				System.out.println();
			}
			;
			break;
		default:
		}
	} // end method

	// Pick seat function which is called by newBooking
	public static void pickSeat(Audience NewAudience) {
		boolean userSeatFound = false;
		//While loop to ensure the user doesn't pick a spot thats already taken
		while (userSeatFound == false) {
			System.out.println("Choose a seat number (X is taken):");
			long userSeat = scan.nextLong();

			if (userSeat < 1 || userSeat > 20) {
				System.out.println("Invalid option!\nMust be between 1 and 20 (X is taken)\n");
				userSeat = scan.nextLong();
			} else {
				for (int z = 0; z < audienceMembers[choice].length; z++) {
					if (audienceMembers[choice][(int) userSeat - 1] == null) {
						System.out.println("Seat picked successfully!");
						NewAudience.setSeatNumber(userSeat);
						takenSeats.add(userSeat);
						userSeatFound = true;
						break;

					}
				}
			}
		}
	}

	// Exit method for option 5
	public static void Exit() {
		System.out.println("\nThanks for using!\nBye!");
		System.exit(0);
	}
	
} // end ticket class
