import javax.swing.*;
import java.awt.image.AreaAveragingScaleFilter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class TeacherSchema extends Teachers {
    public ArrayList<TeacherSchema> schema = new ArrayList<>();
    private String day;
    private String time;
    private String subjectName;
    private String className;
    private String teacherFirstname;
    private String teacherLastname;
    private int teacherID;


    public TeacherSchema(int id, String day, String time, String subjectName, String className, String teacherFirstname, String teacherLastname) {
        this.teacherID = id;
        this.day = day;
        this.time = time;
        this.subjectName = subjectName;
        this.className = className;
        this.teacherFirstname = teacherFirstname;
        this.teacherLastname = teacherLastname;
    }

    public TeacherSchema() {

    }


    public ArrayList<TeacherSchema> getSchema() {
        return schema;
    }

    public void setSchema(ArrayList<TeacherSchema> schema) {
        this.schema = schema;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherFirstname() {
        return teacherFirstname;
    }

    public void setTeacherFirstname(String teacherFirstname) {
        this.teacherFirstname = teacherFirstname;
    }

    public String getTeacherLastname() {
        return teacherLastname;
    }

    public void setTeacherLastname(String teacherLastname) {
        this.teacherLastname = teacherLastname;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }


    public void teacherSchema(int id) throws SQLException {


        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Classroom_Schema", "root", "Tingbjerg1!");

            //Statement statement = connection.createStatement();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Class.TeacherID_,Class.Day, Class.Time,Subjects.SubjectName, Class.ClassName,  Teachers.Firstname, Teachers.LastName\n" +
                    "FROM Class\n" +
                    "INNER JOIN Teachers\n" +
                    "ON Class.TeacherID_ = Teachers.TeacherID\n" +
                    "INNER JOIN Subjects\n" +
                    "ON Class.SubjectID_ = Subjects.SubjectID\n" +
                    "WHERE Teachers.TeacherID = " + id + "");

            //.setInt(1, .teacherID);
            // preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int teacherID = resultSet.getInt("TeacherID_");
                String day = resultSet.getString("Day");
                String time = resultSet.getString("Time");
                String subjectName = resultSet.getString("SubjectName");
                String className = resultSet.getString("ClassName");
                String teacherFirstname = resultSet.getString("Firstname");
                String teacherLastname = resultSet.getString("LastName");


                TeacherSchema teacherSchema = new TeacherSchema(teacherID, day, time, subjectName, className, teacherFirstname, teacherLastname);
                schema.add(teacherSchema);
            }
            for (TeacherSchema ts : schema) {
                System.out.println(ts);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String toString() {
        return "Day: " + day + "\n" + "Time: " + time + "\n" + "Subject: " + subjectName + "\n" + "Class: " + className + "\n" + "Teacher " + teacherFirstname + " " + teacherLastname + "\n" + "--------------------------";
    }
}








