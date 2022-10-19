import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class PoisedUtilities {
    /**
     * Method to display the menu options
     */
    public static void menuOption() {
        System.out.println("___________________________________________________________");
        System.out.println("WELCOME TO POISED CONSTRUCTION ");
        System.out.println("Please choose one of the following options");
        System.out.println("By Typing in one of the numbers '1' to '10' ");
        System.out.println("\n1.  Add New Project details");
        System.out.println("2.  View Ongoing Projects - Full Details");
        System.out.println("3.  View Ongoing Projects - Name and Number");
        System.out.println("4.  View Ongoing Projects Passed Due Date");
        System.out.println("5.  Search for Project - Full Details");
        System.out.println("6.  Update Ongoing Project Details");
        System.out.println("7.  Delete a Project ");
        System.out.println("8.  Finalize and Display Invoice ");
        System.out.println("9.  View Completed Projects - Name and Number");
        System.out.println("10. View Completed Projects Full Details");
        System.out.println("0. To exit\n ");
        System.out.println("Enter your menu choice: ");
    }

    /**
     * Method that ensure User enters the amounts in the correct numerical format
     * @return - the user entered amounts but only ing the correct format
     */
    public static double assignAmount(){
        //declare base variables
        boolean amountStop;
        double enteredAmount = 0;
        Scanner amountScanner;
        amountScanner = new Scanner(System.in);
        amountStop = false;

        //loops the code until the correct usable type of data is inputted
        do {
            try{
                System.out.println("Enter the Amount value in Numerical format eg 1000 or 1000.00");
                enteredAmount = Double.parseDouble(amountScanner.nextLine());
                break;
            }catch (Exception  e){
                System.out.println("Invalid entry");
            }
        }while (amountStop == false);
        return enteredAmount;

    }

    /**
     * Method that accepts the deadline/completion date in only the correct format
     * @return - the user entered date but only in the correct format
     */
    public static LocalDate assignDate() {
        //declare base variables
        boolean dateStop;
        LocalDate enteredDate;
        Scanner dateScanner;
        dateScanner = new Scanner(System.in);
        dateStop = false;
        enteredDate = null;

        //loops the code until the correct usable type of data is inputted
        do {
            try {
                System.out.println("Please Enter the date in the following format: eg 2010-12-31");

                enteredDate = LocalDate.parse(dateScanner.nextLine());

                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid entry");
            }
        } while (dateStop == false);
        return enteredDate;
    }





}
