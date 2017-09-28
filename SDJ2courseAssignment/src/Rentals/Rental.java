package Rentals;

import java.io.Serializable;

import Customer.Customer;
import Customer.CustomerFactory;
import Vehicles.Vehicle;
import util.Date;

/**
 * a class that will be used to create rentals
 * @author mihai cristian pavel
 * @version 1.0
 */
public class Rental implements Serializable
{
   private Date pickUpTime;
   private Date returnTime;
   private Customer customer;
   private Vehicle vehicle;
   private String pickUpLocation;
   private String returnLocation;
   /**
    * A four-argument constructor for initializing a Rental object
    * @param vehicle the vehicle
    * @param customer the customer
    * @param pickUpTime the pick-up time
    * @param returnTime the return time
    * @param pickUpLocation the pick-up location
    * @param returnLocation the return location
    */
   public Rental(Vehicle vehicle, String firstName, String lastName, String licenseNumber,
         String address, String phoneNumber,Date pickUpTime,Date returnTime,String pickUpLocation,String returnLocation)
   {
      this.customer= CustomerFactory.getMeCustomer(firstName,lastName,
            address, phoneNumber);
      this.vehicle=vehicle;
      this.pickUpTime=pickUpTime;
      this.returnTime=returnTime;
      this.pickUpLocation=pickUpLocation;
      this.returnLocation=returnLocation;
   }
   /**
    * a set method for the pick-up time
    * @param pickUpTime the pick-up time for the rental
    */
   public void setPickUpTime(Date pickUpTime)
   {
      this.pickUpTime=pickUpTime;
   }
   /**
    * a set method for the return time
    * @param returnTime the return time for the rental
    */
   public void setReturnTime(Date returnTime)
   {
      this.returnTime=returnTime;
   }
   /**
    * a method for calculating the rental period in days
    * @return the number of days the vehicle is rented
    */
   public int rentalPeriod()
   {
      int count=0;
      Date temp=pickUpTime.copy();
      while(temp.isBefore(returnTime))
      {
         temp.nextDay();
         count++;
      }
      return count;
   }
   /**
    * a method for calculating the price for the  estimated price of the rental
    * @return estimated price of rental
    */
   public double calculateRentalPrice(int estimatedKm)
   {
      double sum=0;
      if(rentalPeriod()<=2){
         if(estimatedKm<=rentalPeriod()*100) 
            {
            sum=rentalPeriod()*vehicle.getPrice();
            }
         else
         {
            sum=rentalPeriod()*vehicle.getPrice()+((estimatedKm-100*rentalPeriod())/100)*vehicle.getPrice();
         }
      }
      else
      {
         if(estimatedKm<=rentalPeriod()*100)
         {
            sum=2*vehicle.getPrice()+(rentalPeriod()-2)*(vehicle.getPrice()*0.7);
         }
         else
         {
            sum=2*vehicle.getPrice()+(rentalPeriod()-2+((estimatedKm-100*rentalPeriod())/100))*(vehicle.getPrice()*0.7);
         }
      }
      return sum;
   }
   /**
    * a get method for the pick-up time
    * @return the pickUpTime object of type Date
    */
   public Date getPickUpTime()
   {
      return pickUpTime;
   }
   /**
    * a get method for the return time
    * @return the returnTime object of type Date
    */
   public Date getReturnTime()
   {
      return returnTime;
   }
   /**
    * a get method for the vehicle of the Rental
    * @return the vehicle of the Rental
    */
   public Vehicle getVehicle()
   {
      return vehicle;
   }
   /**
    * a get method for the customer of the rental
    * @return the customer that made the rental
    */
   public Customer getCustomer()
   {
      return customer;
   }
   /**
    * a get method for the pick-up location
    * @return the value of pickUpLocation
    */
   public String getPickUPLocation()
   {
      return pickUpLocation;
   }
   /**
    * a get method for the return location
    * @return the value of returnLocation
    */
   public String getReturnLocation()
   {
      return returnLocation;
   }
   /**
    * a equals method that returns true if the 2 objects are the same , or false if they are different
    */
   public boolean equals(Object obj)
   {
      if(!(obj instanceof Rental)) return false;
      Rental other=(Rental)obj;
      return pickUpTime.equals(other.pickUpTime) && returnTime.equals(other.returnTime) && customer.equals(other.customer) && vehicle.equals(other.vehicle) && pickUpLocation.equals(other.pickUpLocation) && returnLocation.equals(other.returnLocation);
   }
   /**
    * a toString method that will return the first name and last name of the customer, the vehicles registration number,the pick-up date and location and the return date and location
    */
   public String toString()
   {
      return customer+" "+vehicle+" "+pickUpTime+" "+pickUpLocation+" "+returnTime+" "+returnLocation;
   }
}
