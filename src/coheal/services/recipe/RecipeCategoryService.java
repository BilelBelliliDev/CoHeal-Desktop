/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coheal.services.recipe;

import coheal.entities.Recipe.RecipeCategory;
import coheal.iservices.recipe.IRecipeCategoryService;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
        try {
        Statement st = con.createStatement();
        String query = "INSERT INTO recipe_category(cat_id, name, img_url) "
                + "VALUES (" + RC.getCatId()+ ",'"+ RC.getName()+ "','" + RC.getImgUrl()+ "')";
        st.executeUpdate(query);
        System.out.println("Catégorie ajoutée avec succés!");
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());       
        }
    }

    @Override
    public List<RecipeCategory> Afficher_RecipeCategory() {
        ArrayList<RecipeCategory> ListRC = new ArrayList();
        try {
            Statement st = con.createStatement();
            String res = "SELECT * FROM recipe_category WHERE is_deleted=0";
            ResultSet rs = st.executeQuery(res);
            while (rs.next()){
                RecipeCategory rc = new RecipeCategory();
                rc.setCatId(rs.getInt("cat_id"));
                rc.setName(rs.getString("name"));
                rc.setImgUrl(rs.getString("img_url"));
                ListRC.add(rc);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
        return ListRC;
    }

    @Override
    public void Update_RecipeCategory(RecipeCategory RC, int id) {
try {
            String query = "UPDATE recipe_category SET cat_id=" + RC.getCatId() + ", name='" + RC.getName() + "', img_url='" + RC.getImgUrl() + "' WHERE cat_id="+id+";";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Catégorie modifiée avec succés!");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification de la Catégorie!"+e.getMessage());
        }    
}
    
    @Override
    public void Delete_RecipeCategory(int idc) {
        try {
            String query = "UPDATE  recipe_category SET  is_deleted=1,deleted_at=CURRENT_TIMESTAMP() where cat_id=" + idc +"" ;
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Suppression de la catégorie avec succés!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
}

   