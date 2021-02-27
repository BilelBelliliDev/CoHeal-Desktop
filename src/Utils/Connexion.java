/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class Connexion {

    private final static String URL = "jdbc:mysql://127.0.0.1:3306/coheal";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "";
    private static Connexion instance = null;
    private Connection con;

    private Connexion() {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connexion etablie");
        } catch (SQLException e) {
            System.out.println("pas de connexion" + e);

        }
    }

    public static Connexion getInstance() {
        if (instance == null) {
            instance = new Connexion();
        }
        return instance;
    }

    public java.sql.Connection getCon() {
        return con;
    }
}
