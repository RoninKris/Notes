import javax.swing.*;
import java.awt.*;

public class NotesWindow extends JFrame {
    JButton testButton;
    NotesWindow(){
        //Frame Cofigurations
        this.setLayout(null);
        this.setTitle("Note");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(920, 560);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(0xF6FCA9)); //Change background color

        //Buttons
        testButton = new JButton("Test");
        testButton.setBounds(60, 60, 100, 75);
        testButton.addActionListener(e -> Test());

        //Frame initialization
        this.add(testButton);
    }

    public void Test(){
        System.out.println("Test");
    }
}
