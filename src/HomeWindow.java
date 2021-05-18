import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.sql.*;
public class HomeWindow extends JFrame {
    HomeWindow(String username){
        //URL Syntax: jdbc:sqlserver://[servername];databaseName=[databasename]
        String url = "jdbc:sqlserver://DESKTOP-GPEFG8S;databaseName=NotesDB";
        String pass = "roninkris";

        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        JPanel panel = new JPanel();
        panel.setLayout(new WrapLayout(WrapLayout.LEFT));
        panel.setBounds(30,50,850,430);
        panel.setBackground(new Color(0xF6FCA9));
        try {
            //Create a connection between java and sql server
            Connection connection = DriverManager.getConnection(url, pass, pass);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select id, noteText, convert(varchar, dateTimeAdded, 0) as dateTimeAdded from " + username + "_table");
            int count = 0;
            while(result.next()){
                count++;
                int id = result.getInt("id");
                String text = result.getString("noteText");
                String dateTime = result.getString("dateTimeAdded");
                panel.add(DisplayNotes(username, id, text, dateTime));
            }
            //If there are no notes yet, do this
            JLabel emptyLabel = new JLabel("You have no notes yet.");
            emptyLabel.setFont(new Font("MV Boli", Font.BOLD, 35));
            emptyLabel.setForeground(new Color(0x9A8D3E));
            if(count == 0){
                panel.setLayout(new GridBagLayout());
                panel.add(emptyLabel);
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
        addButton.addActionListener(e -> {
            new NotesWindow(username);
            this.dispose();
        });
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

        //ScrollPane
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(30,50,850,430);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setBackground(new Color(0xD1BF56));
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0xf0dc64);
            }
        });
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        //Components initialization
        this.add(usernameLabel);
        this.add(logoutButton);
        this.add(addButton);
        this.add(scrollPane);
        this.setVisible(true);
    }
    JLabel DisplayNotes(String username, int id, String text, String dateTime){ //Pass down the text from database. xIncrease and yIncrease is for debug only
        String newText = text.replaceAll("\r\n", "<br>"); //Replace token \r\n with <br> to display line break

        JLabel imageContainer = new JLabel();
        imageContainer.setIcon(new ImageIcon(new ImageIcon("Images/note.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        imageContainer.setBounds(50,50,200,200);
        JButton textButton = new JButton("<html>" + newText + "</html>");
        textButton.setVerticalAlignment(JLabel.TOP);
        textButton.setBounds(20,50,150,125);
        textButton.setFont(new Font("MV Boli", Font.PLAIN, 14));
        textButton.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        textButton.setContentAreaFilled(false);
        textButton.setBorder(null);
        textButton.setFocusable(false);
        textButton.addActionListener(e -> {
            new NotesWindow(username, id, text, dateTime);
            this.dispose();
        });
        imageContainer.add(textButton);
        this.add(imageContainer);
        return imageContainer;
    }

}
