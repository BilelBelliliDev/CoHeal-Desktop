/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.recipe;

import coheal.entities.recipe.Recipe;
import coheal.iservices.recipe.IRecipeService;
import static coheal.services.recipe.Constants.projectPath;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;

/**
 *
 * @author HP
 */
public class RecipeService implements IRecipeService {

    Connection con;

    public RecipeService() {
        con = MyConnection.getInstance().getConnection();
    }

    @Override
    public void Create_Recipe(int user_id, String name, Recipe R) {
        try {
            Statement st = con.createStatement();
            String query = "INSERT INTO recipe(user_id, cat_id, title, description, img_url, duration, persons, ingredients, steps) "
                    + "VALUES (" + user_id + "," + R.getCatId() + ",'" + R.getTitle() + "','" + R.getDescription() + "','" + R.getImgUrl() +"','" + R.getIngredients()+ "','" + R.getSteps()+ "," + R.getDuration()+ "," + R.getPersons() +")";
            st.executeUpdate(query);
            System.out.println("Recipe added successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Recipe> Afficher_Recipe() {

        ObservableList<Recipe> ListR = FXCollections.observableArrayList();
        Statement st;
        try {
            st = con.createStatement();
            String res = "SELECT * FROM recipe WHERE is_deleted=0";
            ResultSet rs = st.executeQuery(res);
            while (rs.next()) {
                Recipe r = new Recipe();
                r.setRecipeId(rs.getInt("recipe_id"));
                r.setUserId(rs.getInt("user_id"));
                r.setCatId(rs.getInt("cat_id"));
                r.setTitle(rs.getString("title"));
                r.setDescription(rs.getString("description"));
                r.setDuration(rs.getInt("duration"));
                r.setIngredients(rs.getString("ingredients"));
                r.setSteps(rs.getString("steps"));
                r.setPersons(rs.getInt("persons"));
                ListR.add(r);
                //image
                ImageView img = null;
                String url = "file:///" + projectPath + "/coheal/images/" + rs.getString("img_url");
                img = new ImageView(url);
                img.setFitHeight(100);
                img.setFitWidth(100);
                r.setImg(img);
                ListR.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListR;
    }

    @Override
    public void Update_Recipe(Recipe r, int id) {
        try {
            String query = "UPDATE recipe SET user_id=1" + ", cat_id=" + r.getCatId() + ", title='" + r.getTitle() + "', description='" + r.getDescription() + "', img_url='" + r.getImgUrl() + "' WHERE recipe_id=" + id + ";";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Recipe modified successfully!");
        } catch (SQLException e) {
            System.out.println("Error!" + e.getMessage());
        }
    }

    @Override
    public void Delete_Recipe(int idR) {
        try {
            String query = "UPDATE  recipe SET  is_deleted=1,deleted_at=CURRENT_TIMESTAMP() where recipe_id=" + idR + "";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Recipe deleted successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public ObservableList<Recipe> Rechercher_Recette(int idR) throws SQLException {
        Statement st = con.createStatement();
        String query = "SELECT * FROM recipe WHERE is_deleted = 0 AND recipe_id = " + idR + "";
        ResultSet rst = st.executeQuery(query);
        ObservableList<Recipe> recipe = FXCollections.observableArrayList();
        while (rst.next()) {
            Recipe r = new Recipe();
            r.setRecipeId(rst.getInt("recipe_id"));
            r.setTitle(rst.getString("title"));
            r.setDescription(rst.getString("description"));
            r.setImgUrl(rst.getString("img_url"));
            recipe.add(r);
        }
        return recipe;
    }

    @Override
    public ObservableList<Recipe> Tri() throws SQLException {
        Statement stm = con.createStatement();
        String query = "SELECT * FROM recipe WHERE is_deleted = 0 ORDER BY title DESC  ";
        ResultSet rs = stm.executeQuery(query);
        ObservableList<Recipe> ListR = FXCollections.observableArrayList();
        while (rs.next()) {
            Recipe r = new Recipe();
            r.setRecipeId(rs.getInt("recipe_id"));
            r.setTitle(rs.getString("title"));
            r.setDescription(rs.getString("description"));
            r.setImgUrl(rs.getString("img_url"));
            ListR.add(r);
        }
        return ListR;
    }
}
