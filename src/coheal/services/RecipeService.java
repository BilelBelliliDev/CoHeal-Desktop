/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services;

import coheal.entities.Recipe.Recipe;
import coheal.iservices.IRecipeService;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 *
 * @author HP
 */
public class RecipeService implements IRecipeService {

    Connection con;
    
    public RecipeService(){
        con = MyConnection.getInstance().getConnection();
    }
    
    @Override
    public void Create_Recipe(int user_id,String name,Recipe R) {
        try {
        Statement st = con.createStatement();
        String query = "INSERT INTO recipe(user_id, cat_id, title, description, img_url) "
                + "VALUES (" +user_id+ "," + R.getCatId()+ ",'"+ R.getTitle()+ "','" + R.getDescription()+ "','" + R.getImgUrl()+ "')";
        st.executeUpdate(query);
        System.out.println("Recette ajoutée avec succés!");
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());       
        }
    }

    @Override
    public List<Recipe> Afficher_Recipe() {
        ArrayList<Recipe> ListR = new ArrayList();
        try {
            Statement st = con.createStatement();
            String res = "SELECT * FROM recipe";
            ResultSet rs = st.executeQuery(res);
            while (rs.next()){
                Recipe r = new Recipe();
                r.setUserId(rs.getInt("user_id"));
                r.setCatId(rs.getInt("cat_id"));
                r.setTitle(rs.getString("title"));
                r.setDescription(rs.getString("description"));
                ListR.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
        return ListR;
    }

    @Override
    public void Update_Recipe(Recipe r,int id) {
       try {
            String query = "UPDATE recipe SET recipe_id=" + r.getRecipeId() + ", user_id=" + r.getUserId() + ", cat_id=" + r.getCatId() + ",' title=" + r.getTitle() + "',' description" + r.getDescription() + "',' img_url=" + r.getImgUrl() + "',' WHERE recipe_id="+id+";";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Recette modifiée avec succés!");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification de la Recette!");
        }
    }

    @Override
   public void Delete_Recipe(int idR) {
       try {
            String query = "UPDATE  recipe SET  is_deleted=1,deleted_at=CURRENT_TIMESTAMP() where task_id=" + idR +"" ;
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Suppression de la recette avec succés!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}

