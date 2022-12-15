import java.util.ArrayList;

public abstract class Teachers extends DBEmployees {
    private String name;
    private String post;
    private int ID; // ID of the teacher

    public Teachers(int ID, String firstName, String lastName, String email, String adress, int phoneNumber) {
        this.name = name;
        this.post = post;
        this.ID = ID;
    }

    public Teachers() {

    }

    public String getName() {
        return name;
    }

    public String getPost() {
        return post;
    }

    public int getID() {
        return ID;
    }

}
