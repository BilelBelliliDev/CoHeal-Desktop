/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices.report;


import coheal.entities.report.Report;
import java.util.List;

/**
 *
 * @author BilelxOS
 */
public interface IReportService {
    public void addReport(int reporterId, int reportedId, String note);
    public void closeReport(int reportId);
    public void limitUser(int userId);
    public List<Report> reportsList();
}
