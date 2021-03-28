/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.ui;

import coheal.entities.book.Book;
import coheal.entities.book.BookCategory;
import coheal.entities.event.Event;
import coheal.entities.event.EventCategory;
import coheal.entities.recipe.Recipe;
import coheal.entities.recipe.RecipeCategory;
import coheal.entities.session.Session;
import coheal.entities.task.Task;
import coheal.entities.task.TaskCategory;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author BilelxOS
 */
public class UIService {

    Connection cnx;

    public UIService() {
        cnx = MyConnection.getInstance().getConnection();
    }

    public ObservableList<Book> topThreeBook() {
        ObservableList<Book> data = FXCollections.observableArrayList();
        try {

            Statement st = cnx.createStatement();
            String query = "select b.* , x.book_id, round(avg(y.score),2) as score from rate y, book_rate x, book b where x.rate_id=y.rate_id and x.book_id=b.book_id and b.is_deleted=0 group by x.book_id order by score desc limit 3";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Book b = new Book();
                b.setImgUrl(rs.getString("b.img_url"));
                b.setTitle(rs.getString("b.title"));
                b.setAuthor(rs.getString("b.author"));
                b.setDescription(rs.getString("b.description"));
                b.setBookId(rs.getInt("b.book_id"));
                b.setViews(rs.getInt("b.views"));
                data.add(b);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return data;
    }

    public ObservableList<Event> upcomingEvent() throws SQLException {

        Statement stm = cnx.createStatement();
        String query = "select * from `event` where is_deleted=0 order by created_at desc limit 2";

        ResultSet rst = stm.executeQuery(query);

        ObservableList<Event> Events = FXCollections.observableArrayList();

        while (rst.next()) {
            Event e = new Event();
            e.setEventId(rst.getInt("event_id"));
            e.setTitle(rst.getString("title"));
            e.setDescription(rst.getString("description"));

            //e.setStartTime(rst.getTime("start_time"));
            //e.setEndTime(rst.getTime("end_time"));
            e.setStartDate(rst.getDate("start_date"));
            e.setEndDate(rst.getDate("end_date"));
            e.setLocation(rst.getString("location"));
            e.setType(rst.getString("type"));
            Events.add(e);
        }

        return Events;

    }

    public ObservableList<Task> topThreeTask() {
        ObservableList<Task> t = FXCollections.observableArrayList();
        try {

            Statement st = cnx.createStatement();
            String query = "select t.* , x.task_id, round(avg(y.score),2) as score from rate y, task_rate x, task t where x.rate_id=y.rate_id and x.task_id=t.task_id and t.is_deleted=0 group by x.task_id order by score desc limit 3";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                t.add(new Task(rs.getInt("task_id"), rs.getInt("cat_id"), rs.getString("title"), rs.getString("description"), rs.getInt("num_of_days"), rs.getInt("min_users"), rs.getInt("max_users")));
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage");
        }
        return t;
    }

    public ObservableList<Recipe> topThreeRecipe() {
        ObservableList<Recipe> ListR = FXCollections.observableArrayList();
        try {

            Statement st = cnx.createStatement();
            String query = "select r.* , x.recipe_id, round(avg(y.score),2) as score from rate y, recipe_rate x, recipe r where x.rate_id=y.rate_id and x.recipe_id=r.recipe_id and r.is_deleted=0 group by x.recipe_id order by score desc limit 3";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Recipe r = new Recipe();
                RecipeCategory rc = new RecipeCategory();
                r.setRecipeId(rs.getInt("r.recipe_id"));
                r.setUserId(rs.getInt("r.user_id"));
               // r.setCat(rs.getInt("rc.cat_id"));
                r.setTitle(rs.getString("r.title"));
                r.setDescription(rs.getString("r.description"));
                ListR.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListR;
    }

    public ObservableList<Session> newSessions() throws SQLException {

        Statement stm = cnx.createStatement();
        String query = "select * from `session` where is_deleted=0 order by created_at desc limit 3";

        ResultSet rst = stm.executeQuery(query);

        ObservableList<Session> sessions = FXCollections.observableArrayList();

        while (rst.next()) {
            Session e = new Session();
            e.setTherpId(rst.getInt("therp_id"));
            e.setSessionId(rst.getInt("session_id"));
            e.setTitle(rst.getString("title"));
            e.setDescription(rst.getString("description"));
            sessions.add(e);
        }

        return sessions;
    }

    public String therpSession(int sessionId) throws SQLException {

        Statement stm = cnx.createStatement();
        String query = "select u.first_name, u.last_name from user u, session s where s.is_deleted=0 and u.user_id=s.therp_id and s.session_id=" + sessionId + "";
        ResultSet rst = stm.executeQuery(query);
        String user = "";
        while (rst.next()) {
            user = rst.getString("u.first_name") + " " + rst.getString("u.last_name");
        }

        return user;
    }

    public ObservableList<TaskCategory> topThreeCatg() {
        ObservableList<TaskCategory> t = FXCollections.observableArrayList();
        TaskCategory taskCategory = new TaskCategory();
        try {

            Statement st = cnx.createStatement();
            String query = "SELECT tc.cat_id, tc.name, tc.img_url, count(*) as total_tasks FROM task t,task_category tc WHERE t.cat_id=tc.cat_id and tc.is_deleted=0 group by tc.cat_id ORDER by total_tasks desc limit 3";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                t.add(new TaskCategory(rs.getInt("tc.cat_id"), rs.getString("tc.name"), rs.getString("tc.img_url")));
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage");
        }
        return t;
    }

    public ObservableList<BookCategory> topThreeBookCatg() {
        ObservableList<BookCategory> data = FXCollections.observableArrayList();
        try {

            Statement st = cnx.createStatement();
            String query = "SELECT bc.cat_id, bc.name, bc.img_url, count(*) as total_books FROM book b,book_category bc WHERE b.cat_id=bc.cat_id and bc.is_deleted=0 group by bc.cat_id ORDER by total_books desc limit 3";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                BookCategory bc = new BookCategory();
                bc.setCatId(rs.getInt("cat_id"));
                bc.setName(rs.getString("name"));
                bc.setImgUrl(rs.getString("img_url"));

                data.add(bc);
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage");
        }
        return data;
    }

    public ObservableList<Book> ListerBooksByIdCatg(String title) {
        ObservableList<Book> l = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String selectCategoryId = "select * from book_category where name='" + title + "';";
            ResultSet rs = st.executeQuery(selectCategoryId);
            int id = 0;
            while (rs.next()) {
                id = rs.getInt("cat_id");
            }

            String query = "select * from book where is_deleted=0 and cat_id=" + id + ";";
            System.out.println(query);
            ResultSet rst = st.executeQuery(query);
            while (rst.next()) {
                Book b = new Book();
                b.setImgUrl(rst.getString("img_url"));
                b.setTitle(rst.getString("title"));
                b.setAuthor(rst.getString("author"));
                b.setDescription(rst.getString("description"));
                b.setBookId(rst.getInt("book_id"));
                b.setCatId(rst.getInt("cat_id"));
                l.add(b);
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;

    }
    
    public ObservableList<Event> ListerEventsByIdCatg(String title) {
        ObservableList<Event> l = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String selectCategoryId = "select * from event_category where name='" + title + "';";
            ResultSet rs = st.executeQuery(selectCategoryId);
            int id = 0;
            while (rs.next()) {
                id = rs.getInt("cat_id");
            }
            String query = "select * from event where is_deleted=0 and cat_id=" + id + ";";
            System.out.println(query);
            ResultSet rst = st.executeQuery(query);
            while (rst.next()) {
                Event e = new Event() ;
                e.setEventId(rst.getInt("event_id"));
                e.setTitle(rst.getString("title"));
                e.setDescription(rst.getString("description"));
                e.setStartDate(rst.getDate("start_date"));
                e.setEndDate(rst.getDate("end_date"));
                e.setLocation(rst.getString("location"));
                e.setType(rst.getString("type"));
                e.setCatId(rst.getInt("cat_id"));
                l.add(e);
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;

    }
    
    public ObservableList<EventCategory> topThreeEventCatg() {
        ObservableList<EventCategory> data = FXCollections.observableArrayList();
        try {

            Statement st = cnx.createStatement();
            String query = "SELECT bc.cat_id, bc.name, bc.img_url, count(*) as total_events FROM event b,event_category bc WHERE b.cat_id=bc.cat_id and bc.is_deleted=0 group by bc.cat_id ORDER by total_events desc limit 3";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                EventCategory bc = new EventCategory();
                bc.setCatId(rs.getInt("cat_id"));
                bc.setName(rs.getString("name"));
                bc.setImgUrl(rs.getString("img_url"));

                data.add(bc);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return data;
    }
    public ObservableList<RecipeCategory> topThreeRecCatg() {
        ObservableList<RecipeCategory> data = FXCollections.observableArrayList();
        try {

            Statement st = cnx.createStatement();
            String query = "SELECT bc.cat_id, bc.name, bc.img_url, count(*) as total_recipes FROM recipe b,recipe_category bc WHERE b.cat_id=bc.cat_id and bc.is_deleted=0 group by bc.cat_id ORDER by total_recipes desc limit 3";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                RecipeCategory bc = new RecipeCategory();
                bc.setCatId(rs.getInt("cat_id"));
                bc.setName(rs.getString("name"));
                bc.setImgUrl(rs.getString("img_url"));

                data.add(bc);
            }
        } catch (SQLException ex) {
            System.out.println("topThreeRecCatg :"+ex.getMessage());
        }
        return data;
    }
    
    public ObservableList<Recipe> ListerRecipesByIdCatg(String title) {
        ObservableList<Recipe> l = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String selectCategoryId = "select * from recipe_category where name='" + title + "';";
            ResultSet rs = st.executeQuery(selectCategoryId);
            int id = 0;
            while (rs.next()) {
                id = rs.getInt("cat_id");
            }
            String query = "select * from recipe where is_deleted=0 and cat_id=" + id + ";";
            System.out.println(query);
            Statement st2 = cnx.createStatement();
            ResultSet rst = st2.executeQuery(query);
            while (rst.next()) {
                Recipe r = new Recipe();
                r.setRecipeId(rst.getInt("recipe_id"));
                r.setUserId(rst.getInt("user_id"));
               // r.setCat(rst.getInt("cat_id"));
                r.setTitle(rst.getString("title"));
                r.setDescription(rst.getString("description"));
                l.add(r);
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;

    }
}
