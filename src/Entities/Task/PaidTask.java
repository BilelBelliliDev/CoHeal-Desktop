/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Task;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Admin
 */
public class PaidTask extends Task{
     private Double price;

    public PaidTask(int taskId,/*List<User> users,*/ TaskCategory category, String imgUrl, String title, String description, int numOfDays, int minUsers, int maxUsers,List<TaskActions> actions, boolean isDeleted, Timestamp deletedAt, Timestamp createdAt, Timestamp modifiedAt,Double price) {
        super(taskId,/*users,*/ category, imgUrl, title, description, numOfDays, minUsers, maxUsers,actions, isDeleted, deletedAt, createdAt, modifiedAt);
        this.price=price;
    }

    public PaidTask() {
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return super.toString()+" PaidTask{" + "price=" + price + '}';
    }
    
}
