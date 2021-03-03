/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.iservices.book;

import coheal.entities.Book.Book;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Marwen
 */
public interface IServiceBook {
        public void AjouterBook(Book b);
     public List<Book>AfficherBook()throws SQLException ;
     
}
