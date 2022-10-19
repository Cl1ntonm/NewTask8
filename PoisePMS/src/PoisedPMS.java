
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

    public static void main(String[] args) {

        //Connection to the Database
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/poisepms?useSSL=false",
                    "otheruser",
                    "swordfish"
            );

            // Creating a direct line to the database
            Statement statement = connection.createStatement();
            ResultSet results = null;

            // declare base variables
            boolean closeProgram = false;
            ArrayList<Project> projectArrayList = new ArrayList<>();
            ListIterator listIterator = null;
            ArrayList<Integer> projectNumberList = new ArrayList();

            // while loop for menu
            while (!closeProgram){
                Scanner scanner = new Scanner(System.in).useDelimiter("\\n");

                // calling method to display the options available for the user
                PoisedUtilities.menuOption();
                try {
                    switch (scanner.nextInt()) {
                        case 1:
                            //Add New Project details

                            break;

                        case 2:
                            //View Ongoing Projects - Full Details
                            String sqlProject = "select*\n" +
                                    "from projects\n" +
                                    "where projects.project_number = 1;";

                            String sqlCustomer = "select*\n" +
                                    "from persons\n" +
                                    "where persons.project_number = 1\n" +
                                    "and role_id = 1;";

                            String sqlArchitect = "select*\n" +
                                    "from persons\n" +
                                    "where persons.project_number = 1\n" +
                                    "and role_id = 2;";

                            String sqlContractor = "select*\n" +
                                    "from persons\n" +
                                    "where persons.project_number = 1\n" +
                                    "and role_id = 3;";

                            String sqlEngineer = "select*\n" +
                                    "from persons\n" +
                                    "where persons.project_number = 1\n" +
                                    "and role_id = 4;";

                            String sqlManager = "select*\n" +
                                    "from persons\n" +
                                    "where persons.project_number = 1\n" +
                                    "and role_id = 5;";

                            ResultSet resultSetProject = statement.executeQuery(sqlProject);
                            ResultSet resultSetCustomer = statement.executeQuery(sqlCustomer);
                            ResultSet resultSetArchitect = statement.executeQuery(sqlArchitect);
                            ResultSet resultSetContractor = statement.executeQuery(sqlContractor);
                            ResultSet resultSetEngineer = statement.executeQuery(sqlEngineer);
                            ResultSet resultSetManager = statement.executeQuery(sqlManager);


                            while (resultSetCustomer.next()) {
                                // fields from Customer
                                int personID = results.getInt("persons.id");
                                int roleID = results.getInt("persons.project_number");
                                String customerFirstName = results.getString("persons.first_name");
                                String customerLastName = results.getString("persons.last_name");
                                String customerTelephone = results.getString("persons.telephone");
                                String customerEmail = results.getString("persons.email");
                                String customerAddress = results.getString("persons.address");

                                Person customer = new Person(personID, roleID, customerFirstName, customerLastName,
                                        customerTelephone, customerEmail, customerAddress);
                            }

                            while (resultSetArchitect.next()) {
                                // fields from Architect
                                int personID = results.getInt("persons.id");
                                int roleID = results.getInt("persons.project_number");
                                String architectFirstName = results.getString("persons.first_name");
                                String architectLastName = results.getString("persons.last_name");
                                String architectTelephone = results.getString("persons.telephone");
                                String architectEmail = results.getString("persons.email");
                                String architectAddress = results.getString("persons.address");

                                Person architect = new Person(personID, roleID, architectFirstName, architectLastName,
                                        architectTelephone, architectEmail, architectAddress);
                            }

                            while (resultSetContractor.next()) {
                                // fields from Contractor
                                int personID = results.getInt("persons.id");
                                int roleID = results.getInt("persons.project_number");
                                String contractorFirstName = results.getString("persons.first_name");
                                String contractorLastName = results.getString("persons.last_name");
                                String contractorTelephone = results.getString("persons.telephone");
                                String contractorEmail = results.getString("persons.email");
                                String contractorAddress = results.getString("persons.address");

                                Person contractor = new Person(personID, roleID, contractorFirstName,
                                        contractorLastName, contractorTelephone, contractorEmail, contractorAddress);
                            }

                            while (resultSetEngineer.next()) {
                                // fields from Structural Engineer
                                int personID = results.getInt("persons.id");
                                int roleID = results.getInt("persons.project_number");
                                String engineerFirstName = results.getString("persons.first_name");
                                String engineerLastName = results.getString("persons.last_name");
                                String engineerTelephone = results.getString("persons.telephone");
                                String engineerEmail = results.getString("persons.email");
                                String engineerAddress = results.getString("persons.address");

                                Person engineer = new Person(personID, roleID, engineerFirstName,
                                        engineerLastName, engineerTelephone, engineerEmail, engineerAddress);
                            }

                            while (resultSetManager.next()) {
                                // fields from Project Manager
                                int personID = results.getInt("persons.id");
                                int roleID = results.getInt("persons.project_number");
                                String managerFirstName = results.getString("persons.first_name");
                                String managerLastName = results.getString("persons.last_name");
                                String managerTelephone = results.getString("persons.telephone");
                                String managerEmail = results.getString("persons.email");
                                String managerAddress = results.getString("persons.address");

                                Person manager = new Person(personID, roleID, managerFirstName,
                                        managerLastName, managerTelephone, managerEmail, managerAddress);
                            }

                            Project project = null;
                            while (resultSetProject.next()) {
                                // fields from Project table
                                int projectNumber = results.getInt("project.project_number");
                                String projectName = results.getString("project.name");
                                String projectType = results.getString("project.type");
                                String projectAddress = results.getString("project.address");
                                String projectErf = results.getString("project.erf");
                                float projectAmountCharged = results.getFloat("project.amount_charged");
                                float projectAmountPaid = results.getFloat("project.amount_paid");
                                //using pass-through function method for java and sql date incompatibility
                                Date projectDeadlineDateSQL = results.getDate("project.deadline_date");
                                LocalDate localDateDeadLineDate = projectDeadlineDateSQL.toLocalDate();
                                //using pass-through function method for java and sql date incompatibility
                                Date projectCompletedDateSQL = results.getDate("project.completed_date");
                                LocalDate localDateCompletedDate = projectCompletedDateSQL.toLocalDate();
                                String projectFinalised = results.getString("project.finalised");

                                Person customer = null;
                                Person architect = null;
                                Person contractor = null;
                                Person engineer = null;
                                Person manager = null;

                                project = new Project(projectNumber, projectName, projectType, projectAddress,
                                        projectErf, projectAmountCharged, projectAmountPaid, localDateDeadLineDate,
                                        projectFinalised, localDateCompletedDate, customer, architect, contractor,
                                        engineer, manager);
                            }

                            projectArrayList.add(project);

                            listIterator = projectArrayList.listIterator();
                            while (listIterator.hasNext())
                                System.out.println(listIterator.next());

                            break;

                        case 3:
                            //View Ongoing Projects - Name and Number

                            break;

                        case 4:
                            //View Ongoing Projects Passed Due Date

                            break;

                        case 5:
                            //Search for Ongoing Project

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

                            break;

                        case 10:
                            //View Completed Projects with details

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}