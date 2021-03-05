/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.entities.rate;

import java.sql.Timestamp;

/**
 *
 * @author BilelxOS
 */
public abstract class Rate {
    protected int rateId;
    protected int userId;
    protected String type;
    protected double score;
    protected Timestamp createdAt;

    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateID) {
        this.rateId = rateID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userID) {
        this.userId = userID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
}
