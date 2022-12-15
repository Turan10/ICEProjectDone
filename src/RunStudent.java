import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Scanner;

public class RunStudent extends DBStudents {

    Student student = new Student();

    DBStudents dbstudents = new DBStudents();
    Bookingtest bookingtest = new Bookingtest();


    Scanner input = new Scanner(System.in);


    public void run() throws SQLException {
        Login login = new Login();
        login.studentLogin();
        DBStudents dbStudents = new DBStudents();


        while (true) {

            System.out.println("Welcome to the student menu");
            System.out.println("1. View my information");
            System.out.println("2. Change my information");
            System.out.println("3. Show my school class schedule");
            System.out.println("4. View all employees");
            System.out.println("5. Exit");

            int choice = input.nextInt();
            switch (choice) {
                case 1:

                    dbStudents.getSpecificStudent(login.getUsername());
                    break;
                case 2:
                    ChangeStudentInfo changeStudentInfo = new ChangeStudentInfo();
                    changeStudentInfo.infoChangeGUI();
                    break;
                case 3:
                    //getSchedule();
                    break;
                case 4:
                    DBEmployees dbemployees = new DBEmployees();
                    dbemployees.getEmployeesFromDB();
                    break;
                default:
                    System.exit(0);
                    break;
            }
        }
    }
}


