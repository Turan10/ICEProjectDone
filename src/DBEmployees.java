import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/*
1. The code defines a DBStudents class,

2. which represents a student with various pieces of information such as their ID, name, age, and more.

3. The code also includes a main method, which is the entry point for the program.

4. In the main method, the code establishes a connection to a database and uses a SQL
query to retrieve data for all of the students in the database.

5. For each row of student data returned by the query, the code creates a new DBStudents
object and adds it to an ArrayList called StudentsDatabase.

6. The code then loops through the StudentsDatabase ArrayList and prints each DBStudents object to the console.
*/


public class DBEmployees extends Person{

    //informationer omkring den gældende lærer
    int ID;
    String name;
    int age;
    String email;
    int phoneNumber;
    String jobPosition;
    String workingHours;
    String location;
    ArrayList<DBEmployees> DBEmployeesDatabase = new ArrayList<DBEmployees>();

    //constructor omkring eleven
    public DBEmployees(int ID, String name, int age, int phoneNumber, String jobPosition, String workingHours, String location, String email) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.jobPosition = jobPosition;
        this.workingHours = workingHours;
        this.location = location;
    }

    public DBEmployees() {
    }


    public void getEmployeesFromDB() throws SQLException {

        // Set up connection to the database
        String url = "jdbc:mysql://localhost:3306/ICE";
        String username = "root";
        String password = "Tingbjerg1!";


        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();


        // Execute a query to retrieve student data from the database
        ResultSet resultSet = statement.executeQuery("SELECT * FROM DBEmployeesDB");

        // Loop through the result set and create a DBStudents object for each row
        while (resultSet.next()) {
            int ID = resultSet.getInt("ID");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            int phoneNumber = resultSet.getInt("phone_number");
            String jobPosition = resultSet.getString("job_position");
            String workingHours = resultSet.getString("working_hours");
            String location = resultSet.getString("location");
            String email = resultSet.getString("email");

            // Create a new DBStudents object using the data from the ResultSet
            DBEmployees employees = new DBEmployees(ID, name, age, phoneNumber, jobPosition, workingHours, location, email);

            // Add the DBStudents object to the StudentsDatabase ArrayList
            DBEmployeesDatabase.add(employees);
        }

        for (DBEmployees employees : DBEmployeesDatabase) {
            System.out.println(employees);
        }
    }

    // Loop through the StudentsDatabase ArrayList and print each DBStudents object
            /*for (DBStudents s : StudentsDatabase) {
            System.out.println(s);
             */


    public DBEmployees getSpecificEmployeeFromDB() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ICE", "root", "Tingbjerg1!");
        Scanner scanner = new Scanner(System.in);
        DBEmployees employees = new DBEmployees();
        getEmployeesFromDB();
        System.out.println("Enter employee ID");
        int studentID = scanner.nextInt();


        String sql = "SELECT * FROM DBEmployeesDB WHERE ID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, studentID);
        ResultSet resultSet = stmt.executeQuery();


        // Loop through the result set and create a DBStudents object for each row
        while (resultSet.next()) {
            int ID = resultSet.getInt("ID");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            int phoneNumber = resultSet.getInt("phone_number");
            String jobPosition = resultSet.getString("job_position");
            String workingHours = resultSet.getString("working_hours");
            String location = resultSet.getString("location");
            String email = resultSet.getString("email");

            // Create a new DBStudents object using the data from the ResultSet
            employees = new DBEmployees(ID, name, age, phoneNumber, jobPosition, workingHours, location, email);
            DBEmployeesDatabase.add(employees);
        }
        for (DBEmployees employee : DBEmployeesDatabase) {
            if (employee.ID == studentID) {
                System.out.println(employee);
            }
        }
        return employees;
    }

    @Override
    public String toString() {
        return "\nID " + ID + "\n" +

                "Name: " + name + "\n" +

                "Age: " + age + "\n" +

                "Email: " + email + "\n" +

                "PhoneNumber: " + phoneNumber + "\n" +

                "jobPosition: " + jobPosition + "\n" +

                "workingHours: " + workingHours + "\n" +

                "location: " + location + "\n" +
                "--------------------------------------------";

    }
}