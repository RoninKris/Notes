import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class NotesWindow extends JFrame {
    NotesWindow(String username){
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
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(0xF6FCA9)); //Change background color

        //Buttons
        JButton logoutButton = new JButton();
        logoutButton = new JButton("Log out");
        logoutButton.addActionListener(e -> {
            new LoginWindow();
            this.dispose();
        });
        logoutButton.setFont(new Font("MV Boli", Font.PLAIN, 20));
        logoutButton.setBackground(null);
        logoutButton.setBorder(blackline);
        logoutButton.setFocusable(false);
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutButton.setContentAreaFilled(false);
        logoutButton.setBounds(395,70+160,115,26);

        //Labels
        JLabel usernameLabel = new JLabel(username);
        usernameLabel.setFont(new Font("MV Boli", Font.PLAIN, 70));
        usernameLabel.setBounds(100,150,920,70);
        //Components initialization
        this.add(usernameLabel);
        this.add(logoutButton);
    }

}
