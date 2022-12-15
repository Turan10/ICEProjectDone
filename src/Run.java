import java.sql.SQLException;
import java.util.Scanner;

public class Run {


    public void runProgram() throws SQLException {
        Scanner input = new Scanner(System.in);


        System.out.println("Welcome to Ny-Turans Heldagsskole");
        System.out.println("1. Teacher Login");
        System.out.println("2. Student Login");
        System.out.println("3. Superuser Login");
        System.out.println("4. Exit");

        switch (input.nextInt()) {
            case 1:
                RunTeacher runTeacher = new RunTeacher();
                runTeacher.run();
                break;
            case 2:
                RunStudent runStudent = new RunStudent();
                runStudent.run();
                break;
            case 3:
                RunSuperUser runSuperUser = new RunSuperUser();
                runSuperUser.runSuperUser();
                break;
            case 4:
                System.exit(0);
                break;
        }

    }
}
