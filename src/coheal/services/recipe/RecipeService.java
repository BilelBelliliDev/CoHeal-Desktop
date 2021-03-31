/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.recipe;

import coheal.entities.recipe.Recipe;
import coheal.entities.recipe.RecipeCategory;
import coheal.entities.user.User;
import coheal.iservices.recipe.IRecipeService;
import static coheal.services.recipe.Constants.projectPath;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        int catId = 0;
        try {
            Statement st = con.createStatement();
            Recipe r = new Recipe();
            String selectCategoryId = "select * from recipe_category where name='" + name + "';";
            ResultSet rs = st.executeQuery(selectCategoryId);
            while (rs.next()) {
                RecipeCategory rc = new RecipeCategory();
                rc.setCatId(rs.getInt("cat_id"));
                r.setCat(rc);
                catId = rs.getInt("cat_id");
            }
            String query = "INSERT INTO `recipe`(`user_id`, `cat_id`, `title`, `description`, `ingredients`, `steps`, `duration`, `persons`, `calories`, `img_url`) VALUES (" + user_id + "," + catId + ",'" + R.getTitle() + "','" + R.getDescription() + "','" + R.getIngredients() + "','" + R.getSteps() + "'," + R.getDuration() + "," + R.getPersons() + "," + R.getCalories() + ",'" + R.getImgUrl() + "')";
            System.out.println(query);
            st.executeUpdate(query);
            System.out.println("Recipe added successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Recipe> Afficher_Recipe() {

        ObservableList<Recipe> ListR = FXCollections.observableArrayList();
        Statement st;
        try {
            st = con.createStatement();
            String res = "SELECT * FROM recipe WHERE is_deleted=0 order by created_at desc";
            ResultSet rs = st.executeQuery(res);
            while (rs.next()) {
                Recipe r = new Recipe();
                RecipeCategoryService rcs = new RecipeCategoryService();
                r.setRecipeId(rs.getInt("recipe_id"));
                r.setUserId(rs.getInt("user_id"));
                r.setTitle(rs.getString("title"));
                r.setDescription(rs.getString("description"));
                r.setIngredients(rs.getString("ingredients"));
                r.setSteps(rs.getString("steps"));
                r.setCalories(rs.getFloat("calories"));
                r.setDuration(rs.getInt("duration"));
                r.setPersons(rs.getInt("persons"));
                r.setCat(rcs.RechercherRecipeCategoryById(rs.getInt("cat_id")));
                //image
                ImageView img = null;
                String url = "file:///" + projectPath + "/src/coheal/resources/images/recipes/" + rs.getString("img_url");
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
            String query = "UPDATE recipe SET title='" + r.getTitle() + "', description='" + r.getDescription() + "', ingredients='" + r.getIngredients() + "', steps='" + r.getSteps() + "', duration=" + r.getDuration() + ", persons=" + r.getPersons() + ", calories=" + r.getCalories() + ", img_url='" + r.getImgUrl() + "' WHERE recipe_id=" + id + ";";
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
            r.setIngredients(rst.getString("ingredients"));
            r.setSteps(rst.getString("steps"));
            r.setImgUrl(rst.getString("img_url"));
            r.setCalories(rst.getFloat("calories"));
            r.setDuration(rst.getInt("duration"));
            r.setPersons(rst.getInt("persons"));
            // image
            ImageView img = null;
            String url = "file:///" + projectPath + "/src/coheal/resources/images/recipes/" + rst.getString("img_url");
            img = new ImageView(url);
            img.setFitHeight(100);
            img.setFitWidth(100);
            r.setImg(img);
            recipe.add(r);
        }
        return recipe;
    }

    @Override
    public List<Recipe> RecipesByUserId(int id) throws SQLException {
        Statement stm = con.createStatement();
        String query = "SELECT * FROM recipe WHERE is_deleted = 0 AND user_id = " + id + "";
        ResultSet rs = stm.executeQuery(query);
        ObservableList<Recipe> data = FXCollections.observableArrayList();
        while (rs.next()) {
            Recipe r = new Recipe();
            r.setUserId(rs.getInt("user_id"));
            r.setRecipeId(rs.getInt("recipe_id"));
            r.setTitle(rs.getString("title"));
            r.setDescription(rs.getString("description"));
            r.setIngredients(rs.getString("ingredients"));
            r.setSteps(rs.getString("steps"));
            r.setImgUrl(rs.getString("img_url"));
            r.setCalories(rs.getFloat("calories"));
            r.setDuration(rs.getInt("duration"));
            r.setPersons(rs.getInt("persons"));
            // image
            ImageView img = null;
            String url = "file:///" + projectPath + "/src/coheal/resources/images/recipes/" + rs.getString("img_url");
            img = new ImageView(url);
            img.setFitHeight(100);
            img.setFitWidth(100);
            r.setImg(img);
            data.add(r);
        }
        return data;
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
            r.setIngredients(rs.getString("ingredients"));
            r.setSteps(rs.getString("steps"));
            r.setImgUrl(rs.getString("img_url"));
            r.setCalories(rs.getFloat("calories"));
            r.setDuration(rs.getInt("duration"));
            r.setPersons(rs.getInt("persons"));
            // image
            ImageView img = null;
            String url = "file:///" + projectPath + "/src/coheal/resources/images/recipes/" + rs.getString("img_url");
            img = new ImageView(url);
            img.setFitHeight(100);
            img.setFitWidth(100);
            r.setImg(img);
            ListR.add(r);
        }
        return ListR;
    }

    @Override
    public ObservableList<Recipe> RechercheRecipeAvance(String t) throws SQLException {
        Statement stm = con.createStatement();
        String query = "SELECT * FROM recipe WHERE is_deleted = 0 AND (title like '" + t + "%')";
        ResultSet rs = stm.executeQuery(query);
        ObservableList<Recipe> ListR = FXCollections.observableArrayList();
        while (rs.next()) {
            Recipe r = new Recipe();
            r.setRecipeId(rs.getInt("recipe_id"));
            r.setTitle(rs.getString("title"));
            r.setDescription(rs.getString("description"));
            r.setIngredients(rs.getString("ingredients"));
            r.setSteps(rs.getString("steps"));
            r.setImgUrl(rs.getString("img_url"));
            r.setCalories(rs.getFloat("calories"));
            r.setDuration(rs.getInt("duration"));
            r.setPersons(rs.getInt("persons"));
            // image
            ImageView img = null;
            String url = "file:///" + projectPath + "/src/coheal/resources/images/recipes/" + rs.getString("img_url");
            img = new ImageView(url);
            img.setFitHeight(100);
            img.setFitWidth(100);
            r.setImg(img);
            ListR.add(r);
        }
        return ListR;
    }

    @Override
    public Recipe getRecipe(int idR) {
        Recipe r = null;
        ImageView img = null;
        try {
            Statement st = con.createStatement();
            String query = "select recipe_id,user_id,cat_id,title,description,ingredients,steps,duration,persons,calories,img_url,created_at from recipe  where recipe_id=" + idR + " and is_deleted=0 ;";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Recipe recipe = new Recipe();
                RecipeCategoryService rcs = new RecipeCategoryService();
                recipe.setRecipeId(rs.getInt("recipe_id"));
                recipe.setTitle(rs.getString("title"));
                recipe.setDescription(rs.getString("description"));
                recipe.setIngredients(rs.getString("ingredients"));
                recipe.setSteps(rs.getString("steps"));
                recipe.setImgUrl(rs.getString("img_url"));
                recipe.setCalories(rs.getFloat("calories"));
                recipe.setDuration(rs.getInt("duration"));
                recipe.setPersons(rs.getInt("persons"));
                recipe.setImgUrl(rs.getString("img_url"));
                recipe.setCat(rcs.RechercherRecipeCategoryById(rs.getInt("cat_id")));
                User u = getUserById(rs.getInt("user_id"));
                recipe.setUser(u);
                String url = "file:///" + projectPath + "/src/coheal/resources/images/recipes/" + rs.getString("img_url");
                img = new ImageView(url);
                recipe.setImg(img);
                recipe.setCreatedAt(rs.getTimestamp("created_at"));
                r = recipe;
            }

        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        return r;
    }

    public User getUserById(int idU) {
        User u = null;
        String query = "select user_id from user where user_id=" + idU;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                u = user;
            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        System.out.println(u);
        return u;
    }

}
