import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Teacher extends Teachers {

    private ArrayList<DBStudents> StudentInfo = new ArrayList<>();
    private String firstName;
    private String lastName;
    private int ID;
    private String email;
    private String adress;
    private int phoneNumber;
    private String subjects;


    public Teacher(int ID, String firstName, String lastName, String email, String adress, int phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
        this.email = email;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
    }

    public Teacher(int ID, String firstName, String lastName, String email, String subjects, int phoneNumber, String adress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.subjects = subjects;

    }


    public Teacher(String firstName, String email, String adress, String subjects, int phoneNumber, String lastName, int ID) {
        this.firstName = firstName;
        this.email = email;
        this.adress = adress;
        this.subjects = subjects;
        this.phoneNumber = phoneNumber;
        this.lastName = lastName;
        this.ID = ID;
    }


    public Teacher() {

    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public int getID() {
        return ID;
    }


    public int giveGrade() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ICE", "root", "Tingbjerg1!");
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM StudentInfo");

        DBStudents dbStudents = new DBStudents();
        dbStudents.getStudentFromDatabase();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the student ID number you want to give a grade to: ");
        int studentID = input.nextInt();
        System.out.println("Please enter the grade you want to give the student: ");
        int grade = input.nextInt();

        // Give the grade
        String sql = "UPDATE StudentInfo SET grades = ? WHERE ID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, grade);
        stmt.setInt(2, studentID);
        stmt.executeUpdate();
        System.out.println("Grade given successfully");
        return grade;
    }


    public DBStudents getAccessToStudent() throws SQLException {


        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ICE", "root", "Tingbjerg1!");
        Scanner scanner = new Scanner(System.in);
        DBStudents students = new DBStudents();
        students.getStudentFromDatabase();
        System.out.println("Enter student ID");
        int studentID = scanner.nextInt();


        String sql = "SELECT * FROM StudentInfo WHERE ID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, studentID);
        ResultSet resultSet = stmt.executeQuery();

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


            students = new DBStudents(ID, schoolClass, forName, lastName, age, email, phoneNumber, absenceRate, teacherComment, grades);
            StudentInfo.add(students);

        }

        System.out.println(students);

        return students;

    }


    //This method will give the teacher access to the student object and the student object will give the teacher access to the student's grades.


    public String toString() {
        return "Teacher:" + '\n' +
                "ID: " + ID + '\n' +
                "FirstName: " + firstName + '\n' +
                "LastName: " + lastName + '\n' +
                "Email: " + email + '\n' +
                "Address: " + adress + '\n' +
                "PhoneNumber: " + phoneNumber + '\n' +
                "Subjects: " + subjects + '\n' +
                "--------------------------------------------------------";
    }
}



