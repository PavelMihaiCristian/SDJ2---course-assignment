package Rentals;

import java.io.Serializable;
import java.util.ArrayList;

import Customer.Customer;
import util.Date;

/**
 * A class that will contain a list of Rental objects
 * @author mihai cristian pavel
 * @version 1.0
 */
public class RentalList implements Serializable
{
   private ArrayList<Rental> rentals;
   /**
    * A no-argument constructor that is initializing the Rental list 
    */
   public RentalList()
   {
      rentals=new ArrayList<Rental>();
   }
   /**
    * a method for the rental list size
    * @return the number of rentals in the list
    */
   public int size()
   {
      return rentals.size();
   }
   /**
    * a method that will add the rental object received as a parameter
    * @param rental the rental that will be added
    */
   public void addRental(Rental rental)
   {
      if(checkRentalAvailability(rental))
      {
         rentals.add(rental);
      }
   }
   /**
    * a get method for a Rental object
    * @param index the position of the Rental object that will be returned by the method
    * @return the Rental object at the position given by the parameter index
    */
   public Rental getRentalAtIndex(int index)
   {
      if(index<size()) return rentals.get(index);
      else return null;
   }
   /**
    * a method for checking the rental period of a rental so is not overlapping the other rentals
    * @param rental the rental that we are checking
    * @return a boolean value true if the rental can be made ,or false if it's not possible 
    */
   public boolean checkRentalAvailability(Rental rental)
   {
      boolean flag=true;
      Date begin=rental.getPickUpTime();
      Date finnish=rental.getReturnTime();
      for(int i=0;i<rentals.size();i++)
      {
         Date b=rentals.get(i).getPickUpTime();
         Date f=rentals.get(i).getReturnTime();
         if(rental.getVehicle().equals(rentals.get(i).getVehicle()))
         {
         if(begin.isBefore(f) && !(begin.isBefore(b)))
         {
            flag=false;
            break;
         }
         if(finnish.isBefore(f) && !(finnish.isBefore(b)))
         {
            flag=false;
            break;
         }
         if(begin.isBefore(b) && !(finnish.isBefore(f)))
         {
            flag=false;
            break;
         }
         }
      }
      return flag;
   }
   /**
    * a get method for a rental that matches the first name, last name and given date by the parameters
    * @param firstName the first name searched
    * @param lastName the last name searched
    * @param pickUpDate the pick up date searched
    * @return the Rental object that matches the search criteria
    */
   public Rental getRentalByNameAndPickUpDate(String firstName,String lastName,Date pickUpDate)
   {
      Rental temp=null;
      for(int i=0;i<rentals.size();i++)
      {
         if(rentals.get(i).getCustomer().getFirstName().equals(firstName) && rentals.get(i).getCustomer().getLastName().equals(lastName) && 
               rentals.get(i).getPickUpTime().equals(pickUpDate))
         {
            temp=rentals.get(i);
            break;
         }
      }
      return temp;
   }
   /**
    * a get method for a rental with the firstName and LastName given
    * @param firstName the first name of the customer that made the rental
    * @param lastName the last name of the customer that made the rental
    * @return the rental that matches the parameters given 
    */
   public Rental getRentalByName(String firstName,String lastName)
   {
      Rental temp=null;
      for(int i=0;i<rentals.size();i++)
      {
         if(rentals.get(i).getCustomer().getFirstName().equals(firstName) && rentals.get(i).getCustomer().getLastName().equals(lastName))
         {
            temp=rentals.get(i);
            break;
         }
      }
      return temp;
   }
   /**
    * a get method for a specific Rental object
    * @param phoneNumber the phone number of the customer is looked for
    * @return the rental that matches the searching criteria
    */
   public Rental getRentalByPhoneNumber(String phoneNumber)
   {
      Rental temp=null;
      for(int i=0;i<rentals.size();i++)
      {
         if(rentals.get(i).getCustomer().getPhoneNumber().equals(phoneNumber))
         {
            temp=rentals.get(i);
            break;
         }
      }
      return temp;
   }
   /**
    * a method for deleting a specific rental given a parameter
    * @param rental the rental that is searched for so it can be deleted
    */
   public void deleteRental(Rental rental)
   {
      for(int i=0;i<rentals.size();i++)
      {
         if(rentals.get(i).equals(rental))
         {
            rentals.remove(i);
            break;
         }
      }
   }
   /**
    * a method for deleting a rental make by a customer that is given as a parameter
    * @param customer the customer we are looking for in the rental list so the rental made by him can be deleted
    */
   public void deleteRentalByCustomer(Customer customer)
   {
      for(int i=0;i<rentals.size();i++)
      {
         if(rentals.get(i).getCustomer().equals(customer))
         {
            rentals.remove(i);
            break;
         }
      }
   }
   /**
    * a method for deleting a rental at a specific index in the rental list
    * @param index the index from where the rental will be deleted
    */
   public void deleteRentalAtIndex(int index)
   {
      if(index<rentals.size())
         {
            rentals.remove(index);
         }
   }
   /**
    * a method for deleting a rental that match the first name last name and pick-up date
    * @param firstName the first name 
    * @param lastName the last name
    * @param pickUpDate the pick-up date
    */
   public void deleteRentalByNameAndPickUpDate(String firstName,String lastName,Date pickUpDate)
   {
      for(int i=0;i<rentals.size();i++)
      {
         if(rentals.get(i).getCustomer().getFirstName().equals(firstName) && rentals.get(i).getCustomer().getLastName().equals(lastName) && rentals.get(i).getPickUpTime().equals(pickUpDate))
         {
            rentals.remove(i);
         }
      }
   }
   /**
    * a toString method for returning all the rentals as string
    */
   public String toString()
   {
      String str="";
      for(int i=0;i<rentals.size();i++)
      {
         str+=rentals.get(i).toString()+"\n";
      }
      return str;
   }
}
