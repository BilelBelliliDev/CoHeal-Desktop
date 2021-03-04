/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.entities.task;

/**
 *
 * @author Admin
 */
public class TaskActions {
     private int actionId;
    private Task task;
    private String title;
    private String description;

    public TaskActions(int actionId, String title, String description) {
        this.actionId = actionId;
        this.title = title;
        this.description = description;
    }

    public TaskActions() {
    }

   

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TaskActions{" + "actionId=" + actionId + ", task=" + task + ", title=" + title + ", description=" + description + '}';
    }
    
}
