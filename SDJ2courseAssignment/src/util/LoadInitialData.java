package util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import Adaptor.MyFileIO;
import Adaptor.MyTextFileIO;
import Rentals.RentalList;
import Vehicles.AutoCamper;
import Vehicles.FamilyCar;
import Vehicles.MovingTruck;
import Vehicles.Van;
import Vehicles.VehicleList;

/**
 * A class that will add vehicle objects to the vehicle list
 * 
 * @author ISergiu
 * @version 1.0
 */
public class LoadInitialData
{

   public static void main(String[] args)
   {
      VehicleList vehicles = new VehicleList();

      MyTextFileIO mtfio = new MyTextFileIO();
      String[] autoCamperArray = null;
      String[] vanArray = null;
      String[] familyCarArray = null;
      String[] movingTruckArray = null;

      try
      {  
         
         autoCamperArray = mtfio.readArrayFromFile("txtFiles/autoCampers.txt");
         vanArray = mtfio.readArrayFromFile("txtFiles/vans.txt");
         familyCarArray = mtfio.readArrayFromFile("txtFiles/familyCars.txt");
         movingTruckArray = mtfio.readArrayFromFile("txtFiles/movingTrucks.txt");

         
         
         for (int i = 0; i < autoCamperArray.length; i++)
         {
            String temp = autoCamperArray[i];
            String[] tempArr = temp.split(",");

            String make = tempArr[0];
            String model = tempArr[1];
            String year = tempArr[2];
            String color = tempArr[3];
            String regNo = tempArr[4];
            String fuelType = tempArr[5];
            String mileage = tempArr[6];
            String transmission = tempArr[7];
            String location = tempArr[8];
            String price = tempArr[9];
            String capacity = tempArr[10];

            double p = Double.parseDouble(price);
            int ma = Integer.parseInt(mileage);
            int y = Integer.parseInt(year);
            int cap = Integer.parseInt(capacity);

            vehicles.addVehicle(new AutoCamper(make, model, y, color, regNo,
                  fuelType, ma, transmission, location, p, cap));
         }
         for (int i = 0; i < vanArray.length ; i++)
         {
            String temp = vanArray[i];
            String[] tempArr = temp.split(",");

            String make = tempArr[0];
            String model = tempArr[1];
            String year = tempArr[2];
            String color = tempArr[3];
            String regNo = tempArr[4];
            String fuelType = tempArr[5];
            String mileage = tempArr[6];
            String transmission = tempArr[7];
            String location = tempArr[8];
            String price = tempArr[9];
            String loadSize = tempArr[10];

            double p = Double.parseDouble(price);
            int ma = Integer.parseInt(mileage);
            int y = Integer.parseInt(year);
            int ls = Integer.parseInt(loadSize);

            vehicles.addVehicle(new Van(make, model,y,color,regNo,fuelType,
                  ma,transmission,location,p,ls));
         }
         for (int i = 0; i < familyCarArray.length; i++)
         {
            String temp = familyCarArray[i];
            String[] tempArr = temp.split(",");

            String make = tempArr[0];
            String model = tempArr[1];
            String year = tempArr[2];
            String color = tempArr[3];
            String regNo = tempArr[4];
            String fuelType = tempArr[5];
            String mileage = tempArr[6];
            String transmission = tempArr[7];
            String location = tempArr[8];
            String price = tempArr[9];

            double p = Double.parseDouble(price);
            int ma = Integer.parseInt(mileage);
            int y = Integer.parseInt(year);

            vehicles.addVehicle(new FamilyCar(make,model,y,color,regNo,
                  fuelType,ma,transmission,location,p));
         }
         for (int i = 0; i < movingTruckArray.length; i++)
         {
            String temp = movingTruckArray[i];
            String[] tempArr = temp.split(",");

            String make = tempArr[0];
            String model = tempArr[1];
            String year = tempArr[2];
            String color = tempArr[3];
            String regNo = tempArr[4];
            String fuelType = tempArr[5];
            String mileage = tempArr[6];
            String transmission = tempArr[7];
            String location = tempArr[8];
            String price = tempArr[9];
            String loadSize = tempArr[10];

            double p = Double.parseDouble(price);
            int ma = Integer.parseInt(mileage);
            int y = Integer.parseInt(year);
            int ls = Integer.parseInt(loadSize);

            vehicles.addVehicle(new MovingTruck(make, model, y, color, regNo,
                  fuelType, ma, transmission, location, p, ls));
         }
      }
      catch (FileNotFoundException e)
      {
         System.out.println("File was not found, or could not be opened");
      }
      
      
      MyFileIO mfio = new MyFileIO();
      String fileName="vehicles.bin";
      
      String rentals="rentals.bin";
      String customers="customers.bin";
      
      RentalList allRentals=new RentalList();
     // CustomerList allCustomers=new CustomerList();

      try
      {
         mfio.writeToFile(fileName,vehicles);
       //  mfio.writeToFile(customers, allCustomers);
         mfio.writeToFile(rentals, allRentals);
      }
      catch (FileNotFoundException e)
      {
         System.out.println("Error opening file");
      }
      catch (IOException e)
      {
         System.out.println("IO Error writing to file");
         e.printStackTrace();
      }

      System.out.println("Done");

   }
}