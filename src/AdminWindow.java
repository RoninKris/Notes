import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class AdminWindow extends JFrame {
    JLabel resultLabel;
    JTextField horizontalField;
    int horizontalAlignment;
    AdminWindow(){
        ImageIcon icon = new ImageIcon("Images/Icons/note.png");
        //Frame Cofigurations
        this.setLayout(null);
        this.setTitle("Admin");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(920, 560);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(0xF6FCA9)); //Change background color
        JButton backButton = new JButton("Return");
        backButton.setBounds(0,450,120,70);
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
        this.add(backButton);
        getTable();

    }

    public void getTable(){
        String url = "jdbc:sqlserver://DESKTOP-GPEFG8S;databaseName=NotesAccounts";
        String pass = "roninkris";
        try {
            Connection connection = DriverManager.getConnection(url, pass, pass);
            String query = "select * from account_details";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            String resultText = "<html><table border = 1 style='width: 800; background-color: white;'>" +
                    "<th>Username</th>Password<th>First Name</th><th>Last Name</th>";
            int tableHeight = 90, count = 0;
            while(result.next()){
                count++;
                String username = result.getString("username");
                String password = result.getString("password");
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");
                resultText+="<tr>" +
                        "<td>" + count + ". " + username + "</td>" +
                        "<td>" + password + "</td>" +
                        "<td>" + firstName + "</td>" +
                        "<td>" + lastName + "</td></tr>";
                if(count > 2) tableHeight+=30;
            }
            resultLabel = new JLabel();
            resultLabel.setBounds(0,0,800,tableHeight);
            resultLabel.setText(resultText);
            JPanel tablePanel = new JPanel();
            tablePanel.setBounds(50,0, 800, tableHeight);
            tablePanel.setBackground(null);
            tablePanel.add(resultLabel);
            this.add(tablePanel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
