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

            int xIncrease = 0, yIncrease = 0;
            for(int row = 0; row < 5; row++){
                    for(int col = 0; result.next(); col++){
                        String text = result.getString("noteText");
                        DisplayNotes(text, xIncrease, yIncrease);
                        xIncrease+=250;
                        System.out.println(text);
                        if(col >= 5) break;
                    }
                    if(!result.next()) break;
                    yIncrease+= 250;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
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
        this.setVisible(true);
    }
    /*TODO:
    *  Add these to ScrollPane and try to wrap them to fit the screen*/
    void DisplayNotes(String text, int xIncrease, int yIncrease){ //Pass down the text from database. xIncrease and yIncrease is for debug only
        String newText = text.replaceAll("\r\n", "<br>"); //Replace token \r\n with <br> to display line break

        JLabel imageContainer = new JLabel();
        imageContainer.setIcon(new ImageIcon(new ImageIcon("Images/note.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        imageContainer.setBounds(50 + xIncrease,50 + yIncrease,200,200);
        JLabel textLabel = new JLabel("<html>" + text + "</html>");
        textLabel.setVerticalAlignment(JLabel.TOP);
        textLabel.setBounds(20,50,150,125);
        textLabel.setFont(new Font("MV Boli", Font.PLAIN, 14));
        imageContainer.add(textLabel);
        this.add(imageContainer);
    }

}
