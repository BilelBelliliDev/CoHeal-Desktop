/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices.recipe;

import coheal.entities.recipe.Recipe;
import coheal.entities.recipe.RecipeCategory;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IRecipeCategoryService {

    public void Create_RecipeCategory(RecipeCategory RC);

    public List<RecipeCategory> Afficher_RecipeCategory();

    public void Update_RecipeCategory(RecipeCategory RC, int id);

    public void Delete_RecipeCategory(int idc);

    public RecipeCategory RechercherRecipeCategory(String n);

    public List<Recipe> AfficherRecipesByIdCatg(String n);

    public RecipeCategory RechercherRecipeCategoryById(int id);

    }
