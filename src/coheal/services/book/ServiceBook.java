/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coheal.services.book;

import coheal.entities.book.Book;
import coheal.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Marwen
 */
public class ServiceBook  {
         Connection cnx;
        public ServiceBook(){
                cnx=MyConnection.getInstance().getConnection();
        }

       // @Override
        public void AjouterBook(Book b) {
                try {
                        Statement stm=cnx.createStatement();
                        
                
                String query="INSERT INTO book( cat_id, user_id,img_url,file_path,author, title, description) VALUES ("+b.getCatId()+","+b.getUserId()+",'"+b.getImgUrl()+"','"+b.getFilePath()+"','"+b.getAuthor()+"','"+b.getTitle()+"','"+b.getDescription()+"')";
                stm.executeUpdate(query);
                
                }
                 catch (SQLException ex) {
                        System.out.println("le");
                } 
        }

      /*  @Override
        public List<book> AfficherBook() throws SQLException  {
                
                        Statement stm=cnx.createStatement();
                        String query="SELECT author,title,description FROM book ";
                        ResultSet rst=stm.executeQuery(query);
                        List<book> books=new ArrayList<>();
                        while(rst.next()){
                                book b= new book();
                                b.setTitle(rst.getString("title"));
                                b.setAuthor(rst.getString("author"));
                                b.setDescription(rst.getString("description"));
                                books.add(b);
                               
                                
                        }
                
                return books;
                
        }*/
       // @Override
        public ObservableList<Book>AfficherBook2()throws SQLException {
                Statement stm=cnx.createStatement();
                        String query="SELECT book_id,img_url,author,title,description FROM book WHERE is_deleted = 0 ";
                        ResultSet rst=stm.executeQuery(query);
                         ObservableList<Book> data = FXCollections.observableArrayList();
                          while(rst.next()){
                                Book b= new Book();
                                b.setImgUrl(rst.getString("img_url"));
                                b.setTitle(rst.getString("title"));
                                b.setAuthor(rst.getString("author"));
                                b.setDescription(rst.getString("description"));
                                b.setBookId(rst.getInt("book_id"));
                                data.add(b);
                               
                                
                        }
                          return data;
        }
        public void supprimerBook(int id){
                 try {
            Statement stm=cnx.createStatement();
            String query;
            query ="update book set is_deleted = 1,deleted_at=CURRENT_TIMESTAMP  WHERE book.book_id  = "+id+"";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
        public void modifierBook(int id, Book b){
                 try {
            Statement stm=cnx.createStatement();
            String query;
            query ="update book set img_url = '"+b.getImgUrl()+"', title = '"+b.getTitle()+"', author = '"+b.getAuthor()+"', description = '"+b.getDescription()+"' where book_id = "+id+"";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          

}
          public ObservableList<Book>Rechercher(int t)throws SQLException{
                Statement stm=cnx.createStatement();
                        String query="SELECT * FROM book WHERE is_deleted = 0 AND book_id = "+t+"";
                        ResultSet rst=stm.executeQuery(query);
                         ObservableList<Book> data = FXCollections.observableArrayList();
                          while(rst.next()){
                                Book b= new Book();
                                b.setImgUrl(rst.getString("img_url"));
                                b.setTitle(rst.getString("title"));
                                b.setAuthor(rst.getString("author"));
                                b.setDescription(rst.getString("description"));
                                b.setBookId(rst.getInt("book_id"));
                                data.add(b);
                               
                                
                        }
                          return data;
        }
        public ObservableList<Book>Try()throws SQLException{
                Statement stm=cnx.createStatement();
                        String query="SELECT * FROM book WHERE is_deleted = 0 ORDER BY views DESC  ";
                        ResultSet rst=stm.executeQuery(query);
                         ObservableList<Book> data = FXCollections.observableArrayList();
                          while(rst.next()){
                                Book b= new Book();
                                b.setImgUrl(rst.getString("img_url"));
                                b.setTitle(rst.getString("title"));
                                b.setAuthor(rst.getString("author"));
                                b.setDescription(rst.getString("description"));
                                b.setBookId(rst.getInt("book_id"));
                                data.add(b);
                               
                                
                        }
                          return data;
        }}
