package coheal.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    final static String URL = "jdbc:mysql://localhost:3306/coheal";
    final static String username = "root";
    final static String password = "";
    private static MyConnection instance = null;
    private Connection cnx;
    
    private MyConnection(){
        try {
            cnx = DriverManager.getConnection(URL, username, password);
            System.out.println("Connection done");
        } catch (SQLException ex) {
            System.out.println("Connection failed");
        }
    }
    public static MyConnection getInstance(){
        if(instance == null)
            instance = new MyConnection();
        return instance;
    }
    
    public Connection getConnection(){
        return cnx;
    }
}