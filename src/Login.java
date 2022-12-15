import com.mysql.cj.log.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Login extends DBEmployees {

    private String username;
    private String password;
    private int ID;


    public Login(String username, String password, int ID) {
        this.username = username;
        this.password = password;
        this.ID = ID;

    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Login() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Login teacherLogin() {
        Login user = new Login();
        ArrayList<Login> loginArrayList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Classroom_Schema", "root", "Tingbjerg1!");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT TeacherLogin.TeacherID__,  TeacherLogin.Username, TeacherLogin.password FROM TeacherLogin");


            while (resultSet.next()) {
                int id = resultSet.getInt("TeacherID__");
                String username = resultSet.getString("Username");
                String password = resultSet.getString("password");
                user = new Login(username, password, id);

                loginArrayList.add(user);

            }

            Scanner scan = new Scanner(System.in);

            System.out.println("Username:");
            String userName = scan.nextLine();
            System.out.println("Password");
            String password = scan.nextLine();

            boolean found = false;
            for (Login s : loginArrayList) {
                if (s.getUsername().equals(userName) && s.getPassword().equals(password)) {
                    user = new Login(userName, password, s.getID());
                    System.out.println("Login Sucessfull" + " " + s.getUsername());
                    this.ID = s.getID();
                    found = true;

                }
            }
            if (found == false) {
                System.out.println("Login wasnt succesful");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;


    }

    public Login studentLogin() {

        Login student = new Login();
        ArrayList<Login> studentLogin = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ICE", "root", "Tingbjerg1!");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT StudentInfo.username, StudentInfo.password FROM StudentInfo");


            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                student = new Login(username, password);

                studentLogin.add(student);

            }
            Scanner input = new Scanner(System.in);

            System.out.println("Username:");
            String userName = input.nextLine();
            System.out.println("Password");
            String password = input.nextLine();
            Login user1 = new Login(userName, password);

            boolean found = false;
            for (Login sa : studentLogin) {
                if (sa.getUsername().equals(userName) && sa.getPassword().equals(password)) {
                    System.out.println("Login Sucessfull" + " " + sa.getUsername());
                    found = true;
                    this.username = sa.getUsername();
                    found = true;
                }
            }
            if (found == false) {
                System.out.println("Login wasnt succesful");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }
}


