import javax.swing.*;  // import the Swing library
import java.awt.event.ActionEvent;  // import the ActionEvent class
import java.awt.event.ActionListener;  // import the ActionListener interface
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.InputVerifier;

//This code creates a simple graphical user interface (GUI) in Java using the Swing library.
// The GUI has labels, text fields, and a button. When the button is clicked, the
// text entered in the text fields is printed to the console and a connection is established
// to a MySQL database. The code then tries to update the information for a user in the database
// with the given id and new email and phone number. If the update is successful, a message is displayed in the GUI.

public class ChangeStudentInfo implements ActionListener {

    // Declare the GUI components
    public static JLabel userlabel;
    public static JTextField userText;
    public static JLabel passwordLabel;
    public static JTextField passwordText;
    public static JButton button;
    public static JLabel success;
    public static JLabel phonenumberLabel;
    public static JTextField phonenumberText;

    // The main method is the entry point of the program
    public void infoChangeGUI() {

        // Create a new panel and set its layout to null
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Create a new frame and set its size and close operation
        JFrame frame = new JFrame();
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the panel to the frame
        frame.add(panel);

        // Create a label for the user's id
        userlabel = new JLabel("Please enter the id of the user you want to access: ");
        userlabel.setBounds(10, 20, 350, 25);  // set the position and size of the label
        panel.add(userlabel);  // add the label to the panel

        // Create a text field for the user's id
        userText = new JTextField(20);
        userText.setBounds(10, 40, 350, 25);  // set the position and size of the text field
        panel.add(userText);  // add the text field to the panel

        // Create a label for the password
        passwordLabel = new JLabel("Please enter your new email address: ");
        passwordLabel.setBounds(10, 80, 350, 25);  // set the position and size of the label
        panel.add(passwordLabel);  // add the label to the panel

        // Create a password field for the password
        passwordText = new JTextField();
        passwordText.setBounds(10, 100, 350, 25);  // set the position and size of the password field
        panel.add(passwordText);  // add the password field to the panel

        // Create a label for the phone number
        phonenumberLabel = new JLabel("Please enter your new phone number: ");
        phonenumberLabel.setBounds(10, 150, 350, 25);  // set the position and size of the label
        panel.add(phonenumberLabel);  // add the label to the panel

        // Create a phonenumber field for the phone number
        phonenumberText = new JTextField(20);
        phonenumberText.setBounds(10, 170, 350, 25);
        panel.add(phonenumberText);


        button = new JButton("Update information");
        button.setBounds(10, 200, 350, 25);
        button.addActionListener(new ChangeStudentInfo());
        panel.add(button);

        success = new JLabel("");
        success.setBounds(10, 230, 350, 25);
        panel.add(success);

        // Create an input verifier for the field
        InputVerifier verifier = new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                JTextField field = (JTextField) input;
                return !field.getText().trim().isEmpty();
            }
        };

        // Set the input verifier for the user id field
        userText.setInputVerifier(verifier);

        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String id = userText.getText();
        String email = passwordText.getText();
        String phonenumber = phonenumberText.getText();
        System.out.println(id + ", " + email + ", " + phonenumber);

        String dbUrl = "jdbc:mysql://localhost:3306/ICE";
        String username = "root";
        String password = "Tingbjerg1!";

        // Establish a connection to the database
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        String sql = "UPDATE StudentInfo SET email = ?, phone_number = ? WHERE ID = ?";
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        // Set the values of the query parameters
        try {
            statement.setString(1, email); // Use the password from the user input
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        try {
            statement.setString(2, phonenumberText.getText()); // Use the text from the phonenumber Text JTextField
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        try {
            statement.setInt(3, Integer.valueOf(id)); // Use the user from the user input
        } catch (SQLException ex) {
            //throw new RuntimeException(ex);
        }


        // Execute the query


        // Execute the query and get the number of rows affected
        int rowsUpdated = 0;
        try {
            rowsUpdated = statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        if (rowsUpdated > 0) {
            success.setText("The database was updated successfully!");
            //System.out.println("The database was updated successfully!");
        } else {
            System.out.println("An error occurred while updating the database.");
        }
    }
}