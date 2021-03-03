/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices;

import coheal.entities.Recipe.RecipeCategory;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IRecipeCategoryService {
    
    public void Create_RecipeCategory(RecipeCategory RC);
    
    public List<RecipeCategory> Afficher_RecipeCategory();
    
    public void Update_RecipeCategory(RecipeCategory RC);
    
    public void Delete_RecipeCategory(int idC);
}
