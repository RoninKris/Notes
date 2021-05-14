import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.sql.*;

public class NotesWindow extends JFrame {
    // Constructor for adding a new note
    NotesWindow(String username){
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        this.setTitle("Add");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLayout(null);
        this.setSize(752, 630);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(0xF6FCA9)); //Change background color

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

        JButton doneButton = new JButton();
        doneButton.setBounds(325, 45, 70, 70);
        doneButton.setBackground(null);
        doneButton.setFocusPainted(false);
        doneButton.setContentAreaFilled(false);
        doneButton.setBorder(null);
        doneButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        doneButton.addActionListener(e -> AddNote(username, textArea.getText()));

        JLabel helpLabel = new JLabel("?");
        helpLabel.setBounds(650, 45, 50, 50);
        helpLabel.setFont(new Font("MV Boli", Font.PLAIN, 50));
        helpLabel.setForeground(new Color(0x9A8D3E));

        helpLabel.setToolTipText("<html>-Click the note to start typing<br>-Click the pushpin to add note</html>");
        UIManager.put("ToolTip.background", Color.WHITE);

        helpLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        JLabel backgroundLabel = new JLabel(new ImageIcon("Images/note.png"));
        backgroundLabel.setBounds(0, 0, 736, 591);

        this.add(scrollPane);
        this.add(doneButton);
        this.add(helpLabel);
        this.add(backgroundLabel);
    }
    // Constructor for editing/viewing the notes
    NotesWindow(String username, String text, String dateTime){
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        this.setTitle("Edit");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLayout(null);
        this.setSize(752, 630);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(0xF6FCA9)); //Change background color

        JTextArea textArea = new JTextArea();
        textArea.setText(text);
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

        JLabel dateTimeLabel = new JLabel(dateTime);
        dateTimeLabel.setBounds(500,280,70,70);

        JButton doneButton = new JButton();
        doneButton.setBounds(325, 45, 70, 70);
        doneButton.setBackground(null);
        doneButton.setFocusPainted(false);
        doneButton.setContentAreaFilled(false);
        doneButton.setBorder(null);
        doneButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        doneButton.addActionListener(e -> EditNote(username, textArea.getText()));
        JButton removeButton = new JButton();
        removeButton.setBounds(580, 500, 70, 70);
        removeButton.setBackground(null);
        removeButton.setFocusPainted(false);
        removeButton.setContentAreaFilled(false);
        removeButton.setBorder(null);
        removeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        removeButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Note Removed"));

        JLabel helpLabel = new JLabel("?");
        helpLabel.setBounds(650, 45, 50, 50);
        helpLabel.setFont(new Font("MV Boli", Font.PLAIN, 50));
        helpLabel.setForeground(new Color(0x9A8D3E));

        helpLabel.setToolTipText("<html>-Click the note to start typing<br>-Click the pushpin to add note<br>-Click the elevated part of paper to remove.</html>");
        UIManager.put("ToolTip.background", Color.WHITE);

        helpLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        JLabel lblNewLabel = new JLabel(new ImageIcon("Images/note.png"));
        lblNewLabel.setBounds(0, 0, 736, 591);

        this.add(scrollPane);
        this.add(doneButton);

        this.add(removeButton);

        this.add(helpLabel);
        this.add(lblNewLabel);
    }
    void AddNote(String username, String text){
        //URL Syntax: jdbc:sqlserver://[servername];databaseName=[databasename]
        String url = "jdbc:sqlserver://DESKTOP-GPEFG8S;databaseName=NotesDB";
        String pass = "roninkris";
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            //Create a connection between java and sql server
            Connection connection = DriverManager.getConnection(url, pass, pass);
            String query = "insert into " + username + "_table(noteText, dateTimeAdded) values(?, sysdatetime())";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, text.toLowerCase());
            preparedStatement.execute();
            this.dispose();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    void EditNote(String username, String text){
        //URL Syntax: jdbc:sqlserver://[servername];databaseName=[databasename]
        String url = "jdbc:sqlserver://DESKTOP-GPEFG8S;databaseName=NotesDB";
        String pass = "roninkris";
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            //Create a connection between java and sql server
            Connection connection = DriverManager.getConnection(url, pass, pass);
            String query = "insert into " + username + "_table(noteText, dateTimeAdded) values(?, sysdatetime())";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, text.toLowerCase());
            preparedStatement.execute();
            this.dispose();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    void RemoveNote(String username){

    }
}
