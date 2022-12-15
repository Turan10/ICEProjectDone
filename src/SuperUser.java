import com.mysql.cj.jdbc.exceptions.MySQLQueryInterruptedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SuperUser extends DBEmployees {
    private ArrayList<Student> listOfStudents = new ArrayList<>();
    private ArrayList<Teacher> listOfTeachers = new ArrayList<>();
    private ArrayList<Login> listOfSuperUsers = new ArrayList<>();


    String url = "jdbc:mysql://localhost:3306/Classroom_Schema";
    String usernameDB = "root";
    String passwordDB = "Tingbjerg1!";


    public void createSuperUserTable() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ICE", "root", "Tingbjerg1!");
            PreparedStatement createTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS SuperUsers(iD int NOT NULL AUTO_INCREMENT, Username varchar(255), Password varchar(255), PRIMARY KEY (iD))");
            createTable.executeUpdate();

        } catch (Exception e) {
            System.out.println("Your connection went wrong. Table was not created");
        } finally {
            System.out.println("Your table has been created!");
        }
    }

    public void createSuperUser() {
        Scanner scan = new Scanner(System.in);
        SuperUserObject superUserObject = new SuperUserObject("david", "looser");

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ICE", "root", "Tingbjerg1!");

            System.out.println("Write your username");
            String userName = scan.nextLine();
            superUserObject.setUsernameOfSuperUser(userName);

            System.out.println("Write your password");
            String password = scan.nextLine();
            superUserObject.setPasswordOfSuperUser(password);

            PreparedStatement addSuperUser = connection.prepareStatement("INSERT INTO SuperUsers (Username, Password) VALUES ('" + userName + "', '" + password + "')");
            addSuperUser.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Your SuperUser has been created to the database");
    }

    public ArrayList<Login> loginSuperUser() {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ICE", "root", "Tingbjerg1!");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT* FROM superUsers");

            while (resultSet.next()) {
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");

                Login login = new Login(username, password);
                listOfSuperUsers.add(login);
            }
            Scanner scan = new Scanner(System.in);

            System.out.println("Enter your username");
            String username = scan.nextLine();
            System.out.println("Enter your password");
            String password = scan.nextLine();
            Login login1 = new Login(username, password);

            boolean found = false;
            for (Login l : listOfSuperUsers) {
                if (l.getUsername().equals(username) && l.getPassword().equals(password)) {
                    System.out.println("Login successful!");
                    found = true;
                }
            }
            if (found == false) {
                System.out.println("Login was not successful. Try again!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfSuperUsers;
    }


    public void createStudent() {
        Scanner scan = new Scanner(System.in);
        DBStudents student = new DBStudents("4.C", "Martin", "Koefod", 11, "Martinkoefod@gmail.com", 45152585, 0, null, 0);


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ICE", "root", "Tingbjerg1!");

            System.out.println("Write down the firstname of the Student");
            String firstName = scan.nextLine();
            student.setForName(firstName);

            System.out.println("Write down the lastname of the Student");
            String lastName = scan.nextLine();
            student.setLastName(lastName);

            System.out.println("Write down the classname of the Student");
            String classname = scan.nextLine();
            student.setSchoolClass(classname);

            System.out.println("Write down the email of the Student");
            String email = scan.nextLine();
            student.setEmail(email);

            System.out.println("Write down the age of the Student");
            int age = scan.nextInt();
            student.setAge(age);

            System.out.println("Write down the phoneNumber of the Student");
            int phoneNumber = scan.nextInt();
            student.setPhoneNumber(phoneNumber);

            int abscence = 0;
            student.setAbsenceRate(abscence);
            String teacherComment = null;
            student.setTeacherComment(teacherComment);
            int grades = 0;
            student.setGrades(grades);


            PreparedStatement insertNewStudentToDB = connection.prepareStatement("INSERT INTO StudentInfo (school_class, forName, lastName, age, email, phone_number, absence_rate, teacher_comment, grades) VALUES ('" + classname + "', '" + firstName + "', '" + lastName + "', '" + age + "', '" + email + "', '" + phoneNumber + "' , '" + abscence + "', '" + teacherComment + "', '" + grades + "')");
            insertNewStudentToDB.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Your student has been added to the database");
    }


    //TODO
    //Make a code that shows the user a list of current teachers and then give the option to delete one

    public void deleteStudentDB() throws SQLException {
        Scanner scan = new Scanner(System.in);
        DBStudents student = new DBStudents();
        student.getStudentFromDatabase();
        System.out.println("Please enter the ID of the student you want to delete");
        int studentID = scan.nextInt();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ICE", "root", "Tingbjerg1!");
            PreparedStatement deleteStudent = connection.prepareStatement("DELETE FROM StudentInfo WHERE ID = ?");
            deleteStudent.setInt(1, studentID);
            deleteStudent.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Student with iD number " + studentID + " is removed from the Database");
    }


    public void createTeacher() {
        Scanner scan = new Scanner(System.in);

        Teacher teacher = new Teacher(1, "Martin", "Koefod", "jeppe@hotmail.com", "English ", 45152585, "juliusvej");

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ClassRoom_Schema", "root", "Tingbjerg1!");

            System.out.println("Write down the firstname of the Teacher");
            String firstName = scan.nextLine();
            teacher.setFirstName(firstName);

            System.out.println("Write down the lastname of the Teacher");
            String lastname = scan.nextLine();
            teacher.setLastName(lastname);

            System.out.println("Write down the subject of the Teacher");
            String subject = scan.nextLine();
            teacher.setSubjects(subject);

            System.out.println("Write down the email of the Teacher");
            String email = scan.nextLine();
            teacher.setEmail(email);

            System.out.println("Write down the address of the Teacher");
            String address = scan.nextLine();
            teacher.setAdress(address);

            System.out.println("Write down the phoneNumber of the Teacher");
            int phoneNumber = scan.nextInt();
            teacher.setPhoneNumber(phoneNumber);


            PreparedStatement insertTeacherToDB = connection.prepareStatement("INSERT INTO Teachers (Firstname,LastName, Email, subjects, Phonenumber ,Adress) VALUES ('" + firstName + "', '" + lastname + "', '" + email + "', '" + subject + "', '" + phoneNumber + "', '" + address + "')");

            insertTeacherToDB.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Your teacher has been added to the Database");

    }


    public void deleteTeacherDB(int id) {

        //TODO
        //Make a code that shows the user a list of current teachers and then give the option to delete one
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Classroom_schema", "root", "Tingbjerg1!");
            PreparedStatement deleteStudent = connection.prepareStatement("DELETE FROM Teachers WHERE TeacherID = ?");
            deleteStudent.setInt(1, id);
            deleteStudent.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Teacher with iD number " + id + " is removed from the Database");
    }


    public void displayAllTeachers() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Classroom_Schema", "root", "Tingbjerg1!");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT* FROM Teachers");

            while (resultSet.next()) {
                int id = resultSet.getInt("TeacherID");
                String name = resultSet.getString("Firstname");
                String lastname = resultSet.getString("LastName");
                String email = resultSet.getString("Email");
                String subject = resultSet.getString("subjects");
                int phonenumber = resultSet.getInt("Phonenumber");
                String address = resultSet.getString("Adress");


                Teacher teacher = new Teacher(name, email, address, subject, phonenumber, lastname, id);
                listOfTeachers.add(teacher);
            }
            for (Teacher s : listOfTeachers) {
                System.out.println(s.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Teacher displaySpecificTeacher() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Classroom_Schema", "root", "Tingbjerg1!");
        Scanner scanner = new Scanner(System.in);
        SuperUser superUser = new SuperUser();
        Teacher teacher = new Teacher();
        superUser.displayAllTeachers();

        System.out.println("Enter ID ");
        int teacherID = scanner.nextInt();

        String sql = "SELECT * FROM Teachers WHERE TeacherID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, teacherID);
        ResultSet resultSet = stmt.executeQuery();


        // Loop through the result set and create a DBStudents object for each row
        while (resultSet.next()) {
            int id = resultSet.getInt("TeacherID");
            String name = resultSet.getString("Firstname");
            String lastname = resultSet.getString("LastName");
            String email = resultSet.getString("Email");
            String subject = resultSet.getString("subjects");
            int phonenumber = resultSet.getInt("Phonenumber");
            String address = resultSet.getString("Adress");
            teacher = new Teacher(name, email, address, subject, phonenumber, lastname, id);
            listOfTeachers.add(teacher);
        }
        for (Teacher t : listOfTeachers) {
            if (t.getID() == teacherID) {
                System.out.println(t);
            }
        }
        return teacher;
    }
}



















