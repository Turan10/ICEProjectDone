public class Room extends TeacherSchema {
    private int roomID;
    private String roomName;
    private boolean booked1 = false;

    private int booked;
    private String bookedBy;


    public Room(int roomID, String roomName, int booked) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.booked = booked;

    }

    public Room() {

    }

    int getRoomID() {
        return roomID;
    }

    void cancelBooking() {
        booked1 = false;

    }

    boolean isBooked() {
        return booked1;
    }

    public boolean setBooked(boolean booked1) {
        if (booked == 1) {
            this.booked1 = true;
        } else {
            return false;
        }
        return true;
    }


    public String toString() {
        return "Classroom: " + roomName + " " + "ID: " + roomID + " " + "Booked: " + setBooked(booked1);
    }
}
