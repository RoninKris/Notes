import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener{

    JButton loginButton;
    Frame(){
        //Borders
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);

        //Panels
        JPanel loginPanel = new JPanel();
        loginPanel.setBorder(blackline);
        loginPanel.setBounds(625, 50, 250, 450);
        loginPanel.setBackground(null);

        //Labels
        JLabel label = new JLabel("Something to note?");
        ImageIcon labelIcon = new ImageIcon("Images/Icons/note.png");
        label.setIcon(labelIcon);
        label.setFont(new Font("MV Boli", Font.PLAIN, 20));
        label.setBounds(50, 100, 500, 250);

        //Login Label
        JLabel loginLabel = new JLabel("Log in");
        loginLabel.setFont(new Font("MV Boli", Font.PLAIN, 30));
        loginLabel.setBounds(710, 100, 150, 40);

        //Placeholders
        JLabel usernamePlaceholder = new JLabel("username");
        usernamePlaceholder.setFont(new Font("MV Boli", Font.PLAIN, 20));
        usernamePlaceholder.setBounds(710, 220, 150, 25);

        JLabel passwordPlaceholder = new JLabel("password");
        passwordPlaceholder.setFont(new Font("MV Boli", Font.PLAIN, 20));
        passwordPlaceholder.setBounds(710, 295, 150, 25);

        //Buttons
        loginButton = new JButton();
        loginButton.setBounds(700, 375, 100, 25);
        loginButton.addActionListener(this);
        loginButton.setText("Log in");
        loginButton.setFocusable(false);
        loginButton.setFont(new Font("MV Boli", Font.PLAIN, 20));
        loginButton.setBackground(null);
        loginButton.setBorder(blackline);
        loginButton.setContentAreaFilled(false);

        //TextFields
        JTextField usernameField = new JTextField();
        usernameField.setBounds(680, 200, 150, 25);
        usernameField.setBackground(null);
        usernameField.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        usernameField.setFont(new Font("MV Boli", Font.PLAIN, 20));
        usernameField.setOpaque(true);
        usernameField.setHorizontalAlignment(JLabel.CENTER);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(680, 275, 150, 25);
        passwordField.setBackground(null);
        passwordField.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        passwordField.setFont(new Font("MV Boli", Font.PLAIN, 20));
        passwordField.setHorizontalAlignment(JLabel.CENTER);

        //Frame Cofigurations
        this.setTitle("Note");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(920, 560);
        this.setResizable(false);
        this.setVisible(true);

        //Images
        ImageIcon image = new ImageIcon("Images/Icons/note.png");
        this.setIconImage(image.getImage());

        //Content Panes
        this.getContentPane().setBackground(new Color(0xF6FCA9));

        //Frame Initialization
        this.setLayout(null);
        this.add(label);
        this.add(loginLabel);
        this.add(loginButton);
        this.add(passwordField);
        this.add(usernameField);
        this.add(usernamePlaceholder);
        this.add(passwordPlaceholder);
        this.add(loginPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==loginButton){
            System.out.println("Logged in");
        }
    }
}
