import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Class for creating Project objects
 */

public class Project {
    // Assigning class Attributes of various essential construction (project) characteristic
    // all marked as private to limit access
    protected int projectNumber;
    private String projectName;
    private String projectType;
    private String projectAddress;
    private final String projectErfNumber;
    private double projectCostAmount;
    private double projectPaidAmount;
    private LocalDate projectDeadline;
    private LocalDate projectCompletedDate;
    private String projectFinalized;
    // assigning (Person) object for use
    private Person projectCustomer;
    private Person projectArchitect;
    private Person projectContractor;
    private Person projectEngineer;
    private Person projectManager;

    // Constructor method , to pass through variables needed to flesh out and create the class object
    public Project(int projectNumber, String projectName, String projectType, String projectAddress,
                   String projectErfNumber, double projectCostAmount, double projectPaidAmount,
                   LocalDate projectDeadlineDate, String projectFinalized, LocalDate projectCompletedDate, Person projectCustomer,
                   Person projectArchitect, Person projectContractor, Person projectEngineer, Person projectManager){
        this.projectNumber = projectNumber;
        this.projectName = projectName;
        this.projectType = projectType;
        this.projectAddress = projectAddress;
        this.projectErfNumber = projectErfNumber;
        this.projectCostAmount = projectCostAmount;
        this.projectPaidAmount = projectPaidAmount;
        this.projectDeadline = projectDeadlineDate;
        this.projectCompletedDate = projectCompletedDate;
        this.projectFinalized = projectFinalized;
        this.projectCustomer = projectCustomer;
        this.projectArchitect = projectArchitect;
        this.projectContractor = projectContractor;
        this.projectEngineer = projectEngineer;
        this.projectManager = projectManager;
    }

    // Providing Getter methods for each encapsulated Attribute
    //GETTERS
    public int getProjectNumber(){
        return projectNumber;
    }
    public String getProjectName(){
        return projectName;
    }
    public String getProjectType(){
        return projectType;
    }
    public String getProjectAddress(){
        return projectAddress;
    }
    public String getProjectErfNumber(){
        return projectErfNumber;
    }
    public double getProjectCostAmount(){
        return projectCostAmount;
    }
    public double getProjectPaidAmount(){
        return projectPaidAmount;
    }
    public LocalDate getProjectDeadline(){
        return projectDeadline;
    }
    public LocalDate getProjectCompletedDate() {
        return projectCompletedDate;
    }
    public Person getProjectCustomer() {
        return projectCustomer;
    }
    public Person getProjectArchitect() {
        return projectArchitect;
    }
    public Person getProjectContractor() {
        return projectContractor;
    }
    public Person getProjectEngineer() {
        return projectEngineer;
    }
    public Person getProjectManager() {
        return projectManager;
    }
    public String getProjectFinalized() {
        return projectFinalized;
    }

    //SETTERS
    public void setProjectCompletedDate(LocalDate completedDate) {
        this.projectCompletedDate = completedDate;
    }
    public void setProjectFinalized(String projectFinalized) {
        this.projectFinalized = projectFinalized;
    }

    //Methods
    public boolean isProjectFinalized(){
        if (projectFinalized == "yes"){
            return true;
        } else if (projectFinalized == "no") {
            return false;
        }
        return false;
    }
    // method that , when called , will display all the objects Project related information
    // including the details of personal objects (Customer , architect, contractor )
    /**
     *Displays Information on screen
     * @return Displays to screen the stored Project object values
     */
    public String displayDetails(){

        return "FULL PROJECT DETAILS:"
                + "\n___________________________________________________________"
                + "\nProject Number :" + projectNumber
                + "\nProject Name :" + projectName
                + "\nProject Type :" + projectType
                + "\nProject Address :" + projectAddress
                + "\nProject ERF Number :" + projectErfNumber
                + "\nProject Cost :" + projectCostAmount
                + "\nProject Paid Amount :" + projectPaidAmount
                + "\nProject Deadline: " + projectDeadline
                + "\nProject Finalized :" + projectFinalized
                + "\n"
                + "\nCustomer Details: " + projectCustomer.toString() + "\n"
                + "\nArchitect Details: " + projectArchitect.toString() + "\n"
                + "\nContractor Details: " + projectContractor.toString() + "\n"
                + "\nStructural Engineer Details: " + projectEngineer.toString() + "\n"
                + "\nProject Manager Details: " + projectManager.toString() + "\n"
                + "\n___________________________________________________________";
    }

    public String displaySummary(){

        return "___________________________________________________________"
                + "\nProject Number :" + projectNumber
                + "\nProject Name :" + projectName;
    }

    /**
     * Takes user entered values and passed and inserted them into the Database Table
     *
     * @param connection - connection session to the database
     * @return the success status when fields are written to te database tables
     * @throws SQLException
     */
    public String toSql(Connection connection) throws SQLException {
        String success = null;

        String projectInsertSql = "insert into project(project_number, name, type, address, erf, amount_charged, amount_paid, deadline_date, completed_date, finalised) values(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(projectInsertSql);
        preparedStatement.setInt(1,projectNumber);
        preparedStatement.setString(2,projectName);
        preparedStatement.setString(3,projectType);
        preparedStatement.setString(4,projectAddress);
        preparedStatement.setString(5,projectErfNumber);
        preparedStatement.setDouble(6,projectCostAmount);
        preparedStatement.setDouble(7,projectPaidAmount);
        preparedStatement.setDate(8, Date.valueOf(projectDeadline));
        preparedStatement.setDate(9, Date.valueOf(projectCompletedDate));
        preparedStatement.setString(10,projectFinalized);

        int rows = preparedStatement.executeUpdate();

        if (rows > 0 ) {
            success = "project record updated successfully";
        }
        return success;
    }
}
