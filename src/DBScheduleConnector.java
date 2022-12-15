import java.sql.*;

//public class DBScheduleConnector {

 /*   public static void connectToAndQueryDatabase() throws SQLException, ClassNotFoundException {
        // Load the JDBC driver
        //Class.forName("com.mysql.jdbc.Driver");

        // Establish a connection to the database
    //public void connectToAndQueryDatabase() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ICE", "root", "martin123");

            // Create a statement for executing queries
            Statement stmt = con.createStatement();

            // Execute a SELECT query to get data from the SchoolSchedule table
            ResultSet rs = stmt.executeQuery("SELECT SchoolClass, WeekDay, SchoolSubjects, AssignedTeacher, TeachingTime FROM SchoolSchedule");

            // Loop through the result set and print out each row of data
            while (rs.next()) {
                int SC = rs.getInt("SchoolClass");
                String WD = rs.getString("WeekDay");
                String SS = rs.getString("SchoolSubjects");
                String AT = rs.getString("AssignedTeacher");
                int TT = rs.getInt("TeachingTime");

                //System.out.println(SC + " " + WD + " " + SS + " " + AT + " " + TT);
            }
        } catch (Exception e) {
            System.out.println("Der skete en fejl");
        }

    }*/

/*
    public void CreateDBSchedule() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ICE", "root", "martin123");

            // Create a prepared statement for creating the SchoolSchedule table
            PreparedStatement createTable = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS SchoolSchedule ( " +
                            "SchoolClass int NOT NULL AUTO_INCREMENT, " +
                            "WeekDay varchar(255), " +
                            "SchoolSubjects varchar(255), " +
                            "AssignedTeacher varchar(255), " +
                            "TeachingTime varchar(255), " +
                            "PRIMARY KEY (SchoolClass) " +
                            ")"
            );

            // Execute the prepared statement to create the table
            createTable.executeUpdate();

        } catch (Exception e) {
            // Print any exceptions that occur
            e.printStackTrace();
        } finally {
            // Print a message when the table has been created
            System.out.println("Your table has been created");
        }
    }
}

 */