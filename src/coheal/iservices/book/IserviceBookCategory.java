/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices.book;

import coheal.entities.book.BookCategory;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author Marwen
 */
public interface IserviceBookCategory {
         public void AjouterBookCategory(BookCategory bc);
         public ObservableList<BookCategory>AfficherBookCat()throws SQLException ;
     public void supprimerBookCat(int id);
     public void modifierBookCat(int id, BookCategory bc);
}
