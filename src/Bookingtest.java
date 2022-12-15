import java.util.Date;
import java.sql.*;
import java.util.Scanner;

public class Bookingtest extends DBClassRooms {


    private int roomNumber;
    private String roomName;
    private int booked;

    DBClassRooms dbClassRooms = new DBClassRooms();
    Room room = new Room(roomNumber, roomName, booked);


    public Bookingtest() {
        this.roomNumber = roomNumber;
        this.roomName = roomName;
        this.booked = booked;

    }

    public int getRoomNumber() {
        return this.roomNumber;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getBooked() {
        return booked;
    }

    public void bookRoom() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Classroom_Schema", "root", "Tingbjerg1!");
        dbClassRooms.getClassRoomsFromDatabase();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the room ID number you want to book: ");
        int roomNumber = input.nextInt();

        // Check if the room is available
        String sql = "SELECT * FROM Subjectclassrooms WHERE classRoom_ID = ? AND booked = 0";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, roomNumber);
        ResultSet rs = stmt.executeQuery();


        if (!rs.next()) {
            throw new SQLException("Room is not available");
        }

        // Book the room
        sql = "UPDATE SubjectClassRooms SET booked = 1 WHERE classRoom_ID = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, roomNumber);
        stmt.executeUpdate();
        System.out.println("Room booked successfully");

    }

    public void unbookRoom() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Classroom_Schema", "root", "Tingbjerg1!");
        dbClassRooms.getClassRoomsFromDatabase();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the room ID number you want to unbook: ");
        int roomNumber = input.nextInt();
        // Check if the room is booked
        String sql = "SELECT * FROM SubjectClassRooms WHERE classRoom_ID = ? AND booked = 1";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, roomNumber);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            throw new SQLException("Room is not booked");
        }

        // Unbook the room
        sql = "UPDATE SubjectClassRooms SET booked = 0 WHERE classRoom_ID = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, roomNumber);
        stmt.executeUpdate();
        System.out.println("Room unbooked successfully");
    }
}

