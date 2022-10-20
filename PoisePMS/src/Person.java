import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class for Creation of Person Objects
 */
public class Person {
    //Attributes
    private int personID;
    private int projectNum;
    private String firstName;
    private String lastName;
    private String telephone;
    private String email;
    private String address;
    private int roleID;

    // Constructor
    public Person(int personID, int projectNum, int roleID, String firstName, String lastName, String telephone, String email, String address) {
        this.personID = personID;
        this.projectNum = projectNum;
        this.roleID = roleID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
    }

    //Getters
    public int getPersonID() {
        return personID;
    }
    public int getProjectNum() {
        return projectNum;
    }
    public int getRoleID() {
        return roleID;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getTelephone() {
        return telephone;
    }
    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }

    //Methods
    /**
     * Displays Information on screen
     *
     * @return Displays to screen the stored Person objects values
     */
    public String toString() {
        return "Name: " + firstName + " " + lastName
                + "\nPhone Number: " + telephone
                + "\nEmail Address: " + email
                + "\nAddress : " + address;
    }

    /**
     * Takes user entered values and passed and inserted them into the Database Table
     *
     * @param connection - connection session to the database
     * @param personType - variable used to indicate which table to write person details to
     * @return the success status when fields are written to te database tables
     * @throws SQLException
     */
    public String toSql(Connection connection, String personType) throws SQLException {

        String success = null;
        String personInsertSql = null;

        String insertCustomerTable = "insert into customer (cust_id, project_number, first_name, last_name, telephone, email, address) values(?,?,?,?,?,?,?)";
        String insertArchitectTable = "insert into architect (arch_id, project_number, first_name, last_name, telephone, email, address) values(?,?,?,?,?,?,?)";
        String insertContractorTable = "insert into contractor (con_id, project_number, first_name, last_name, telephone, email, address) values(?,?,?,?,?,?,?)";

        switch (personType){
            case "customer":
                personInsertSql = insertCustomerTable;
                break;
            case "architect":
                personInsertSql = insertArchitectTable;
                break;
            case "contractor":
                personInsertSql = insertContractorTable;
        }
        PreparedStatement preparedStatement = connection.prepareStatement(personInsertSql);
        preparedStatement.setInt(1,personID);
        preparedStatement.setInt(2, roleID);
        preparedStatement.setString(3,firstName);
        preparedStatement.setString(4,lastName);
        preparedStatement.setString(5,telephone);
        preparedStatement.setString(6,email);
        preparedStatement.setString(7,address);

        int rows = preparedStatement.executeUpdate();
        if(rows > 0 ){
            success = personType+" record updated successfully";
        }
        return success;
    }
}

