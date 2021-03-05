/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.report;

import coheal.entities.rate.BookRate;
import coheal.entities.rate.EventRate;
import coheal.entities.rate.Rate;
import coheal.entities.rate.RecipeRate;
import coheal.entities.rate.SessionRate;
import coheal.entities.rate.TaskRate;
import coheal.iservices.report.IRateService;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BilelxOS
 */
public class RateService implements IRateService {

    Connection cnx;

    public RateService() {
        cnx = MyConnection.getInstance().getConnection();
    }

    @Override
    public void addRate(Rate r, int id) {
        String query = "INSERT INTO rate(user_id,score) VALUES (" + r.getUserId() + ", " + r.getScore() + ")";
        if (r instanceof BookRate) {
            try {
                Statement stm = cnx.createStatement();
                stm.executeUpdate(query);
                query = "INSERT INTO book_rate(rate_id,book_id) VALUES (LAST_INSERT_ID(), " + id + ")";
                stm.executeUpdate(query);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (r instanceof EventRate) {
            try {
                Statement stm = cnx.createStatement();
                stm.executeUpdate(query);
                query = "INSERT INTO event_rate(rate_id,event_id) VALUES (LAST_INSERT_ID(), " + id + ")";
                stm.executeUpdate(query);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (r instanceof TaskRate) {
            try {
                Statement stm = cnx.createStatement();
                stm.executeUpdate(query);
                query = "INSERT INTO task_rate(rate_id,task_id) VALUES (LAST_INSERT_ID(), " + id + ")";
                stm.executeUpdate(query);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (r instanceof SessionRate) {
            try {
                Statement stm = cnx.createStatement();
                stm.executeUpdate(query);
                query = "INSERT INTO session_rate(rate_id,session_id) VALUES (LAST_INSERT_ID(), " + id + ")";
                stm.executeUpdate(query);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (r instanceof RecipeRate) {
            try {
                Statement stm = cnx.createStatement();
                stm.executeUpdate(query);
                query = "INSERT INTO recipe_rate(rate_id,recipe_id) VALUES (LAST_INSERT_ID(), " + id + ")";
                stm.executeUpdate(query);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public List<Rate> ratesList() {
        return null;
    }

    @Override
    public boolean isRated(int id, int userId, String type) {
        switch (type) {
            case "Book":
                try {
                    Statement stm = cnx.createStatement();
                    String query = "select * from rate r, book_rate br where r.user_id=" + userId + " and br.book_id=" + id + " and r.rate_id=br.rate_id";
                    ResultSet rst = stm.executeQuery(query);
                    while (rst.next()) {
                        return true;
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                return false;
            case "Recipe":
                try {
                    Statement stm = cnx.createStatement();
                    String query = "select * from rate r, recipe_rate rr where r.user_id=" + userId + " and rr.recipe_id=" + id + " and r.rate_id=rr.rate_id";
                    ResultSet rst = stm.executeQuery(query);
                    while (rst.next()) {
                        return true;
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                return false;
            case "Task":
                try {
                    Statement stm = cnx.createStatement();
                    String query = "select * from rate r, task_rate tr where r.user_id=" + userId + " and tr.task_id=" + id + " and r.rate_id=tr.rate_id";
                    ResultSet rst = stm.executeQuery(query);
                    while (rst.next()) {
                        return true;
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                return false;
            case "Event":
                try {
                    Statement stm = cnx.createStatement();
                    String query = "select * from rate r, event_rate er where r.user_id=" + userId + " and er.event_id=" + id + " and r.rate_id=er.rate_id";
                    ResultSet rst = stm.executeQuery(query);
                    while (rst.next()) {
                        return true;
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                return false;
            case "Session":
                try {
                    Statement stm = cnx.createStatement();
                    String query = "select * from rate r, session_rate sr where r.user_id=" + userId + " and sr.session_id=" + id + " and r.rate_id=sr.rate_id";
                    ResultSet rst = stm.executeQuery(query);
                    while (rst.next()) {
                        return true;
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                return false;
            default:
                return false;
        }

    }

}
