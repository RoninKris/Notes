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

        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBounds(30,50,850,430);
        panel.setBackground(new Color(0xF6FCA9));
        try {
            //Create a connection between java and sql server
            Connection connection = DriverManager.getConnection(url, pass, pass);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from " + username + "_table");
            while(result.next()){
                String text = result.getString("noteText");
                panel.add(DisplayNotes(text));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //Images
        ImageIcon image = new ImageIcon("Images/Icons/note.png");
        this.setIconImage(image.getImage());
        //Frame Cofigurations
        this.setTitle("Notes");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(920, 560);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(0xF6FCA9)); //Change background color

        //Buttons
        JButton logoutButton = new JButton("return");
        logoutButton.addActionListener(e -> {
            new LoginWindow();
            this.dispose();
        });
        logoutButton.setFont(new Font("MV Boli", Font.PLAIN, 20));
        logoutButton.setBackground(null);
        logoutButton.setBorder(null);
        logoutButton.setFocusable(false);
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutButton.setContentAreaFilled(false);
        logoutButton.setBounds(800,490,115,26);

        JButton addButton = new JButton(new ImageIcon(new ImageIcon("Images/Icons/add.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        addButton.addActionListener(e -> new NotesWindow(username));
        addButton.setBackground(null);
        addButton.setBorder(null);
        addButton.setFocusable(false);
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.setContentAreaFilled(false);
        addButton.setBounds(850,20,30,30);

        //Labels
        JLabel usernameLabel = new JLabel("Hello, " + username + "!");
        usernameLabel.setFont(new Font("MV Boli", Font.PLAIN, 24));
        usernameLabel.setBounds(380,20,250,25);
        //Components initialization
        this.add(usernameLabel);
        this.add(logoutButton);
        this.add(addButton);
        this.add(panel);
        this.setVisible(true);
    }
    /*TODO:
    *  Add these to ScrollPane and try to wrap them to fit the screen*/
    JLabel DisplayNotes(String text){ //Pass down the text from database. xIncrease and yIncrease is for debug only
        String newText = text.replaceAll("\r\n", "<br>"); //Replace token \r\n with <br> to display line break

        JLabel imageContainer = new JLabel();
        imageContainer.setIcon(new ImageIcon(new ImageIcon("Images/note.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        imageContainer.setBounds(50,50,200,200);
        JLabel textLabel = new JLabel("<html>" + text + "</html>");
        textLabel.setVerticalAlignment(JLabel.TOP);
        textLabel.setBounds(20,50,150,125);
        textLabel.setFont(new Font("MV Boli", Font.PLAIN, 14));
        imageContainer.add(textLabel);
        this.add(imageContainer);
        return imageContainer;
    }

}
