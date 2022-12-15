public class Substitute extends Teachers {
    private String name;
    private String post;
    private int ID; // ID of the teacher

    public Substitute(String name, String post, int ID) {
        this.name = name;
        this.post = post;
        this.ID = ID;
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
