package TeachersChat.server;

import java.sql.*;

public class AuthService {

    private static Connection connection;
    private static Statement stmt;

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:userTestDB.db");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static String getNickByLoginAndPass(String login, String pass) {

        String sql = String.format("select nickname FROM userTable where" +
                " login = '%s' and password = '%s'", login, pass);
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static synchronized void addMessageToSQL(String nickname,String message){
        String sql = String.format("INSERT INTO messages (nickname, message)" +
                "VALUES ('%s', '%s');",nickname,message);
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void sendAllPastMessages(ClientHandler clientHandler){
        String sql = "SELECT nickname, message FROM messages;";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                String nickname = rs.getString("nickname");
                String message = rs.getString("message");
                clientHandler.sendMsg(nickname+" "+message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
