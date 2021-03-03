/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.report;

import coheal.entities.report.BookReport;
import coheal.entities.report.EventReport;
import coheal.entities.report.RecipeReport;
import coheal.entities.report.Report;
import coheal.entities.report.SessionReport;
import coheal.entities.report.TaskReport;
import coheal.entities.report.UserReport;
import coheal.iservices.report.IReportService;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author BilelxOS
 */
public class ReportService implements IReportService {

    Connection cnx;

    public ReportService() {
        cnx = MyConnection.getInstance().getConnection();
    }

    @Override
    public void addReport(Report r, int id) {
        String query = "INSERT INTO report(reporter_id,note) VALUES (" + r.getReporterId() + ", '" + r.getNote() + "')";
        if (r instanceof BookReport) {
            try {
                Statement stm = cnx.createStatement();
                stm.executeUpdate(query);
                query = "INSERT INTO book_report(report_id,book_id) VALUES (LAST_INSERT_ID(), " + id + ")";
                stm.executeUpdate(query);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (r instanceof EventReport) {
            try {
                Statement stm = cnx.createStatement();
                stm.executeUpdate(query);
                query = "INSERT INTO event_report(report_id,event_id) VALUES (LAST_INSERT_ID(), " + id + ")";
                stm.executeUpdate(query);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (r instanceof UserReport) {
            try {
                Statement stm = cnx.createStatement();
                stm.executeUpdate(query);
                query = "INSERT INTO user_report(report_id,user_id) VALUES (LAST_INSERT_ID(), " + id + ")";
                stm.executeUpdate(query);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (r instanceof TaskReport) {
            try {
                Statement stm = cnx.createStatement();
                stm.executeUpdate(query);
                query = "INSERT INTO task_report(report_id,task_id) VALUES (LAST_INSERT_ID(), " + id + ")";
                stm.executeUpdate(query);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (r instanceof SessionReport) {
            try {
                Statement stm = cnx.createStatement();
                stm.executeUpdate(query);
                query = "INSERT INTO session_report(report_id,session_id) VALUES (LAST_INSERT_ID(), " + id + ")";
                stm.executeUpdate(query);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (r instanceof RecipeReport) {
            try {
                Statement stm = cnx.createStatement();
                stm.executeUpdate(query);
                query = "INSERT INTO recipe_report(report_id,recipe_id) VALUES (LAST_INSERT_ID(), " + id + ")";
                stm.executeUpdate(query);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void closeReport(int reportId) {
        try {
            String query = "update report set is_closed=1 where report_id=" + reportId + "";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void banUser(int userId) {
        try {
            String query = "update user set is_limited=1 where user_id=" + userId + "";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void unbanUser(int userId) {
        try {
            String query = "update user set is_limited=0 where user_id=" + userId + "";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Report> reportsList() {
        return null;
    }

}
