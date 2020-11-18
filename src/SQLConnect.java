import java.sql.*;

public class SQLConnect {
    public static void main(String[]args){
        String url = "jdbc:sqlserver://DESKTOP-GPEFG8S;databaseName=NotesAccounts";
        String user_pass = "roninkris";
        try {
            Connection connection = DriverManager.getConnection(url, user_pass, user_pass);
            System.out.println("Connected Successfully");
            //Updating row
//            String query = "insert into account_details(username, password, firstname, lastname) values(?, ?, ?, ?)";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setString(1, "Ronin");
//            statement.setString(2, "ronin");
//            statement.setString(3, "Ronin");
//            statement.setString(4, "Navoa");
//            int rows = statement.executeUpdate();
//            if(rows > 0){
//                System.out.println("Row has been added successfully");
//            }
            //Selecting row
//            String query = "Select * from account_details";
//            Statement statement = connection.createStatement();
//            ResultSet result = statement.executeQuery(query);
//            int count = 0;
//            while(result.next()){
//                count++;
//                String username = result.getString("username");
//                String password = result.getString("password");
//                String firstName = result.getString("firstname");
//                String lastName = result.getString("lastname");
//                System.out.printf("Username %s - Password %s - First Name - %s Last Name %s\n", username, password, firstName, lastName);
//            }
            connection.close();
        } catch (SQLException throwables) {
            System.out.println("Connection error");
            throwables.printStackTrace();
        }
    }
}