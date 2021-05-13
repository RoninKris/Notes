import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class AddNotes extends JFrame {
    AddNotes(){
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        this.setTitle("Edit");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(752, 630);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(0xF6FCA9)); //Change background color

        JLabel lblAddEditNote = new JLabel(" Add/ Edit Note");
        lblAddEditNote.setBackground(new Color(255, 160, 122));
        lblAddEditNote.setFont(new Font("MV Boli", Font.PLAIN, 36));
        lblAddEditNote.setBounds(78, 0, 257, 47);
        Color myColor1 = new Color(255, 160, 122);
        this.getContentPane().add(lblAddEditNote);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("MV Boli", Font.ITALIC, 20));
        textArea.setLineWrap(true);
        textArea.setBackground(new Color(255,255,255,0));
        textArea.setOpaque(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(136, 120+10, 450-10, 400-10);
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
        this.add(scrollPane);

        JButton doneButton = new JButton("DONE");
        doneButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
        doneButton.setBounds(537, 510, 148, 70);
        doneButton.setBackground(null);
        doneButton.setFocusPainted(false);
        doneButton.setContentAreaFilled(false);
        doneButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        this.add(doneButton);

        JLabel lblNewLabel = new JLabel(new ImageIcon("Images/note.png"));
        lblNewLabel.setBounds(0, 0, 736, 591);
        this.add(lblNewLabel);
    }
}
