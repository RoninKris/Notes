import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class HomeWindow extends JFrame {
    HomeWindow(String username){
        //URL Syntax: jdbc:sqlserver://[servername];databaseName=[databasename]
        String url = "jdbc:sqlserver://DESKTOP-GPEFG8S;databaseName=NotesDB";
        String pass = "roninkris";
        try {
            //Create a connection between java and sql server
            Connection connection = DriverManager.getConnection(url, pass, pass);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from " + username + "_table");
            int count = 0;

            while(result.next()){
                String text = result.getString("noteText");
                String dateTime = result.getString("dateTimeAdded");

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Border blackline = BorderFactory.createLineBorder(Color.BLACK);

        // Preview
        JLabel imageContainer = new JLabel();
        imageContainer.setIcon(new ImageIcon(new ImageIcon("Images/note.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        imageContainer.setBounds(50,50,200,200);
        JLabel textLabel = new JLabel("<html>To do:<br>- Eat<br>- Sleep<br>- Repeat</html>");
        textLabel.setVerticalAlignment(JLabel.TOP);
        textLabel.setBounds(20,50,150,125);
        textLabel.setFont(new Font("MV Boli", Font.PLAIN, 14));
        this.add(imageContainer);
        imageContainer.add(textLabel);

        //Images
        ImageIcon image = new ImageIcon("Images/Icons/note.png");
        this.setIconImage(image.getImage());
        //Frame Cofigurations
        this.setTitle("Notes");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(920, 560);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(0xF6FCA9)); //Change background color

        //Buttons
        JButton logoutButton = new JButton("Log out");
        logoutButton.addActionListener(e -> {
            new LoginWindow();
            this.dispose();
        });
        JButton addButton = new JButton("Add");

        logoutButton.setFont(new Font("MV Boli", Font.PLAIN, 20));
        logoutButton.setBackground(null);
        logoutButton.setBorder(blackline);
        logoutButton.setFocusable(false);
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutButton.setContentAreaFilled(false);
        logoutButton.setBounds(395,70+160,115,26);

        addButton.addActionListener(e -> new NotesWindow(username));
        addButton.setFont(new Font("MV Boli", Font.PLAIN, 20));
        addButton.setBackground(null);
        addButton.setBorder(blackline);
        addButton.setFocusable(false);
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.setContentAreaFilled(false);
        addButton.setBounds(750,0,115,26);

        //Labels
        JLabel usernameLabel = new JLabel("Hello, " + username + "!");
        usernameLabel.setFont(new Font("MV Boli", Font.PLAIN, 24));
        usernameLabel.setBounds(380,0,250,25);
        //Components initialization
        this.add(usernameLabel);
        this.add(logoutButton);
        this.add(addButton);
    }


}
