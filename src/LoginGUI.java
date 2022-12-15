/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class LoginGUI extends JDialog {
    private JTextField tfUserName;
    private JPasswordField pfPassword;
    private JButton btnLogin;
    private JPanel LoginPage;

private ArrayList<Login> teacher = new ArrayList<>();

    public Login getTeacher() {

        return teacher.get(0);
    }

    public JTextField getTfUserName() {
        return tfUserName;
    }

    public LoginGUI(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(LoginPage);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfUserName.getText();
                String password = String.valueOf(pfPassword.getText());

                user = getAuthentication(username, password);

                if (user != null) {

                    JOptionPane.showMessageDialog(LoginGUI.this, "Login successful");
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(LoginGUI.this, "Login Failed", "Try again", JOptionPane.ERROR_MESSAGE);
                }


            }
        });
        setVisible(true);
    }
    public Login user;
    public Login getAuthentication(String username, String password) {
       Login user = null;

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Classroom_Schema", "root", "Tingbjerg1!");


            String sql = "SELECT TeacherLogin.Username, TeacherLogin.Password, TeacherLogin.TeacherID__ FROM TeacherLogin WHERE Username =? AND Password =?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                user.username = resultSet.getString("username");
                user.password = resultSet.getString("password");
                user.teacherID = resultSet.getInt("TeacherID__");
             //   user = new Login(user.username, user.password, user.teacherID);
            }



            connection.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


        return user;

    }
}
*/
