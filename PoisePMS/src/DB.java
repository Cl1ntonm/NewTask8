import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class DB {

    private Connection connection;

    public Connection connect() {
        //Connection to the Database
        if (connection == null){
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/PoisePMS?useSSL=false&allowPublicKeyRetrieval=true",
                        "Sai724",
                        "Password"
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public Project GetProject(int projectNum) throws SQLException {

        Project project = null;
        String query = "select * from projects where projects.project_number = " + projectNum +";";
        try{
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                // fields from Project table
                int projectNumber = results.getInt("projects.project_number");
                String projectName = results.getString("projects.name");
                String projectType = results.getString("projects.type");
                String projectAddress = results.getString("projects.address");
                String projectErf = results.getString("projects.erf");
                float projectAmountCharged = results.getFloat("projects.amount_charged");
                float projectAmountPaid = results.getFloat("projects.amount_paid");
                //using pass-through function method for java and sql date incompatibility
                Date projectDeadlineDateSQL = results.getDate("projects.deadline_date");
                LocalDate localDateDeadLineDate = projectDeadlineDateSQL.toLocalDate();
                //using pass-through function method for java and sql date incompatibility
                Date projectCompletedDateSQL = results.getDate("projects.completed_date");
                LocalDate localDateCompletedDate = projectCompletedDateSQL.toLocalDate();
                String projectFinalised = results.getString("projects.finalised");

                Person customer = GetPerson(projectNumber, 1);
                Person architect = GetPerson(projectNumber, 2);
                Person contractor = GetPerson(projectNumber, 3);
                Person engineer = GetPerson(projectNumber, 4);
                Person manager = GetPerson(projectNumber, 5);

                project = new Project(projectNumber, projectName, projectType, projectAddress,
                        projectErf, projectAmountCharged, projectAmountPaid, localDateDeadLineDate,
                        projectFinalised, localDateCompletedDate, customer, architect, contractor,
                        engineer, manager);
            }

        }catch(SQLException e){}

        return project;
    }

    public Person GetPerson(int projectNum, int role_ID) throws SQLException {
        Person person = null;
        String query = "select * from persons where persons.project_number = " + projectNum +" AND persons.role_id = " + role_ID + ";";
        try{
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                int personID = results.getInt("persons.id");
                int projectNumber  = projectNum;
                String firstName = results.getString("persons.first_name");
                String lastName = results.getString("persons.last_name");
                String telephone = results.getString("persons.telephone");
                String email = results.getString("persons.email");
                String address = results.getString("persons.address");
                int roleID = role_ID;
                person = new Person(personID, projectNumber, roleID, firstName, lastName, telephone, email, address);
            }

        }catch(SQLException e){}

        return person;
    }

    public Role GetRole(int role_ID) throws SQLException {
        Role role = null;
        String query = "select * from roles WHERE role_id =" + role_ID +";";
        try{
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                int roleID = results.getInt("roles.id");
                String roleName  = results.getString("roles.role");


                role = new Role(roleID, roleName);
            }

        }catch(SQLException e){}

        return role;
    }

    public ArrayList<Project> GetOngoingProjects() throws SQLException {
        ArrayList<Integer> ongoingProjectIDs = new ArrayList<Integer>();
        ArrayList<Project> projectArrayList = new ArrayList<Project>();
        String query = "select project_number from projects where projects.finalised = 'no'";

        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                ongoingProjectIDs.add(results.getInt("projects.project_number"));
            }

            for (int i = 0; i < ongoingProjectIDs.size(); i++) {
                Project project = GetProject(ongoingProjectIDs.get(i));
                projectArrayList.add(project);
            }

        }catch(SQLException e){}

        return projectArrayList;
    }

    public ArrayList<Project> GetCompletedProjects() throws SQLException {
        ArrayList<Integer> completedProjectIDs = new ArrayList<Integer>();
        ArrayList<Project> projectArrayList = new ArrayList<Project>();
        String query = "select project_number from projects where projects.finalised = 'yes'";

        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                completedProjectIDs.add(results.getInt("projects.project_number"));
            }

            for (int i = 0; i < completedProjectIDs.size(); i++) {
                Project project = GetProject(completedProjectIDs.get(i));
                projectArrayList.add(project);
            }

        }catch(SQLException e){}

        return projectArrayList;
    }

    public ArrayList<Project> GetLateProjects() throws SQLException {
        ArrayList<Integer> lateProjectIDs = new ArrayList<Integer>();
        ArrayList<Project> projectArrayList = new ArrayList<Project>();
        String query = "SELECT project_number FROM projects WHERE deadline_date < NOW() AND finalised = 'no';";

        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                lateProjectIDs.add(results.getInt("projects.project_number"));
            }

            for (int i = 0; i < lateProjectIDs.size(); i++) {
                Project project = GetProject(lateProjectIDs.get(i));
                projectArrayList.add(project);
            }

        }catch(SQLException e){}

        return projectArrayList;
    }

    public ArrayList<Project> GetAllProjects() throws SQLException {
        ArrayList<Integer> allProjectIDs = new ArrayList<Integer>();
        ArrayList<Project> projectArrayList = new ArrayList<Project>();
        String query = "select project_number from projects";

        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                allProjectIDs.add(results.getInt("projects.project_number"));
            }

            for (int i = 0; i < allProjectIDs.size(); i++) {
                Project project = GetProject(allProjectIDs.get(i));
                projectArrayList.add(project);
            }

        }catch(SQLException e){}

        return projectArrayList;
    }

    public void CreatePerson(Person person) throws SQLException {

        String query = "INSERT INTO persons VALUES "+ person.getProjectNum() +", "+ person.getFirstName() +", "+
                person.getLastName() +", "+ person.getTelephone() +", "+ person.getEmail() +", "+ person.getAddress() +
                ", "+ person.getRoleID() +");";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }catch(SQLException e){}
    }

    public int CreateProject(Project project) throws SQLException {
        int key = 0;

        String query = "INSERT INTO projects VALUES ("+ project.getProjectNumber() + ", "+ project.getProjectName() +
                ", "+ project.getProjectType() +", "+ project.getProjectAddress() +", "+ project.getProjectErfNumber() +
                ", "+ project.getProjectCostAmount() +", "+ project.getProjectPaidAmount() +", "+
                project.getProjectDeadline() +", "+ project.getProjectCompletedDate() +", "+ project.getProjectFinalized() +");";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query, RETURN_GENERATED_KEYS);
            ResultSet result = statement.getGeneratedKeys();
            result.next();
            key = result.getInt(1);
        }catch(SQLException e){}

        return key;
    }

    public ArrayList<Role> GetAllRoles() throws SQLException {
        ArrayList<Integer> allRoleIDs = new ArrayList<Integer>();
        ArrayList<Role> projectRoles = new ArrayList<Role>();
        String query = "select id from roles";

        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                allRoleIDs.add(results.getInt("projects.project_number"));
            }

            for (int i = 0; i < allRoleIDs.size(); i++) {
                Role role = GetRole(allRoleIDs.get(i));
                projectRoles.add(role);
            }

        }catch(SQLException e){}

        return projectRoles;
    }
}
