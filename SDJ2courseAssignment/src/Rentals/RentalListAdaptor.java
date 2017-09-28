package Rentals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import util.Date;
import Adaptor.MyFileIO;
import Customer.Customer;

/**
 * A class responsible for readind and writing to bin file the Rental List
 * @author mihai cristian pavel
 * @version 1.0
 */
public class RentalListAdaptor implements Serializable
{
   private String fileName;
   private MyFileIO mfio;
   /**
    * A one-argument constructor that initializes the MyFileIO object and the name af the file name that we receive as a parameter
    * @param fileName the name of the file the RentalListAdaptor will work with
    */
   public RentalListAdaptor(String fileName)
   {
      mfio=new MyFileIO();
      this.fileName=fileName;
   }
   /**
    * a method for reading from the file the RentalList object
    * @return a RentalListObject
    */
   public RentalList getAllRentals()
   {
      RentalList rentals=null;
      try{
         rentals=(RentalList)mfio.readObjectFromFile(fileName);
      }
      catch(FileNotFoundException e)
      {
         System.out.println("File not found");
      }
      catch(ClassNotFoundException e)
      {
         System.out.println("Class not found");
      }
      catch (IOException e)
      {
         System.out.println("IO error reading file");
      }
      return rentals;
   }
   /**
    * a save method for writing the RentalList to the file
    * @param rentals the rentals list that needs to be written to file
    */
   public void saveRentalList(RentalList rentals)
   {
      try{
         mfio.writeToFile(fileName, rentals);
      }
      catch(FileNotFoundException e)
      {
         System.out.println("File not found");
      }
      catch (IOException e) 
      {
         System.out.println("IO error writing to file");
      }
   }
   /**
    * a method for adding a rental to the rental list and then saving the updated list to the file
    * @param rental the rental object needed to be added to the list
    */
   public void addRental(Rental rental)
   {
      RentalList temp=getAllRentals();
      temp.addRental(rental);
      saveRentalList(temp);
      getAllRentals();
   }
   /**
    * a method for deleting a specified rental by the parameter received and after saving the updated list to the file
    * @param rental the rental object that is needed to delete from the list
    */
   public void deleteRental(Rental rental)
   {
      RentalList temp=getAllRentals();
      temp.deleteRental(rental);
      saveRentalList(temp);
      getAllRentals();
   }
   /**
    * a method for deleting a specified rental by the parameter received and after saving the updated list to the file
    * @param firstName the first name of the customer it is looked for
    * @param lastName the last name of the customer it is looked for
    * @param pickUpDate the pick up date of the customer it is looked for
    */
   public void deleteRentalByNameAndPickUpDate(String firstName,String lastName,Date pickUpDate)
   {
      RentalList temp=getAllRentals();
      temp.deleteRentalByNameAndPickUpDate(firstName, lastName, pickUpDate);
      saveRentalList(temp);
      getAllRentals();
   }
   /**
    * a method for deleting a rental by searching the rental with the customer received as a parameter
    * @param customer the customer witch is searched for so his rental can be deleted
    */
   public void deleteRentalByCustomer(Customer customer)
   {
      RentalList list=getAllRentals();
      list.deleteRentalByCustomer(customer);
      saveRentalList(list);
      getAllRentals();
   }
}
