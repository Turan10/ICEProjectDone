import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBClassRooms extends Room {

    public ArrayList<Room> classRooms = new ArrayList<>();

    private String classRoomName;
    private int classRoomID;
    private int booked;


    public void addRoom(Room addRoom) {
        classRooms.add(addRoom);
    }

    public ArrayList<Room> getClassRooms() {
        return classRooms;
    }


    public void getClassRoomsFromDatabase() {
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Classroom_Schema", "root", "Tingbjerg1!");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT  SubjectClassRooms.classRoom_ID, SubjectClassRooms.classRoom_name, SubjectClassRooms.booked FROM SubjectClassRooms");


            while (resultSet.next()) {
                //Want our usernames to be printed
                int classRoomID = resultSet.getInt("classRoom_ID");
                String classRoomName = resultSet.getString("classRoom_Name");
                int booked = resultSet.getInt("booked");

                Room room = new Room(classRoomID, classRoomName, booked);
                this.classRooms.add(room);
            }

            for (Room r : classRooms) {
                System.out.println(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String toString() {
        return "classRoomID: " + classRoomID + " classRoomName: " + classRoomName + " booked: " + booked;
    }
}

