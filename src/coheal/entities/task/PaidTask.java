/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.entities.task;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Admin
 */
public class PaidTask extends Task{
    private Double price;
     private TaskChat taskChat;

  


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public TaskChat getTaskChat() {
        return taskChat;
    }

    public void setTaskChat(TaskChat taskChat) {
        this.taskChat = taskChat;
    }

    
    @Override
    public String toString() {
        return super.toString()+" PaidTask{" + "price=" + price +" taskChat="+taskChat+ '}';
    }
    
}
