/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices.recipe;

import coheal.entities.recipe.Recipe;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public interface IRecipeService {

    public void Create_Recipe(int user_id, String name, Recipe R);

    public List<Recipe> Afficher_Recipe();

    public void Update_Recipe(Recipe r, int id);

    public void Delete_Recipe(int idR);

    public ObservableList<Recipe> Rechercher_Recette(int idR) throws SQLException;

    public ObservableList<Recipe> Tri() throws SQLException;

}
