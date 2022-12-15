import java.sql.SQLException;
import java.util.Scanner;

public class RunSuperUser extends SuperUser {

    public void runSuperUser() throws SQLException {
        SuperUser superUser = new SuperUser();
        Bookingtest bookingtest = new Bookingtest();
        Teacher teacher = new Teacher();
        Scanner scan = new Scanner(System.in);
        DBEmployees dbEmployees = new DBEmployees();
        DBStudents dbStudents = new DBStudents();
        DBClassRooms dbClassRooms = new DBClassRooms();
        TeacherSchema teacherSchema = new TeacherSchema();
        ChangeStudentInfo changeStudentInfo = new ChangeStudentInfo();

        superUser.loginSuperUser();


        while (true) {
            System.out.println("Welcome! /n Choose your option");
            System.out.println("1. View all the Teachers");
            System.out.println("2. View all the Students");
            System.out.println("3. View all the Employees");
            System.out.println("4. Search for a certain Teacher");
            System.out.println("5. Search for a certain Student");
            System.out.println("6. Search for a certain Employee");
            System.out.println("7. Create a new Teacher to the Database");
            System.out.println("8. Create a new Student to the Database");
            System.out.println("9. Get Student information");
            System.out.println("10. Book a room");
            System.out.println("11. Unbook a room");
            System.out.println("12. Exit");

            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    //Displays all teachers
                    superUser.displayAllTeachers();
                    break;

                case 2:
                    //Displays all students
                    dbStudents.getStudentFromDatabase();
                    break;

                case 3:
                    //Displays all employees
                    dbEmployees.getEmployeesFromDB();
                    break;

                case 4:
                    //Displays a specific Teacher
                    superUser.displaySpecificTeacher();
                    break;

                case 5:
                    //Displays a specific Student
                    teacher.getAccessToStudent();
                    break;

                case 6:
                    //Displays a specific employee
                    dbEmployees.getSpecificEmployeeFromDB();
                    break;

                case 7:
                    //Creates a teacher to the database
                    superUser.createTeacher();
                    break;

                case 8:
                    //Creates a student to the database
                    superUser.createStudent();
                    break;

                case 9:
                    //Book a room
                    bookingtest.bookRoom();
                    break;

                case 10:
                    //Unbook a room
                    bookingtest.unbookRoom();
                    break;

                case 11:
                    //Gets classrooms from DB
                    dbClassRooms.getClassRoomsFromDatabase();
                    break;

                default:
                    System.exit(0);
                    break;


            }

        }


    }


}
