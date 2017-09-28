package Customer;
import java.io.Serializable;
/**
 * 
 * Class to Create a customer
 *
 */
public class Customer implements Serializable
{
      private String firstName;
      private String lastName;
      private String licenseNumber;
      private String address;
      private String phoneNumber;
      
      /**
       * constructor to initialize the customer
       * @param firstName
       * @param lastName
       * @param licenseNumber
       * @param address
       * @param phoneNumber
       */
      public Customer(String firstName, String lastName, String licenseNumber,
            String address, String phoneNumber)
      {
         
         this.firstName = firstName;
         this.lastName = lastName;
         this.licenseNumber = licenseNumber;
         this.address = address;
         this.phoneNumber = phoneNumber;
      }
      
      public Customer(String firstName, String lastName,
              String address, String phoneNumber)
        {
           
           this.firstName = firstName;
           this.lastName = lastName;
           this.licenseNumber = "";
           this.address = address;
           this.phoneNumber = phoneNumber;
        }


      public String getFirstName()
      {
         return firstName;
      }


      public void setFirstName(String firstName)
      {
         this.firstName = firstName;
      }


      public String getLastName()
      {
         return lastName;
      }


      public void setLastName(String lastName)
      {
         this.lastName = lastName;
      }


      public String getLicenseNumber()
      {
         return licenseNumber;
      }


      public void setLicenseNumber(String licenseNumber)
      {
         this.licenseNumber = licenseNumber;
      }


      public String getAddress()
      {
         return address;
      }


      public void setAddress(String address)
      {
         this.address = address;
      }


      public String getPhoneNumber()
      {
         return phoneNumber;
      }


      public void setPhoneNumber(String phoneNumber)
      {
         this.phoneNumber = phoneNumber;
      }   
      /**
       * equals method gets an object and it checks
       * if it is equal to the customer
       */
      
      public String toString(){
    	  return firstName+" "+lastName+" ";
      }
      public boolean equals(Object obj)
      {
    	  if(!(obj instanceof Customer))
    	  {
    		  return false;
    	  }
    	  
    	  Customer other = (Customer)obj;
    	  return firstName.equals(other.firstName) && lastName.equals(other.lastName) && licenseNumber==other.licenseNumber &&
    			  address.equals(other.address) && phoneNumber==other.phoneNumber;
      }
}
