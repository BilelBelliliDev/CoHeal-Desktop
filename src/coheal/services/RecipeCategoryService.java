/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coheal.services;

import coheal.entities.Recipe.Recipe;
import coheal.entities.Recipe.RecipeCategory;
import coheal.iservices.IRecipeCategoryService;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class RecipeCategoryService implements IRecipeCategoryService {

    Connection con;
    
    public RecipeCategoryService(){
        con = MyConnection.getInstance().getConnection();
    }

    @Override
    public void Create_RecipeCategory(RecipeCategory RC) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecipeCategory> Afficher_RecipeCategory() {
        ArrayList<RecipeCategory> ListRC = new ArrayList();
        try {
            
            Statement st = con.createStatement();
            String res = "SELECT * FROM `recipe_category`";
            ResultSet rs = st.executeQuery(res);
            
            while (rs.next()){
                ListRC.add(new RecipeCategory(rs.getInt("cat_id"),rs.getString("name"),rs.getString("img_url")));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'affichage de la catégorie!");       
        }
        return ListRC;
    }

    @Override
    public void Update_RecipeCategory(RecipeCategory RC) {
         try {
            String query = "`recipe` SET `recipe_id`=" + RC.getCatId() + "',' `name`=" + RC.getName() + "',' `img_url`=" + RC.getImgUrl() + "',' WHERE `cat_id`="+ RC.getCatId()+";";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Catégorie modifiée avec succés!");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification de la catégorie!");
        }
    }

    @Override
    public void Delete_RecipeCategory(int idC) {
        try {
            Calendar calendar = Calendar.getInstance();
            Timestamp d = new Timestamp(calendar.getTime().getTime());
            String query = "UPDATE  recipe_category SET  is_deleted=" + 1 +",deleted_at='"+d +"' where cat_id=" + idC ;
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Suppression de la recette avec succés!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
}



   