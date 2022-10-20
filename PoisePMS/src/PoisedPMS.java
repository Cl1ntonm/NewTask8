
/**
 * Task 8 Compulsory Task
 *
 * Task Purpose - modify previous level 2 capstone project to work with MySql
 *
 * Program Description - Program performs the following functions for Project management
 *                       .Adds new Projects withe details - to a file  to be recalled later
 *                       .View all ongoing Projects at once
 *                       .View condensed list of ongoing Projects
 *                       .View List if of Projects passed it due date
 *                       .Search for and view a particular project
 *                       .Update any selected ongoing Project detail
 *                       .Delete any ongoing Project
 *                       .Finalize a project by adding completion date and moving it to a new file
 *                       .View condensed list of Completed Projects
 *                       .View an in Detail list of Completed Projects
 *
 * Class Breakdown - Person class , holds Person Objects
 *                 - Project class , holds Project Objects
 *                 - PoisedUtilities , holds reusable methods for the program
 *                 - CrudFunctions , holds methods that can Create, Read, Update and Delete
 *                 - SqlQuerieMethods , holds String SQL query's to be accessed as needed
 *
 * @author ClintonM
 */

import com.mysql.cj.util.DnsSrv;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ListIterator;
import java.util.Scanner;



/**
 * Main class that executes the menu options
 */
public class PoisedPMS {

    public static void main(String[] args) throws SQLException {

        DB context = new DB();
        context.connect();

//        Project project = context.GetProject(1);
//        String projectName = project.getProjectName();
//        System.out.println(projectName);
//
//        Person person = context.GetPerson(1, 2);
//        String personName = person.getLastName();
//        System.out.println(personName);

        //declare base variables
        boolean closeProgram = false;
        ArrayList<Project> projectArrayList = new ArrayList<Project>();
        ArrayList<Project> ongoingProjects = new ArrayList<Project>();
        ArrayList<Project> completedProjects = new ArrayList<Project>();
        ArrayList<Project> lateProjects = new ArrayList<Project>();
        //ListIterator listIterator = null;


        //while loop for menu
        while (!closeProgram){
            Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
            // calling method to display the options available for the user
            PoisedUtilities.menuOption();
            try {
                switch (scanner.nextInt()) {
                    case 1:
                        //Add New Project details
                        System.out.println("Enter Project Name:");
                        String projectName = scanner.nextLine();
                        System.out.println("Enter Project Type:");
                        String projectType = scanner.nextLine();
                        System.out.println("Enter Project Address:");
                        String projectAddress = scanner.nextLine();
                        System.out.println("Enter Project ERF Number:");
                        String projectErfNumber = scanner.nextLine();
                        System.out.println("Enter Project Cost Amount:");
                        String projectCostAmount = scanner.nextLine();
                        System.out.println("Enter Project Paid Amount:");
                        String projectPaidAmount = scanner.nextLine();
                        System.out.println("Enter Project Deadline:");
                        String projectDeadline = scanner.nextLine();
                        System.out.println("Enter Project Completed Date:");
                        String projectCompletedDate = scanner.nextLine();
                        System.out.println("Is the Project Finalized y/n:");
                        String projectFinalized = scanner.nextLine();
                        if (projectFinalized == "y") {
                            projectFinalized = "yes";
                        } else if (projectFinalized == "n") {
                            projectFinalized = "no";
                        }
                        Project project = new Project(0, projectName, projectType, projectAddress,
                                projectErfNumber, Double.parseDouble(projectCostAmount), Double.parseDouble(projectPaidAmount),
                                LocalDate.parse(projectDeadline), projectFinalized, LocalDate.parse(projectCompletedDate), null, null,
                                null, null, null);
                        int projectNumber = context.CreateProject(project);

                        ArrayList<Role> projectRoles = new ArrayList<Role>();
                        projectRoles = context.GetAllRoles();
                        for (Role role : projectRoles) {
                            int roleID = role.getRoleID();
                            System.out.println("Enter "+ role.getRoleName() +" first name:");
                            String firstName = scanner.nextLine();
                            System.out.println("Enter "+ role.getRoleName() +" last name:");
                            String lastName = scanner.nextLine();
                            System.out.println("Enter "+ role.getRoleName() +" telephone:");
                            String telephone = scanner.nextLine();
                            System.out.println("Enter "+ role.getRoleName() +" email:");
                            String email = scanner.nextLine();
                            System.out.println("Enter "+ role.getRoleName() +" email:");
                            String address = scanner.nextLine();
                            Person person = new Person(0, projectNumber, roleID, firstName, lastName, telephone, email, address);
                            context.CreatePerson(person);
                        }
                        break;

                    case 2:
                        //View Ongoing Projects - Full Details
                        ongoingProjects = context.GetOngoingProjects();
                        System.out.println("ONGOING PROJECT DETAILS:");
                        if (ongoingProjects.size() != 0){
                            for (Project temp : ongoingProjects) {
                                System.out.println(temp.displayDetails());
                            }
                        } else {
                            System.out.println("empty");
                        }
                        break;

                    case 3:
                        //View Ongoing Projects - Name and Number
                        ongoingProjects = context.GetOngoingProjects();
                        System.out.println("ONGOING PROJECT SUMMARY:");
                        if (ongoingProjects.size() != 0){
                            for (Project temp : ongoingProjects) {
                                System.out.println(temp.displaySummary());
                            }
                        } else {
                            System.out.println("empty");
                        }
                        break;

                    case 4:
                        //View Ongoing Projects Passed Due Date
                        lateProjects = context.GetLateProjects();
                        System.out.println("LATE PROJECT SUMMARY:");
                        if (lateProjects.size() != 0){
                            for (Project temp : lateProjects) {
                                System.out.println(temp.displaySummary());
                            }
                        } else {
                            System.out.println("empty");
                        }
                        break;

                    case 5:
                        //Search for Ongoing Project
                        ongoingProjects = context.GetOngoingProjects();
                        System.out.println("ONGOING PROJECT SUMMARY:");
                        if (ongoingProjects.size() != 0){
                            for (Project temp : ongoingProjects) {
                                System.out.println(temp.displaySummary());
                            }
                        } else {
                            System.out.println("empty");
                        }
                        System.out.println("\nEnter the Project ID to view details");
                        int projectID = scanner.nextInt();
                        Project searchProject = context.GetProject(projectID);
                        System.out.println(searchProject.displayDetails());
                        break;

                    case 6:
                        //Update Ongoing Project Details

                        break;
                    case 7:
                        //Delete a Project

                        break;

                    case 8:
                        //Finalize - Display Invoice

                        break;

                    case 9:
                        //View Completed Projects - Number & Names only
                        completedProjects = context.GetCompletedProjects();
                        System.out.println("COMPLETED PROJECT SUMMARY:");
                        if (completedProjects.size() != 0){
                            for (Project temp : completedProjects) {
                                System.out.println(temp.displaySummary());
                            }
                        } else {
                            System.out.println("empty");
                        }
                        break;

                    case 10:
                        //View Completed Projects with details
                        completedProjects = context.GetCompletedProjects();
                        System.out.println("COMPLETED PROJECT DETAILS:");
                        if (completedProjects.size() != 0){
                            for (Project temp : completedProjects) {
                                System.out.println(temp.displayDetails());
                            }
                        } else {
                            System.out.println("empty");
                        }
                        break;

                    case 0:
                        //This option exits the program
                        scanner.close();
                        System.exit(0);
                        System.out.println("Program is closed");

                    default:
                        // Should a user enter an incorrect option, the following message is displayed
                        // allowing user to make a correct chose
                        System.out.println("This is not a valid option \nPlease choose another ");
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid entry , Please try again ");
            }
        }
    }
}