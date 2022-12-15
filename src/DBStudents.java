import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/*

1.  The code defines a DBStudents class,

2.  which represents a student with various pieces of information such as their ID, name, age, and more.

3.  The code also includes a main method, which is the entry point for the program.

4.  In the main method, the code establishes a connection to a database and uses a SQL
    query to retrieve data for all the students in the database.

5.  For each row of student data returned by the query, the code creates a new DBStudents
    object and adds it to an ArrayList called StudentsDatabase.

6.  The code then loops through the StudentsDatabase ArrayList and prints each DBStudents object to the console.

*/


public class DBStudents extends Student {

    //informationer omkring den gældende elev
    int ID;
    String schoolClass;
    String forName;
    String lastName;
    int age;
    String email;
    int phoneNumber;
    int absenceRate;
    String teacherComment;
    int grades;

    private ArrayList<DBStudents> StudentsDatabase = new ArrayList<DBStudents>();

    public ArrayList<DBStudents> specificStudent = new ArrayList<DBStudents>();


    //constructor omkring eleven
    public DBStudents(int ID, String schoolClass, String forName, String lastName, int age, String email, int phoneNumber, int absencerate, String teacherComment, int grades) {
        this.ID = ID;
        this.schoolClass = schoolClass;
        this.forName = forName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.absenceRate = absencerate;
        this.teacherComment = teacherComment;
        this.grades = grades;

    }

    public DBStudents(String schoolClass, String forName, String lastName, int age, String email, int phoneNumber, int absencerate, String teacherComment, int grades) {
        this.schoolClass = schoolClass;
        this.forName = forName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.absenceRate = absencerate;
        this.teacherComment = teacherComment;
        this.grades = grades;
    }

    public DBStudents() {

    }

    public int getID() {
        return ID;
    }

    public String getSchoolClass() {
        return schoolClass;
    }

    public String getForName() {
        return forName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getAbsenceRate() {
        return absenceRate;
    }

    public String getTeacherComment() {
        return teacherComment;
    }

    public int getGrades() {
        return grades;
    }


    public void setID(int ID) {
        this.ID = ID;
    }

    public void setSchoolClass(String schoolClass) {
        this.schoolClass = schoolClass;
    }

    public void setForName(String forName) {
        this.forName = forName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAbsenceRate(int absenceRate) {
        this.absenceRate = absenceRate;
    }

    public void setTeacherComment(String teacherComment) {
        this.teacherComment = teacherComment;
    }

    public void setGrades(int grades) {
        this.grades = grades;
    }


    public void getSpecificStudent(String username) throws SQLException {
        Login login = new Login();


        // Set up connection to the database
        String url = "jdbc:mysql://localhost:3306/ICE";
        String dbUser = "root";
        String password = "Tingbjerg1!";

        Connection connection = DriverManager.getConnection(url, dbUser, password);
        Statement statement = connection.createStatement();


        // Execute a query to retrieve student data from the database
        ResultSet resultSet = statement.executeQuery("SELECT ID, School_Class, forName, lastName, age, email, phone_number, absence_rate, teacher_comment, grades FROM StudentInfo WHERE username = '" + username + "'");

        // Loop through the result set and create a new DBStudents object for each row of data
        while (resultSet.next()) {
            int ID = resultSet.getInt("ID");
            String schoolClass = resultSet.getString("School_Class");
            String forName = resultSet.getString("forName");
            String lastName = resultSet.getString("lastName");
            int age = resultSet.getInt("age");
            String email = resultSet.getString("email");
            int phoneNumber = resultSet.getInt("phone_number");
            int absenceRate = resultSet.getInt("absence_rate");
            String teacherComment = resultSet.getString("teacher_comment");
            int grades = resultSet.getInt("grades");

            DBStudents student = new DBStudents(ID, schoolClass, forName, lastName, age, email, phoneNumber, absenceRate, teacherComment, grades);
            specificStudent.add(student);

        }

        for (DBStudents student : specificStudent) {
            System.out.println(student);
        }


    }


    public void getStudentFromDatabase() throws SQLException {


        // Set up connection to the database
        String url = "jdbc:mysql://localhost:3306/ICE";
        String username = "root";
        String password = "Tingbjerg1!";

        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();


        // Execute a query to retrieve student data from the database
        ResultSet resultSet = statement.executeQuery("SELECT * FROM StudentInfo");

        // Loop through the result set and create a DBStudents object for each row
        while (resultSet.next()) {
            int ID = resultSet.getInt("ID");
            String schoolClass = resultSet.getString("school_class");
            String forName = resultSet.getString("forName");
            String lastName = resultSet.getString("lastName");
            int age = resultSet.getInt("age");
            String email = resultSet.getString("email");
            int phoneNumber = resultSet.getInt("phone_number");
            int absenceRate = resultSet.getInt("absence_rate");
            String teacherComment = resultSet.getString("teacher_comment");
            int grades = resultSet.getInt("grades");

            // Create a new DBStudents object using the data from the ResultSet
            DBStudents student = new DBStudents(ID, schoolClass, forName, lastName, age, email, phoneNumber, absenceRate, teacherComment, grades);

            // Add the DBStudents object to the StudentsDatabase ArrayList
            StudentsDatabase.add(student);
            System.out.println(student);
        }

        // Loop through the StudentsDatabase ArrayList and print each DBStudents object
            /*for (DBStudents s : StudentsDatabase) {
            System.out.println(s);
             */

        // Define the ID of the student we want to find


        // Loop through the StudentsDatabase ArrayList and find the student with the specified ID

    }

    // If the student was found, print their information


    @Override
    public String toString() {
        return
                "ID: " + ID + "\n" +

                        "SchoolClass: " + schoolClass + "\n" +

                        "First name: " + forName + "\n" +

                        "Last name: " + lastName + "\n" +

                        "Age: " + age + "\n" +

                        "Email: " + email + "\n" +

                        "PhoneNumber: " + phoneNumber + "\n" +

                        "AbsenceRate: " + absenceRate + "\n" +

                        "TeacherComment: " + teacherComment + "\n" +

                        "Grades: " + grades + "\n" +
                        "-----------------------------------" + "\n";
    }

}

