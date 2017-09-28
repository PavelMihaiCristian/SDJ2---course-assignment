package Customer;

import java.util.HashMap;

public class CustomerFactory {
	private static HashMap<String, Customer> customers = new HashMap<String, Customer>();

	public static Customer getMeCustomer(String fName, String lName,
			String address, String phone) {

		Customer temp = customers.get(fName + lName + phone + "");

		if (temp == null) {
			temp = new Customer(fName, lName, address, phone);
			customers.put(fName + lName + phone + "", temp);
		}
		return temp;
	}

}
