package clintview;

import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;

import Vehicles.Vehicle;
import util.Date;

public class ClientView 
{
   private Scanner key;
   private GregorianCalendar calendar=new GregorianCalendar();
   
   public ClientView()
   {
      key = new Scanner(System.in);
   }
   
	public void menu()
	{
		System.out.println("1. Get all available vehicles within a given date interval");
		System.out.println("2. Get current bookings within a given date interval");
		System.out.println("3. Reserve a Vehicle");
		System.out.println("4. Return a vehicle");
		System.out.println("5. Complete booking process");
		System.out.println("6. Cancel reservation");
		System.out.println("0. Exit");
	}
	
   public void login()
   {
      System.out.println("Are you an administrator or desk clerk?");
      System.out.println("1. Administrator");
      System.out.println("2. Clerk");
   }

   public void insertDate(int x)
   {
      System.out.println("Insert " + x + "° date: ");
   }

   public void insertDate(String msg)
   {
      System.out.println("Insert " + msg + ": ");
   }

   public void showMessage(String msg)
   {
	   if(msg.equals("")){
		   System.out.println("No records found");
	   }
	   else{
		   System.out.println(msg);
	   }
   }
   
   public String readStringFromUser()
   {
      return key.nextLine();
   }
   
   public void getRegistrationNumber(){
	   System.out.println("Enter vehicles registration number");
   }
   public int readIntFromUser()
   {
	   boolean flag = true;
	   int nr = -1;
	   while(flag)
	   {
		   try
		   {
			  nr = key.nextInt();
			  flag = false;
		   }
		   catch(InputMismatchException e)
		   {
			   flag=true;
			   System.out.println("Enter an integer.");
			   key.nextLine();
		   }
	   }
	   return nr;
   }
   
   public Date getDate()
   {
	   
	   int day1, month1, year1, hour1, minute1;
	   
	   do{
		   insertDate("day");
		   day1 = readIntFromUser();
	   }while(day1<1 || day1>calendar.getMaximum(GregorianCalendar.DAY_OF_MONTH));
	   do{
	   insertDate("month");
	   month1 = readIntFromUser();
	   }while(month1<1 || month1>(calendar.getMaximum(GregorianCalendar.MONTH)+1));
	   do{
	   insertDate("year");
	   year1 = readIntFromUser();
	   }while(year1<(calendar.get(GregorianCalendar.YEAR)-1) || year1>(calendar.get(GregorianCalendar.YEAR)+1));
	   do{
	   insertDate("hour");
	   hour1 = readIntFromUser();
	   }while(hour1<calendar.getMinimum(GregorianCalendar.HOUR_OF_DAY) || hour1>calendar.getMaximum(GregorianCalendar.HOUR_OF_DAY));
	   do{
	   insertDate("minute");
	   minute1 = readIntFromUser();
	   }while(minute1!=0 && minute1!=15 && minute1!=30 && minute1!=45);

	   return new Date(day1, month1, year1, hour1, minute1);
   }
   
   public void showVehicleType(){
	   System.out.println("Select the desired type of vehicle ");
	   System.out.println("1. Family Cars");
	   System.out.println("2. Vans");
	   System.out.println("3. Moving Trucks");
	   System.out.println("4. AutoCamper");
   }
   
   public void printVehicles(Vehicle[] vehicles){
	   StringBuilder builder=new StringBuilder();
	   for(int i=0;i<vehicles.length;i++){
		   builder.append((i+1)+". ");
		   builder.append(vehicles[i]);
		   builder.append("\n");
	   }
	   System.out.println(builder.toString());
   }
   
   public String getCustomerData(String msg){
	   String temp;
	   System.out.println("Introduce customers "+msg);
	   temp=readStringFromUser();
	   return temp;
   }
}
