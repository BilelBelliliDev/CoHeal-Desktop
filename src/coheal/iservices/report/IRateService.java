/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices.report;


import coheal.entities.rate.Rate;
import java.util.List;

/**
 *
 * @author BilelxOS
 */
public interface IRateService {
    public void addRate(Rate r, int id);
    public List<Rate> ratesList();
    public boolean isRated(int id, int userId, String type);
}
