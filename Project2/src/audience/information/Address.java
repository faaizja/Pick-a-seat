package audience.information;

import java.util.Scanner;

public class Address { // Address class to create a single String address
	final Scanner scan = new Scanner(System.in);
	String address;

	public Address(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
