import darrylbu.icon.StretchIcon;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegisterWindow extends JFrame{

    JButton registerButton;
    JTextField lastNameField;
    JPasswordField passwordField;
    RegisterWindow(){
        //Borders
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        JButton backButton = new JButton("Return");
        backButton.setBounds(0,2,120,70);
        backButton.setBorder(null);
        backButton.setBackground(null);
        backButton.setFocusable(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setContentAreaFilled(false);
        backButton.setFont(new Font("MV Boli", Font.PLAIN, 18));
        backButton.addActionListener(e -> {
            new LoginWindow();
            this.dispose();
        });
        //Panels
        JPanel registerPanel1 = new JPanel();
        registerPanel1.setBorder(blackline);
        registerPanel1.setLayout(null);
        registerPanel1.setBounds(200, 50, 250, 450);
        registerPanel1.setBackground(new Color(0xF6FCA9));
        //Personal Info Label
        JLabel personalLabel = new JLabel("Personal");
        personalLabel.setFont(new Font("MV Boli", Font.PLAIN, 24));
        personalLabel.setBounds(80, 10, 200, 100);
        registerPanel1.add(personalLabel);

        JLabel informationLabel = new JLabel("Information");
        informationLabel.setFont(new Font("MV Boli", Font.PLAIN, 24));
        informationLabel.setBounds(60, 40, 200,100);
        registerPanel1.add(informationLabel);

        JPanel registerPanel2 = new JPanel();
        registerPanel2.setBorder(blackline);
        registerPanel2.setBounds(500, 50, 250, 450);
        registerPanel2.setBackground(new Color(0xF6FCA9));
        //Register Label
        JLabel registerLabel = new JLabel("Register");
        registerLabel.setFont(new Font("MV Boli", Font.PLAIN, 24));
        //registerLabel.setBounds(710, 100, 150, 40);
        registerLabel.setHorizontalAlignment(JLabel.CENTER);
        registerPanel2.add(registerLabel);

        //Labels
        JLabel label = new JLabel("Something to note?");
        ImageIcon labelIcon = new ImageIcon("Images/Icons/note.png");
        label.setIcon(labelIcon);
        label.setFont(new Font("MV Boli", Font.PLAIN, 20));
        label.setBounds(50, 100, 500, 250);

        //Placeholders
        JLabel firstNameLabel = new JLabel("First Name");
        firstNameLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        firstNameLabel.setBounds(710-435, 220, 150, 25);

        JLabel lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        lastNameLabel.setBounds(710-435, 295, 150, 25);

        //TextFields
        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(680-435, 200, 150, 25);
        firstNameField.setBackground(null);
        firstNameField.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        firstNameField.setFont(new Font("MV Boli", Font.PLAIN, 20));
        firstNameField.setOpaque(true);
        firstNameField.setHorizontalAlignment(JLabel.CENTER);
        firstNameField.addActionListener(e -> FocusLastName(lastNameField));

        lastNameField = new JTextField();
        lastNameField.setBounds(680-435, 275, 150, 25);
        lastNameField.setBackground(null);
        lastNameField.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        lastNameField.setFont(new Font("MV Boli", Font.PLAIN, 20));
        lastNameField.setHorizontalAlignment(JLabel.CENTER);
        //----------------------------------------------------------------------------------------------
        //Placeholders
        JLabel usernamePlaceholder = new JLabel("username");
        usernamePlaceholder.setFont(new Font("MV Boli", Font.PLAIN, 20));
        usernamePlaceholder.setBounds(710-125, 220, 150, 25);

        JLabel passwordPlaceholder = new JLabel("password");
        passwordPlaceholder.setFont(new Font("MV Boli", Font.PLAIN, 20));
        passwordPlaceholder.setBounds(710-125, 295, 150, 25);

        //TextFields
        JTextField usernameField = new JTextField();
        usernameField.setBounds(680-125, 200, 150, 25);
        usernameField.setBackground(null);
        usernameField.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        usernameField.setFont(new Font("MV Boli", Font.PLAIN, 20));
        usernameField.setOpaque(true);
        usernameField.setHorizontalAlignment(JLabel.CENTER);
        usernameField.addActionListener(e -> FocusPassword(passwordField));

        passwordField = new JPasswordField();
        passwordField.setBounds(680-125, 275, 150, 25);
        passwordField.setBackground(null);
        passwordField.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        passwordField.setFont(new Font("MV Boli", Font.PLAIN, 20));
        passwordField.setHorizontalAlignment(JLabel.CENTER);
        passwordField.addActionListener(e -> Register(firstNameField.getText(), lastNameField.getText(), usernameField.getText(), passwordField.getText()));

        //Buttons
        registerButton = new JButton();
        registerButton.setBounds(710-125, 375, 100, 25);
        registerButton.addActionListener(e -> Register(firstNameField.getText(), lastNameField.getText(), usernameField.getText(), passwordField.getText()));
        registerButton.setText("Sign up");
        registerButton.setFocusable(false);
        registerButton.setFont(new Font("MV Boli", Font.PLAIN, 20));
        registerButton.setBackground(null);
        registerButton.setBorder(blackline);
        registerButton.setContentAreaFilled(false);
        registerButton.setCursor(Cursor.getPredefinedCursor(12));

        //Frame Cofigurations
        this.setLayout(null);
        this.setTitle("Register");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(920, 560);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(0xF6FCA9)); //Change background color

        //Images
        ImageIcon image = new ImageIcon("Images/Icons/note.png");
        this.setIconImage(image.getImage());

        //Frame Initialization
        this.add(backButton);
        this.add(registerButton);
        this.add(passwordField);
        this.add(usernameField);
        this.add(usernamePlaceholder);
        this.add(passwordPlaceholder);

        this.add(firstNameField);
        this.add(firstNameLabel);
        this.add(lastNameField);
        this.add(lastNameLabel);
        this.add(registerPanel1);
        this.add(registerPanel2);
        this.add(label);
    }

    public void FocusLastName(JTextField lastNameField){
        lastNameField.requestFocusInWindow();
    }
    public void FocusPassword(JPasswordField passwordField){
        passwordField.requestFocusInWindow();
    }
    public void Register(String firstName, String lastName, String username, String password) {
        //URL Syntax: jdbc:sqlserver://[servername];databaseName=[databasename]
        String url = "jdbc:sqlserver://DESKTOP-GPEFG8S;databaseName=NotesAccounts";
        String pass = "roninkris";

        if (firstName.equals("") || lastName.equals("") || username.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(this, "Please fill up all the field");
        }
        else if (username.length() < 8 || password.length() < 8){
            JOptionPane.showMessageDialog(this, "Username and password must contain at least 8 characters");
        }
            else {
            try {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                //Create a connection between java and sql server
                Connection connection = DriverManager.getConnection(url, pass, pass);
                String query = "insert into account_details(username, password, firstname, lastname) values(?, ?, ? ,?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, username.toLowerCase());
                statement.setString(2, password.toLowerCase());
                statement.setString(3, firstName.toLowerCase());
                statement.setString(4, lastName.toLowerCase());
                //Check if username exists
                Statement checkAccounts = connection.createStatement();
                ResultSet result = checkAccounts.executeQuery("select * from account_details");
                boolean exists = false;
                while(result.next()){
                    String checkUsername = result.getString("username");
                    if(checkUsername.equals(username)){
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Registration Complete");
                    new LoginWindow();
                    this.dispose();
                } else JOptionPane.showMessageDialog(this, "Account already exists");
                this.setCursor(Cursor.getDefaultCursor());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
