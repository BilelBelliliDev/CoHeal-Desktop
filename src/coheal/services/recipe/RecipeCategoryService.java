/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.recipe;

import coheal.entities.recipe.Recipe;
import coheal.entities.recipe.RecipeCategory;
import coheal.iservices.recipe.IRecipeCategoryService;
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
public class RecipeCategoryService implements IRecipeCategoryService {

    Connection con;

    public RecipeCategoryService() {
        con = MyConnection.getInstance().getConnection();
    }

    @Override
    public void Create_RecipeCategory(RecipeCategory RC) {
        try {
            Statement st = con.createStatement();
            String query = "INSERT INTO recipe_category(cat_id, name, img_url) "
                    + "VALUES (" + RC.getCatId() + ",'" + RC.getName() + "','" + RC.getImgUrl() + "')";
            st.executeUpdate(query);
            System.out.println("Category created successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<RecipeCategory> Afficher_RecipeCategory() {
        ObservableList<RecipeCategory> ListRC = FXCollections.observableArrayList();
        try {
            Statement st = con.createStatement();
            String res = "SELECT * FROM recipe_category WHERE is_deleted=0";
            ResultSet rs = st.executeQuery(res);
            while (rs.next()) {
                RecipeCategory rc = new RecipeCategory();
                rc.setCatId(rs.getInt("cat_id"));
                rc.setName(rs.getString("name"));
                rc.setImgUrl(rs.getString("img_url"));
                // image
                ImageView img = null;
                String url = "file:///" + projectPath + "/src/coheal/resources/images/recipes/" + rs.getString("img_url");
                img = new ImageView(url);
                img.setFitHeight(100);
                img.setFitWidth(100);
                rc.setImg(img);
                ListRC.add(rc);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListRC;
    }

    @Override
    public void Update_RecipeCategory(RecipeCategory RC, int id) {
        try {
            String query = "UPDATE recipe_category SET name='" + RC.getName() + "', img_url='" + RC.getImgUrl() + "' WHERE cat_id=" + id + ";";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Category modified successfully!");
        } catch (SQLException e) {
            System.out.println("Error!" + e.getMessage());
        }
    }

    @Override
    public void Delete_RecipeCategory(int idc) {
        try {
            String query = "UPDATE  recipe_category SET  is_deleted=1,deleted_at=CURRENT_TIMESTAMP() where cat_id=" + idc + "";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Category deleted successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<RecipeCategory> RechercheCatAvance(String n) throws SQLException {
        Statement stm = con.createStatement();
        String query = "SELECT * FROM recipe_category WHERE is_deleted = 0 AND (name like '" + n + "%')";
        ResultSet rs = stm.executeQuery(query);
        ObservableList<RecipeCategory> data = FXCollections.observableArrayList();
        while (rs.next()) {
            RecipeCategory rc = new RecipeCategory();
            rc.setCatId(rs.getInt("cat_id"));
            rc.setName(rs.getString("name"));
            rc.setImgUrl(rs.getString("img_url"));
            // image
            ImageView img = null;
            String url = "file:///" + projectPath + "/src/coheal/resources/images/recipes/" + rs.getString("img_url");
            img = new ImageView(url);
            img.setFitHeight(100);
            img.setFitWidth(100);
            rc.setImg(img);
            data.add(rc);
        }
        return data;
    }

    @Override
    public RecipeCategory RechercherRecipeCategory(String n) {
        String query = "select * from recipe_category where name='" + n + "';";
        RecipeCategory rc = null;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                rc = new RecipeCategory();
                rc.setCatId(rs.getInt("cat_id"));
                rc.setName(rs.getString("name"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(rc);
        return rc;
    }

    @Override
    public List<Recipe> AfficherRecipesByIdCatg(String n) {
        List<Recipe> l = new ArrayList<>();
        ImageView img = null;
        try {
            Statement st = con.createStatement();
            String selectCategoryId = "select * from recipe_category where name='" + n + "';";
            ResultSet rs = st.executeQuery(selectCategoryId);
            int id = 0;
            while (rs.next()) {
                id = rs.getInt("cat_id");
            }

            String query = "select * from recipe where is_deleted=0 and cat_id=" + id + ";";
            ResultSet rst = st.executeQuery(query);
            while (rst.next()) {
                Recipe r = new Recipe();
                r.setRecipeId(rst.getInt("recipe_id"));
                r.setUserId(rst.getInt("user_id"));
                r.setTitle(rst.getString("title"));
                r.setDescription(rst.getString("description"));
                r.setIngredients(rst.getString("ingredients"));
                r.setSteps(rst.getString("steps"));
                r.setCalories(rst.getInt("calories"));
                r.setDuration(rst.getInt("duration"));
                r.setPersons(rst.getInt("persons"));

                String url = "file:///" + projectPath + "/src/coheal/resources/images/recipes/" + rst.getString("img_url");
                img = new ImageView(url);
                r.setImg(img);
                l.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("Error! " + ex.getMessage());
        }
        return l;

    }

    @Override
    public RecipeCategory RechercherRecipeCategoryById(int id) {
        String query = "select * from recipe_category where cat_id=" + id + ";";
        RecipeCategory rc = null;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                rc = new RecipeCategory();
                rc.setCatId(rs.getInt("cat_id"));
                rc.setName(rs.getString("name"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rc;
    }

}
