import java.sql.SQLException;
import java.util.Scanner;

public class RunTeacher extends Teacher {
    Teacher teacher = new Teacher();

    Bookingtest bookingtest = new Bookingtest();
    Login login = new Login();
    Scanner input = new Scanner(System.in);

    public void run() throws SQLException {

Login login  = new Login();
login.teacherLogin();
TeacherSchema teacherSchema = new TeacherSchema();

        while (true) {

            System.out.println("Welcome to the teacher menu");
            System.out.println("1. Get access to student");
            System.out.println("2. Get access to Schema");
            System.out.println("3. Get access to booking");
            System.out.println("4. Give grade to student");
            System.out.println("5. Exit");



            switch (input.nextInt()) {
                case 1:
                    teacher.getAccessToStudent();
                    break;
                case 2:
                    teacherSchema.teacherSchema(login.getID());
                    break;
                case 3:

                    System.out.println("1. book a room");
                    System.out.println("2. unbook a room");


                    switch (input.nextInt()){
                        case 1:
                            bookingtest.bookRoom();
                            break;

                        case 2:
                            bookingtest.unbookRoom();
                            break;

                    }
                       break;
                case 4:
                    teacher.giveGrade();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }


        }
    }
}