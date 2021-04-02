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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        String query;
        if (r instanceof BookRate) {
            try {
                Statement stm = cnx.createStatement();
                query = "INSERT INTO rate(user_id,type,score) VALUES (" + r.getUserId() + ",'book', " + r.getScore() + ")";
                stm.executeUpdate(query);
                query = "INSERT INTO book_rate(rate_id,book_id) VALUES (LAST_INSERT_ID(), " + id + ")";
                stm.executeUpdate(query);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (r instanceof EventRate) {
            try {
                Statement stm = cnx.createStatement();
                query = "INSERT INTO rate(user_id,type,score) VALUES (" + r.getUserId() + ",'event', " + r.getScore() + ")";
                stm.executeUpdate(query);
                query = "INSERT INTO event_rate(rate_id,event_id) VALUES (LAST_INSERT_ID(), " + id + ")";
                stm.executeUpdate(query);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (r instanceof TaskRate) {
            try {
                Statement stm = cnx.createStatement();
                query = "INSERT INTO rate(user_id,type,score) VALUES (" + r.getUserId() + ",'task', " + r.getScore() + ")";
                stm.executeUpdate(query);
                query = "INSERT INTO task_rate(rate_id,task_id) VALUES (LAST_INSERT_ID(), " + id + ")";
                stm.executeUpdate(query);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (r instanceof SessionRate) {
            try {
                Statement stm = cnx.createStatement();
                query = "INSERT INTO rate(user_id,type,score) VALUES (" + r.getUserId() + ",'session', " + r.getScore() + ")";
                stm.executeUpdate(query);
                query = "INSERT INTO session_rate(rate_id,session_id) VALUES (LAST_INSERT_ID(), " + id + ")";
                stm.executeUpdate(query);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (r instanceof RecipeRate) {
            try {
                Statement stm = cnx.createStatement();
                query = "INSERT INTO rate(user_id,type,score) VALUES (" + r.getUserId() + ",'recipe', " + r.getScore() + ")";
                stm.executeUpdate(query);
                query = "INSERT INTO recipe_rate(rate_id,recipe_id) VALUES (LAST_INSERT_ID(), " + id + ")";
                stm.executeUpdate(query);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
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

    @Override
    public List<Rate> allRatesList() {
        ObservableList<Rate> rates = FXCollections.observableArrayList();
        try {
            Statement stm = cnx.createStatement();
            String query = "select * from rate ";
            ResultSet rst = stm.executeQuery(query);
            while (rst.next()) {
                Rate r = new BookRate();
                r.setRateId(rst.getInt("rate_id"));
                r.setUserId(rst.getInt("user_id"));
                r.setType(rst.getString("type"));
                r.setScore(rst.getDouble("score"));
                r.setCreatedAt(rst.getTimestamp("created_at"));
                rates.add(r);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rates;
    }

    @Override
    public List<Rate> ratesList(String type) {
        ObservableList<Rate> rates = FXCollections.observableArrayList();
        try {
            Statement stm = cnx.createStatement();
            String query = "select x." + type + "_id, round(avg(y.score),2) as score from rate y, " + type + "_rate x where x.rate_id=y.rate_id group by x." + type + "_id";
            ResultSet rst = stm.executeQuery(query);
            while (rst.next()) {
                switch (type) {
                    case "book":
                        BookRate br = new BookRate();
                        br.setBookId(rst.getInt("" + type + "_id"));
                        br.setScore(rst.getDouble("score"));
                        rates.add(br);
                        break;
                    case "recipe":
                        RecipeRate rr = new RecipeRate();
                        rr.setRecipeId(rst.getInt("" + type + "_id"));
                        rr.setScore(rst.getDouble("score"));
                        rates.add(rr);
                        break;
                    case "task":
                        TaskRate tr = new TaskRate();
                        tr.setTaskId(rst.getInt("" + type + "_id"));
                        tr.setScore(rst.getDouble("score"));
                        rates.add(tr);
                        break;
                    case "event":
                        EventRate er = new EventRate();
                        er.setEventId(rst.getInt("" + type + "_id"));
                        er.setScore(rst.getDouble("score"));
                        rates.add(er);
                        break;
                    case "session":
                        SessionRate sr = new SessionRate();
                        sr.setSessionId(rst.getInt("" + type + "_id"));
                        sr.setScore(rst.getDouble("score"));
                        rates.add(sr);
                        break;
                }

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rates;
    }

    public double ratesListById(String type, int id) {
        double rates =0;
        try {
            Statement stm = cnx.createStatement();
            String query = "select x." + type + "_id, round(avg(y.score),2) as score from rate y, " + type + "_rate x where x.rate_id=y.rate_id and x." + type + "_id="+id+" group by x." + type + "_id";
            ResultSet rst = stm.executeQuery(query);
            while (rst.next()) {
                rates = rst.getDouble("score");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rates;
    }
}
